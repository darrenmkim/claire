(ns claire.mock
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer :all]))

(def deals
  [(make-deal 1 "DEALIRSMOCK" :irs (make-date 2020 1 1)
              (make-date 2020 1 3) (make-date 2025 1 3) (make-date 2025 1 3))
   (make-deal 2 "DEALIRSMOCK" :irs (make-date 2020 1 1)
              (make-date 2020 1 3) (make-date 2025 1 3) (make-date 2025 1 3))
   (make-deal 3 "DEALIRSMOCK" :irs (make-date 2020 1 1)
              (make-date 2020 1 3) (make-date 2025 1 3) (make-date 2025 1 3))
   (make-deal 4 "DEALIRSMOCK" :irs (make-date 2020 1 1)
              (make-date 2020 1 3) (make-date 2025 1 3) (make-date 2025 1 3))])
   
(def legs
  [(make-leg 1 "ABCIRSFIX" 1 :irs-fixed :payer :usd :usd
             :semiannually :dc-30-360 1000000.0 :libor 2.0)
   (make-leg 2 "ABCIRSFLT" 1 :irs-float :receiver :usd :usd
             :semiannually :dc-30-360 1000000.0 :libor nil)])

(def accounts
  [(make-account 1 "Cash" 1111 "Cash Clearing Account")
   (make-account 2 "Receivable" 1121 "Derivative Receivable")
   (make-account 3 "Payable" 2121 "Derivative Payble")
   (make-account 4 "Income" 3111 "Derivative Income")
   (make-account 5 "Realized GL" 5111 "Cash Clearing Account")])

(def presets
  [(make-preset 1 :irs-fixed :int-pay "irs fixed int pay cash" 1 :n)
   (make-preset 2 :irs-fixed :int-pay "irs fixed int pay income" 3 :p)
   (make-preset 3 :irs-fixed :int-receive "irs fixed int receive cash" 1 :p)
   (make-preset 4 :irs-fixed :int-receive "irs fixed int receive income" 2 :n)
   (make-preset 5 :irs-float :pay "irs fixed int pay cash" 1 :n)
   (make-preset 6 :irs-float :pay "irs fixed int pay income" 2 :p)
   (make-preset 7 :irs-float :receive "irs fixed int receive cash" 1 :p)
   (make-preset 8 :irs-float :receive "irs fixed int receive income" 2 :n)])

(def rolls
  [(make-roll 1 :calc (make-date 2020 3 1) (make-date 2020 3 31))
   (make-roll 2 :revert (make-date 2020 3 1) (make-date 2020 3 31))
   (make-roll 3 :calc (make-date 2020 3 1) (make-date 2020 3 31))
   (make-roll 4 :post (make-date 2020 3 1) (make-date 2020 3 31))])

(def rates
  [(make-rate 1 (make-date 2020 2 25) :libor 2.5)
   (make-rate 2 (make-date 2020 8 25) :libor 2.5)
   (make-rate 3 (make-date 2021 2 25) :libor 2.5)
   (make-rate 4 (make-date 2021 8 25) :libor 2.5)
   (make-rate 5 (make-date 2022 2 25) :libor 2.5)
   (make-rate 6 (make-date 2022 8 25) :libor 2.5)
   (make-rate 7 (make-date 2023 2 25) :libor 2.5)
   (make-rate 8 (make-date 2023 8 25) :libor 2.5)
   (make-rate 9 (make-date 2024 2 25) :libor 2.5)
   (make-rate 10 (make-date 2024 8 25) :libor 2.5)
   (make-rate 11 (make-date 2025 2 25) :libor 2.5)
   (make-rate 12 (make-date 2025 8 25) :libor 2.5)
   (make-rate 13 (make-date 2026 2 25) :libor 2.5)
   (make-rate 14 (make-date 2026 8 25) :libor 2.5)
   (make-rate 15 (make-date 2027 2 25) :libor 2.5)
   (make-rate 16 (make-date 2027 8 25) :libor 2.5)])

