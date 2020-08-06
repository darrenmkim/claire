(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer :all]
            [claire.mock :refer :all]))

(defn calc-interest-cash [leg date]
  (let [pact (:pact leg)
        notional (:notional leg)
        months (:months ((:freq leg) freqs))
        frac (:frac ((:freq leg) freqs))
        rate (case pact
                 :irs-fixed (:fixed-r leg)
                 :irs-float (find-rate date (:rate-c leg)))
        amount (* notional (/ rate 100.0) frac)
        annote (str notional "*(" rate "/100)*" "(" months "/12)" "=" amount)]
    {:amount amount :annote annote}))
  
(defn make-jour-cash-interest [roll leg tran-id tran-amount date]
  (let [pact (:pact leg)
        event ((:stance leg) stance-to-event)
        roll-id (:id roll)
        presets (filter
                 (fn [x] (and (= (:pact x) pact)
                              (= (:event x) event)))
                 mock-presets)]
    (for [preset presets]
      (make-journal nil tran-id (:account-id preset)
                    (num-by-sign tran-amount (:sign preset))
                    roll-id))))

(defn make-tran-cash-interest [roll leg date]
  (let [roll-id (:id roll)
        leg-id (:id leg)
        notional (:notional leg)
        contracts (:contract leg)
        frac (:frac ((:freq leg) freqs))
        event ((:stance leg) stance-to-event)
        months (:months ((:freq leg) freqs))
        calc (calc-interest-cash leg date)
        amount (:amount calc)
        annote (:annote calc)]
    (make-tran nil date leg-id event contracts amount annote roll-id)))

(defn process-cash-interest [roll leg start]
  (let [mature (:mature (find-single mock-deals :id (:deal-id leg)))
        months (:months ((:freq leg) freqs))
        new-start (t/plus start (t/months months))
        tran (make-tran-cash-interest roll leg start)
        tran-id (:id tran)
        tran-amount (:amount tran)
        jour (make-jour-cash-interest roll leg tran-id tran-amount start)
        tj {:tran tran :jour jour}]
    (if (t/after? start mature)
      '()
      (cons tj
            (process-cash-interest roll leg new-start)))))

;; test 
(def start (make-date 2020 02 25))
(def roll (first mock-rolls))
(def leg (first mock-legs))
(def cash-test (process-cash-interest roll leg start))
(def cash-count {:count "abc"})
