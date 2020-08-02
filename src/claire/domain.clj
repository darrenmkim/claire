(ns claire.domain)

;; (def roll-types #{:project
;;                   :revert
;;                   :post})

;; (def events #{:contract
;;               :effect
;;               :receive
;;               :pay
;;               :accrue
;;               :valuate
;;               :terminate
;;               :mature})

;; (def stances #{:payer
;;                :receiver
;;                :buyer
;;                :seller})

;; (def freqs #{:monthly
;;              :quarterly
;;              :semiannually
;;              :annually
;;              :biannually})

;; (def convetion #{:dc-ac-360
;;                  :dc-30-360})

;; (def rates #{:libor
;;              :euribor})

;; (def currencies #{:usd
;;                   :eur})

;; (def deal-types #{:irs
;;                   :crs}) 

;; (def leg-types #{:irs-fixed
;;                  :irs-float})

;; (defrecord roll [id target status])

;; (defrecord deal [id type rleg pleg traded effective mature terminate])

;; (defrecord leg [id type stance currency freq conv notional rate])

;; (defrecord tran [id date leg event contracts amount annote roll])

;; (defrecord chart [id name activity account doc])

;; (defrecord account [id name number desc])

;; (defrecord journal [id tran account amount roll])
