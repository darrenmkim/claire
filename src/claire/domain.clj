(ns claire.domain
  (:refer-clojure :exclude [range iterate format max min])
  (:require
   ;; [clojure.spec.alpha :as s]
   [java-time :as t]))

;;; DATE ;;;

(defn make-date [year month day]
  (t/zoned-date-time year month day))

;;; DEAL ;;;

(def breeds #{:irs :crs :fxf :cll
              }) 
(def pacts #{:irs-fixed :irs-float
             :crs-mutual
             :fxf-side
             :cll-sell :cll-buy
             })


(defrecord leg [id name deal-id pact stance base-cur local-cur freq conv notional rate-c fixed-r])
(defn make-leg [id name deal-id pact stance base-cur local-cur freq conv notional rate-c fixed-r]
  (->leg id name deal-id pact stance base-cur local-cur freq conv notional rate-c fixed-r))

(defrecord deal [id name breed trade effect mature terminate])
(defn make-deal [id name breed trade effect mature terminate]
  (->deal id name breed trade effect mature terminate))

;;; RATE ;;;

(def convetion #{:dc-ac-360 :dc-30-360})
(def rate-codes #{:libor :euribor})
(def currencies #{:usd :eur})
(def signs #{:p :n})
(def freqs {:continuously {:months nil :frac nil}
            :monthly {:months 1 :frac (/ 1.0 12.0)}
            :quarterly {:months 3 :frac (/ 3.0 12.0)}
            :semiannually {:months 6 :frac (/ 6.0 12.0)}
            :annually {:months 12 :frac (/ 12.0 12.0)}
            :biannually {:months 24 :frac (/ 24.0 12.0)}})

(defrecord rate [id date code percent])
(defn make-rate [id date code percent]
  (->rate id date code percent))

;;; ACCOUNT ;;;

(defrecord account [id name number desc])
(defn make-account [id name number desc]
  (->account id name number desc))

(defrecord preset [id pact event name account-id sign])
(defn make-preset [id pact event name account-id sign]
  (->preset id pact event name account-id sign))

(defn num-by-sign [num sign]
  (case sign :p num :n (- 0 num)))

;;; TRAN & JOURNAL ;;;

(def events #{:contract :effect :int-receive
              :int-pay :int-accrue :valuate
              :terminate :mature})
(def stance-to-event
  {:payer :int-pay
   :receiver :int-receive})

(defrecord tran [id date leg-id event contracts base-amt local-amt annote roll-id])
(defn make-tran [id date leg-id event contracts base-amt local-amt annote roll-id]
  (->tran id date leg event contracts base-amt local-amt annote roll-id))
  
(defrecord journal [id tran-id account-id amount roll-id])
(defn make-journal [id tran-id account-id amount roll-id]
  (->journal id tran-id account-id amount roll-id))

;;; ROLL ;;;

(def orders #{:calc :post :revert})
(defrecord roll [id order start end])
(defn make-roll [id order start end]
  (->roll id order start end))
