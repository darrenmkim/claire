
(defrecord pact
    [id
     name 
     intermedyiary
     initialpay?
     interest?
     finalpay?
     foreign? 
     amortize?])

(defn make-pact [id name intermediary initialpay? interest? finalpay? foreign? amortize?]
  (->pact id name intermediary initialpay? interest? finalpay? foreign? amortize?))

(def pacts
  [(make-pact 1 :irs-fixed :otc false true false false false)])
            
(defrecord deal
    [id
     name
     breed
     trade
     effective
     mature
     terminate])

(defrecord leg
    [id
     name
     dealid
     pactid
     intermediary
     initialpay?
     interest?
     finalpayment?
     foreign?
     amortize?
     stance
     basecur
     localcur
     payfreq
     dayconv
     notional
     ratecode
     ratefixed])


;;(defn make-leg [name dealid pactid stance basecur localcur payfreq dayconv notional ratecode ratefixed]
;;(let [pact (find )
                

(def ftrbond (->pact
              7 "Futures Bond Leg"
              :exch true true true false false 
                     
              ))

(def crsfloat (>pact
               5 "Currency Swap Float Leg"
               :otc false true true false true 
                   
               ))
