(ns claire.cash
  ;;(:exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer [make-roll
                                   make-leg
                                   make-deal]]))

(defn tran-cash-payments [roll deal leg date]
  (if (t/after? date (:mature deal))
    '()
    (cons date
          (tran-cash-payments roll deal leg
                              (t/plus date (t/months 6))))))

(def mock-leg-a (make-leg 1
                          "ABCIRSFIX"
                          :irs-fixed
                          :payer
                          :usd
                          :semiannually
                          :dc-30-360
                          1000000
                          :libor))

(def mock-leg-b (make-leg 2
                          "ABCIRSFLT"
                          :irs-float
                          :receiver
                          :usd
                          :semiannually
                          :dc-30-360
                          1000000
                          :libor))

(def mock-deal (make-deal 123
                          "ABCIRSMOCK"
                          :irs
                          (list mock-leg-a mock-leg-b )
                          (t/zoned-date-time 2020 1 1)
                          (t/zoned-date-time 2020 1 3)
                          (t/zoned-date-time 2025 1 3)
                          (t/zoned-date-time 2025 1 3)))

;; roll 
(def system-date (t/zoned-date-time 2020 5 1))
(def target-date (t/zoned-date-time 2020 5 31))
(def mock-roll (make-roll 1 :calc system-date target-date))



(def testing (tran-cash-payments mock-roll mock-deal mock-leg-a system-date))
(println testing)

