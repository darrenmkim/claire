(ns claire.cash
  (:refer-clojure :exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer [make-roll
                                   make-leg
                                   make-deal]]))

(def mock-deal (make-deal 123 "DEALIRSMOCK" :irs
                          (t/zoned-date-time 2020 1 1)
                          (t/zoned-date-time 2020 1 3)
                          (t/zoned-date-time 2025 1 3)
                          (t/zoned-date-time 2025 1 3)))

(def mock-leg-a (make-leg 1
                          "ABCIRSFIX"
                          mock-deal
                          :irs-fixed
                          :payer
                          :usd
                          :semiannually
                          :dc-30-360
                          1000000.0
                          2.0))

(def mock-leg-b (make-leg 2
                          "ABCIRSFLT"
                          mock-deal
                          :irs-float
                          :receiver
                          :usd
                          :semiannually
                          :dc-30-360
                          1000000.0
                          nil))

;; roll 
(def system-date (t/zoned-date-time 2020 5 1))
(def target-date (t/zoned-date-time 2020 5 31))
(def mock-roll (make-roll 1 :calc system-date target-date))

(def testing (tran-cash-payments mock-roll mock-deal mock-leg-a system-date))
(println testing)

