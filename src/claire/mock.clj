(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer :all]))

(def mock-deals
  [(make-deal 1 "DEALIRSMOCK" :irs (date 2020 1 1)
              (date 2020 1 3) (date 2025 1 3) (date 2025 1 3))
   (make-deal 2 "DEALIRSMOCK" :irs (date 2020 1 1)
              (date 2020 1 3) (date 2025 1 3) (date 2025 1 3))
   (make-deal 3 "DEALIRSMOCK" :irs (date 2020 1 1)
              (date 2020 1 3) (date 2025 1 3) (date 2025 1 3))
   (make-deal 4 "DEALIRSMOCK" :irs (date 2020 1 1)
              (date 2020 1 3) (date 2025 1 3) (date 2025 1 3))])
   
(def mock-legs
  [(make-leg 1 "ABCIRSFIX" 1 :irs-fixed :payer :usd
             :semiannually :dc-30-360 1000000.0 2.0)
   (make-leg 2 "ABCIRSFLT" 1 :irs-float :receiver :usd
             :semiannually :dc-30-360 1000000.0 nil)])

(def mock-accounts
  [(make-account 1 "Cash" 1111 "Cash Clearing Account")
   (make-account 2 "Receivable" 1121 "Derivative Receivable")
   (make-account 3 "Payable" 2121 "Derivative Payble")
   (make-account 4 "Income" 3111 "Derivative Income")
   (make-account 5 "Realized GL" 5111 "Cash Clearing Account")])

(def mock-presets
  [(make-preset 1 :irs-fixed :pay "irs fixed int pay cash" 1 -)
   (make-preset 2 :irs-fixed :pay "irs fixed int pay income" 3 +)
   (make-preset 3 :irs-fixed :receive "irs fixed int receive cash" 1 +)
   (make-preset 4 :irs-fixed :receive "irs fixed int receive income" 2 -)
   (make-preset 5 :irs-float :pay "irs fixed int pay cash" 1 -)
   (make-preset 6 :irs-float :pay "irs fixed int pay income" 2 +)
   (make-preset 7 :irs-float :receive "irs fixed int receive cash" 1 +)
   (make-preset 8 :irs-float :receive "irs fixed int receive income" 2 -)])

(def mock-rolls
  [(make-roll 1 :calc (date 2020 3 1) (date 2020 3 31))
   (make-roll 2 :revert (date 2020 3 1) (date 2020 3 31))
   (make-roll 3 :calc (date 2020 3 1) (date 2020 3 31))
   (make-roll 4 :post (date 2020 3 1) (date 2020 3 31))])
