(ns claire.domain)

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

(def freqs #{:monthly
             :quarterly
             :semiannually
             :annually
             :biannually})

(def convetion #{:dc-ac-360
                 :dc-30-360})

(def rates #{:libor
             :euribor})

(def currencies #{:usd
                  :eur})

(def deal-types #{:irs
                  :crs}) 

(def leg-types #{:irs-fixed
                 :irs-float})

;;; roll 
(def orders #{:calc :post :revert})
(defrecord roll [id order start end])
(defn make-roll [id order start end]
  (->roll id order start end))

;; deal
(defrecord leg [id name kind stance curr freq conv notio rate])
(defn make-leg [id name kind stance curr freq conv notio rate]
  (->leg id name kind stance curr freq conv notio rate))
(defrecord deal [id name kind legs trade effect mature terminate])
(defn make-deal [id name kind legs trade effect mature terminate]
  (->deal id name kind legs trade effect mature terminate))

;; tran 
(defrecord tran [id date leg event contracts amount annote roll journal])
(defn make-tran [id date leg event contracts amount annote roll journal]
  (->tran id date leg event contracts amount annote roll journal))




(defrecord chart [id name activity account doc])

(defrecord account [id name number desc])

(defrecord journal [id tran account amount roll])
