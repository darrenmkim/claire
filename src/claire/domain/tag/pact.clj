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
   "upfrontcost text, "
   "interestpayments text, "
   "principalpayment text, "
   "memo text )"))

(defn preval []
  [   
   {:id "irsfix" 
    :breedid "irs"
    :upfrontcost nil
    :interestpayments "(notionalinlocalcur * givenrate * (months / 12))"
    :principalpayment nil
    :memo "Fixed Leg of Interest Rate Swap"}
   
   {:id "irsflt" 
    :breedid "irs"
    :upfrontcost nil
    :interestpayments "(notionalinlocalcur * quotedrate * (months / 12))"
    :principalpayment nil
    :memo "Float Leg of Interest Rate Swap"}
    
   {:id "crsfix"
    :breedid "crs"
    :upfrontcost nil
    :interestpayments "(notionalinlocalcur * givenrate * (months / 12))"
    :principalpayment "notionalinlocalcur"
    :memo "Fixed Leg of Interest Rate Swap"}
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
