(ns claire.domain
  (:refer-clojure :exclude [range iterate format max min])
  (:require [clojure.spec.alpha :as s]
            [java-time :as t]
            ))

(def events #{:contract :effect
              :int-receive
              :int-pay
              :int-accrue
              :valuate
              :terminate
              :mature})

(def stances #{:payer :receiver :buyer :seller})

(def freqs {:continuously {:months nil :frac nil}
            :monthly {:months 1 :frac (/ 1.0 12.0)}
            :quarterly {:months 3 :frac (/ 3.0 12.0)}
            :semiannually {:months 6 :frac (/ 6.0 12.0)}
            :annually {:months 12 :frac (/ 12.0 12.0)}
            :biannually {:months 24 :frac (/ 24.0 12.0)}})

(def convetion #{:dc-ac-360 :dc-30-360})

(def rates #{:libor :euribor})

(def currencies #{:usd :eur})

(def breeds #{:irs :crs}) 

(def pacts #{:irs-fixed :irs-float})

(def signs #{:p :n})

(defn date [year month day]
  (t/zoned-date-time year month day))

(def orders #{:calc :post :revert})
(defrecord roll [id order start end])
(defn make-roll [id order start end]
  (->roll id order start end))

(defrecord leg [id name deal-id pact stance curr freq conv notional rate])
(defn make-leg [id name deal-id pact stance curr freq conv notional rate]
  (->leg id name deal-id pact stance curr freq conv notional rate))

(defrecord deal [id name breed trade effect mature terminate])
(defn make-deal [id name breed trade effect mature terminate]
  (->deal id name breed trade effect mature terminate))

(defrecord tran [id date leg-id event contracts amount annote roll-id])
(defn make-tran [id date leg-id event contracts amount annote roll-id]
  (->tran id date leg event contracts amount annote roll-id))

(defrecord account [id name number desc])
(defn make-account [id name number desc]
  (->account id name number desc))

(defrecord preset [id pact event name account-id sign])
(defn make-preset [id pact event name account-id sign]
  (->preset id pact event name account-id sign))
  
(defrecord journal [id tran-id account-id amount roll-id])
(defn make-journal [id tran-id account-id amount roll-id]
  (->journal id tran-id account-id amount roll-id))

(defn find-single [data k id]
  (first (filter #(= (k %) id) data)))

(defn num-by-sign [num sign]
  (case sign :p num :n (- 0 num)))

(defn calc-interest [notional rate frac]
  (* notional (/ rate 100.0) frac))


