(ns claire.domain.tag.pact
  (:require
   ;[clojure.string :refer [split includes?]]
   [claire.center.db :as db]
   ;[infix.macros :refer [infix]]
   ))

(defn schema []
  (str
   "create table if not exists "
   "pact ("
   "id text primary key, "
   "breedid text references breed(id) not null, "
   
   "notionaltiming text, "
   "notionalcalc text, "

   "costtiming text, "
   "costcalc text, "

   "feetiming text, "
   "fee text, "

   "interesttiming text, "
   "interest text, "

   "gainlosstiming text, "
   "gainloss text, "

   "principaltiming text, "
   "principal text, "

   
   "memo text)"))





(defn preval []
  [   
   {:id "irsfix" 
    :breedid "irs"
    :notionaltiming "tradedate"
    :notionalcalc "notionallocal" 
    :costtiming nil
    :cost nil 
    :feetiming nil 
    :fee nil
    :interesttiming "span"
    :interestcalc  "(* notionalinlocalcur givenrate (/ months 12))"
    :gainlosstiming nil
    :gainlosscalc nil 
    :principaltiming nil   
    :principalcalc nil
    :memo "Fixed Leg of Interest Rate Swap"}
   
   {:id "irsflt" 
    :breedid "irs"

    :notional "notionallocal"

    
    :upfrontcost nil
    :interestpayments "(* notionalinlocalcur quotedrate (/ months 12))"
    :principalpayment nil



    
    :memo "Float Leg of Interest Rate Swap"}









   
   {:id "crsfix"
    :breedid "crs"

    :notional "notionallocal"

    
    :upfrontcost nil
    :interestpayments "(* notionalinlocalcur givenrate (/ months 12))"
    :principalpayment "notionalinlocalcur"
    :memo "Fixed Leg of Interest Rate Swap"}
   ])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :pact (preval))
  (println "<pact> table is set up."))
