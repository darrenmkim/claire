(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer :all]
            [claire.mock :refer :all]))

(def stance-to-event
  {:payer :int-pay
   :receiver :int-receive})

(defn find-presets [pact event]
  (filter
   (fn [x] (and (= (:pact x) pact)
                (= (:event x) event)))
   mock-presets))
  
(defn calc-interest-cash [notional rate frac]
  (* notional (/ rate 100.0) frac))



(defn make-jour-cash-interest [roll leg date]
  (let [pact (:pact leg)
        event ((:stance leg) stance-to-event)
        roll-id (:id roll)
        amount 11
        presets (filter
                 (fn [x] (and (= (:pact x) pact)
                              (= (:event x) event)))
                 mock-presets)]
    (for [preset presets]
      (make-journal nil
                    nil
                    (:account-id preset)
                    (num-by-sign amount (:sign preset))
                    roll-id))))


(defn make-tran-cash-interest [roll leg date]
  (let [notional (:notional leg)
        rate (:rate leg)
        frac (:frac ((:freq leg) freqs))
        event ((:stance leg) stance-to-event)
        months (:months ((:freq leg) freqs))
        amount (calc-interest-cash notional rate frac)         
        annote (str notional "*(" rate "/100)*" "(" months "/12)" "=" amount)]
    (make-tran nil date (:id leg) event (:contracts leg) amount annote (:id roll))))



(defn process-cash-interest [roll leg start]
  (let [mature (:mature (find-single mock-deals :id (:deal-id leg)))
        months (:months ((:freq leg) freqs))
        new-start (t/plus start (t/months months))
        tran (make-tran-cash-interest roll leg start)
        jour (make-jour-cash-interest roll leg start)
        tj {:tran tran :jour jour}]
    (if (t/after? start mature)
      '()
      (cons tj
            (make-cash-interest roll leg new-start)))))

;; test 
(def start (date 2020 02 25))
(def roll (first mock-rolls))
(def leg (first mock-legs))
(def cash-test (process-cash-interest roll leg start))



