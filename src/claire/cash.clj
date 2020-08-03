(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer [freqs events make-roll
                                   deal-kinds leg-kinds
                                   make-leg make-deal make-tran
                                   make-journal
                                   ]]
            [claire.mock :refer :all]))


(defn journal-cash [kind event amount]
  (let [presets
         (filter
          (fn [x] (and
                   (= (:leg-kind x) kind)
                   (= (:event x) event)))
          mock-presets)]
    (for [preset presets]
      (make-journal nil
                    preset
                    ((:sign preset) amount)
                    ))))


(defn tran-cash [roll leg pick]
  (let* [kind (:kind leg)
         event (case (:stance leg)
                 :payer :pay
                 :receiver :receive)
         contracts (case (:kind (:deal leg))
                     :irs 1 :crs 1 nil)                    
         mature (:mature (:deal leg))
         months (:months ((:freq leg) freqs))
         interest (* (:notional leg)
                     (/ (:rate leg) 100.0)
                     (:frac ((:freq leg) freqs)))]
    (if (t/after? pick mature)
      '()
      (cons
       (make-tran nil
                  pick leg event contracts interest "abc" roll
                  (journal-cash kind event interest))
       (cash roll leg (t/plus pick (t/months months)))))))

(def mock-cash-testing (tran-cash mock-roll mock-leg-a system-date))
