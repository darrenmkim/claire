(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer [make-roll
                                   make-preset
                                   make-account
                                   make-leg
                                   make-deal]]))

(def mock-deal (make-deal 123 "DEALIRSMOCK" :irs
                          (t/zoned-date-time 2020 1 1)
                          (t/zoned-date-time 2020 1 3)
                          (t/zoned-date-time 2025 1 3)
                          (t/zoned-date-time 2025 1 3)))

(def mock-leg-a
  (make-leg 1 "ABCIRSFIX" mock-deal :irs-fixed :payer :usd
            :semiannually :dc-30-360 1000000.0 2.0))

(def mock-leg-b
  (make-leg 2 "ABCIRSFLT" mock-deal :irs-float :receiver :usd
            :semiannually :dc-30-360 1000000.0 nil))

(def mock-acc-cash (make-account 1 "Cash" 1111 "Cash clearing account"))
(def mock-acc-receivable (make-account 2 "Receivable" 1121 "Derivative receivable"))
(def mock-acc-payable (make-account 3 "Payable" 2121 "Derivative payble"))
(def mock-acc-income (make-account 4 "Income" 3111 "Derivative income"))
(def mock-acc-rgainloss (make-account 5 "Realized GL" 5111 "Cash clearing account"))

(def mock-presets
  [(make-preset nil :irs-fixed :pay "irs fixed int pay cash" mock-acc-cash -)
   (make-preset nil :irs-fixed :pay "irs fixed int pay income" mock-acc-income +)
   (make-preset nil :irs-fixed :receive "irs fixed int receive cash" mock-acc-cash +)
   (make-preset nil :irs-fixed :receive "irs fixed int receive income" mock-acc-income -)
   (make-preset nil :irs-float :pay "irs fixed int pay cash" mock-acc-cash -)
   (make-preset nil :irs-float :pay "irs fixed int pay income" mock-acc-income +)
   (make-preset nil :irs-float :receive "irs fixed int receive cash" mock-acc-cash +)
   (make-preset nil :irs-float :receive "irs fixed int receive income" mock-acc-income -)])

;; roll 
(def system-date (t/zoned-date-time 2020 5 1))
(def target-date (t/zoned-date-time 2020 5 31))
(def mock-roll (make-roll 1 :calc system-date target-date))



(def filtertest
  (filter (fn [x] (and
                   (= (:leg-kind x) :irs-fixed)
                   (= (:event x) :pay)))
          mock-presets))
;;=> ("a" "b" "n" "f" "q")


