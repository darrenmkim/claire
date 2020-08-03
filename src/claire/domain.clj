(ns claire.domain
  (:require [clojure.spec.alpha :as s]))

(def roll-types #{:project
                  :revert
                  :post})

(def events #{:contract
              :effect
              :receive
              :pay
              :accrue
              :valuate
              :terminate
              :mature})

(def stances #{:payer
               :receiver
               :buyer
               :seller})

(def freqs {:continuously {:months nil :frac nil}
            :monthly {:months 1 :frac (/ 1.0 12.0)}
            :quarterly {:months 3 :frac (/ 3.0 12.0)}
            :semiannually {:months 6 :frac (/ 6.0 12.0)}
            :annually {:months 12 :frac (/ 12.0 12.0)}
            :biannually {:months 24 :frac (/ 24.0 12.0)}})

(def convetion #{:dc-ac-360
                 :dc-30-360})

(def rates #{:libor
             :euribor})

(def currencies #{:usd
                  :eur})

(def deal-kinds #{:irs
                  :crs}) 

(def leg-kinds #{:irs-fixed
                 :irs-float})

;;; roll 
(def orders #{:calc :post :revert})
(defrecord roll [id order start end])
(defn make-roll [id order start end]
  (->roll id order start end))

;; deal
(defrecord leg [id name deal kind stance curr freq conv notional rate])
(defn make-leg [id name deal kind stance curr freq conv notional rate]
  (->leg id name deal kind stance curr freq conv notional rate))

(defrecord deal [id name kind trade effect mature terminate])
(defn make-deal [id name kind trade effect mature terminate]
  (->deal id name kind trade effect mature terminate))

;; tran 
(defrecord tran [id date leg event contracts amount annote roll journal])
(defn make-tran [id date leg event contracts amount annote roll journal]
  (->tran id date leg event contracts amount annote roll journal))








(defrecord chart [id name activity account doc])

(defrecord account [id name number desc])

(defrecord journal [id tran account amount roll])













;;;; proof of concept


;; definition using spec
(s/def :order/id int?)
(s/def :order/status #{:received :delivered :cancelled})
(s/def :pizza/kind #{:plain :pepperoni :hawaiian})
(s/def ::pizza-order (s/keys :req [:order/id :order/status :pizza/kind]))
;; test 
(def my-pizza-order
  {:order/id 1234
   :order/status :delivered
   :pizza/kind :hawaiian})
(type my-pizza-order)
