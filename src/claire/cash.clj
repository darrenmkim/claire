(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :as dm]
            [claire.port :as port]
            [claire.mock :as mock]))

(defn find-mature [leg]
  (:mature
   (port/find-single mock/deals
                     :id
                     (:deal-id leg))))

(defn find-months [leg]
  (:months
   ((:freq leg) dm/freqs)))

(defn find-frac [leg]
  (:frac ((:freq leg) dm/freqs)))

(defn find-event [leg]
  ((:stance leg) dm/stance-to-event))

(defn calc-interest-cash [leg date]
  (let [pact (:pact leg)
        notional (:notional leg)
        months (find-months leg)
        frac (find-frac leg)
        rate (case pact
                 :irs-fixed (:fixed-r leg)
                 :irs-float (port/find-rate date (:rate-c leg)))
        amount (* notional (/ rate 100.0) frac)
        annote (str notional "*(" rate "/100)*" "(" months "/12)" "=" amount)]
    {:amount amount :annote annote}))
  
(defn interim-jour [roll leg tran-id tran-amount date]
  (let [pact (:pact leg)
        event (find-event leg)
        roll-id (:id roll)
        presets (port/find-presets pact event)]
    (for [preset presets]
      (dm/make-journal nil tran-id (:account-id preset)
                    (dm/num-by-sign tran-amount (:sign preset))
                    roll-id))))

(defn interim-tran [roll leg date]
  (let [roll-id (:id roll)
        leg-id (:id leg)
        notional (:notional leg)
        contracts (:contract leg)
        frac (find-frac leg)
        event (find-event leg)
        months (find-months leg)
        calc (calc-interest-cash leg date)
        amount (:amount calc)
        annote (:annote calc)]
    (dm/make-tran nil date leg-id event contracts amount annote roll-id)))

(defn interim [roll leg start]
  (let [mature (find-mature leg)
        months (find-months leg)
        new-start (t/plus start (t/months months))
        tran (interim-tran roll leg start)
        tran-id (:id tran)
        tran-amount (:amount tran)
        jour (interim-jour roll leg tran-id tran-amount start)
        tj {:tran tran :jour jour}]
    (if (t/after? start mature)
      '()
      (cons tj
            (interim roll leg new-start)))))

;; test 
(def start (dm/make-date 2020 02 25))
(def roll (first mock/rolls))
(def leg (first mock/legs))
(def cash-test (interim roll leg start))
(def cash-count {:count "abc"})
