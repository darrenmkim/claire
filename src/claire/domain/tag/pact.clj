(ns claire.domain.tag.pact
  (:require
   ;[clojure.string :refer [split includes?]]
   [claire.center.db :as db]
   ;[infix.macros :refer [infix]]
   ))

(defn schema []
  "create table if not exists 
   pact (
   id text primary key, 
   breedid integer 
   references breed (id) not null,
   upfrontcost text not null, 
   interestpayment text not null, 
   principalpayment text not null, 
   memo text not null
   )")

(defn preval []
  [
   
   {:id "irsfix" 
    :breedid "irs"
    :upfrontcost "()"
    :interestpayment "(notional * givenrate * yearfrac)"
    :principalpayment "()"
    :memo "Fixed Leg of Interest Rate Swap"}
   
   {:id "irsflt" 
    :breedid "irs"
    :upfrontcost "()"
    :interestpayment "(notional * quotedrate * yearfrac)"
    :principalpayment "()"
    :memo "Float Leg of Interest Rate Swap"}





   



   
   {:id "crsfix" 
    :breedid "crs"
    :upfrontcost ""
    :interestpayment "(notional * givenrate * (months / 12))"

    :principalpayment "(notional * 1)"
    :memo "Fixed Leg of Interest Rate Swap"}



   








   
   {:id "crsflt" 
    :breedid "crs"
    :upfrontcost "()"
    :interestpayment "(notional * quotedrate * yearfrac)"
    :principalpayment "()"
    :memo "Float Leg of Interest Rate Swap"}

   
   {:id "calfix" 
    :breedid "cal"
    :upfrontcost "()"
    :interestpayment "(notional * givenrate * yearfrac)"
    :principalpayment "()"
    :memo "Fixed Leg of Interest Rate Swap"}
   
   {:id "crsflt" 
    :breedid "cal"
    :upfrontcost "()"
    :interestpayment "(notional * quotedrate * yearfrac)"
    :principalpayment "()"
    :memo "Float Leg of Interest Rate Swap"}



   

   ])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :pact (preval))
  (println "<pact> table is set up."))

;;;; testing
(db/query
 "select 
  p.id as pactid, 
  p.name as pactname, 
  b.id as breedid, 
  b.name as breedname
  from pact as p 
  left join breed as b 
  on p.breedid = b.id")
