(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer :all]
            [claire.mock :refer :all]))




(defn journal-cash [pact event amount roll-id]
  (let [presets
        (filter
         (fn [x] (and (= (:pact x) pact)
                      (= (:event x) event)))
         mock-presets)]
    (for [preset presets]
      (make-journal nil
                    nil
                    (:account-id preset)
                    (num-by-sign amount (:sign preset))
                    roll-id))))

(defn tran-cash [roll-id leg-id pick]
  (let [leg (find-first mock-legs :id leg-id)
        pact (:pact leg)
        event (case (:stance leg)
                :payer :pay :receiver :receive)
        deal (find-first mock-deals :id (:deal-id leg))
        contracts (case (:breed deal)
                    :irs 1 :crs 1 nil)                    
        mature (:mature deal)
        months (:months ((:freq leg) freqs))
        notional (:notional leg)
        rate (:rate leg)
        frac (:frac ((:freq leg) freqs))
        amount (* notional (/ rate 100.0) frac) 
        annote (str notional "*(" rate "/100)*" "(" months "/12)" "=" amount)
        new-pick (t/plus pick (t/months months))
        tran (make-tran nil pick leg-id event contracts amount annote roll-id)
        journal (journal-cash pact event amount roll-id)
        tran-and-journal {:tran tran :journal journal}]
    (if (t/after? pick mature)
      '()
      (cons tran-and-journal    
       (tran-cash roll-id leg-id new-pick)))))

;; test 
(def pick (date 2020 02 25))
(def mock-cash-testing (tran-cash 1 1 pick))
