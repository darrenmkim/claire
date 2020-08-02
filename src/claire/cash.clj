(ns claire.cash
  (:require claire.date :as d)
  (:import java.util.Date)
  (:import java.util.Calendar))


;; (defn tran-cash-payments [roll deal leg start]
;;   (if (>= start (:target roll))
;;     start
;;     (cons start
;;           (tran-cash-payments roll deal leg (inc start)))))

;; (def leg-p (->leg 1 :irs-fixed :payer :usd :semiannually :dc-30-360 1000000 :libor))
;; (def leg-r (->leg 1 :irs-fixed :receiver :usd :semiannually :dc-30-360 1000000 :libor))
;; (def system-date (t/local-date 2020 3 31))
;; (def target-date (t/local-date 2020 5 31))
;; (def deal-1 (->deal 123 :irs leg-r leg-p  (t/local-date 2020 1 1) (t/local-date 2020 1 3) (t/local-date 2030 1 3) (t/local-date 2030 1 3)))
;; (def roll-1 (->roll 5 (t/local-date 2020 6 3) :project))

;; (def xxx (t/month system-date))

