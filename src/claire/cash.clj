(ns claire.cash
  ;;(:exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer [make-roll
                                   make-leg
                                   make-deal]]))

(defn tran-cash-payments [roll deal leg date]
  (if (t/after? date (:target roll))
    '()
    (cons date
          (tran-cash-payments roll deal leg (t/plus date (t/months 1))))))

(def leg-p (make-leg 1 :irs-fixed :payer :usd :semiannually :dc-30-360 1000000 :libor))

(def leg-r (make-leg 1 :irs-fixed :receiver :usd :semiannually :dc-30-360 1000000 :libor))

(def system-date (t/zoned-date-time 2020 1 1))
(def target-date (t/zoned-date-time 2020 5 31))


(def deal-1 (make-deal 123 :irs leg-r leg-p
                       (t/zoned-date-time 2020 1 1) (t/zoned-date-time 2020 1 3)
                       (t/zoned-date-time 2030 1 3) (t/zoned-date-time 2030 1 3)))

(def roll-1 (make-roll 5 (t/zoned-date-time 2020 6 3) :project))

(def testing (tran-cash-payments roll-1 deal-1 leg-p system-date))
(println testing)

