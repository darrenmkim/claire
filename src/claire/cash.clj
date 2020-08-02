(ns claire.cash
  ;;(:exclude [range iterate format max min])
  (:require [java-time :as t]
            [claire.domain :refer [make-roll
                                   make-leg
                                   make-deal]]
            [claire.date :refer [add-months]]))

;; (defn tran-cash-payments [roll deal leg start]
;;   (if (>=  (:target roll))
;;     start
;;     (cons start
;;           (tran-cash-payments roll deal leg (add-months start 1)))))

(def leg-p (make-leg 1 :irs-fixed :payer :usd :semiannually :dc-30-360 1000000 :libor))

(def leg-r (make-leg 1 :irs-fixed :receiver :usd :semiannually :dc-30-360 1000000 :libor))

(def system-date (t/local-date 2020 3 31))

(def target-date (t/local-date 2020 5 31))

(def deal-1 (make-deal 123 :irs leg-r leg-p
                       (t/local-date 2020 1 1) (t/local-date 2020 1 3)
                       (t/local-date 2030 1 3) (t/local-date 2030 1 3)))

(def roll-1 (make-roll 5 (t/local-date 2020 6 3) :project))

(def testing (tran-cash-payments roll-1 deal-1 leg-p system-date))
(println testing)

