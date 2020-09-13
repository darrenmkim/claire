(ns claire.domain.tag.pact
  (:require
   ;[clojure.string :refer [split includes?]]
   [claire.dock.db :as db]
   ;[infix.macros :refer [infix]]
   ))

(defn schema []
  "create table if not exists 
   pact (
   id serial primary key,
   name text unique not null, 
   breedid integer not null,   
   initialcash text not null, 
   interimcash text not null, 
   finalcash text not null, 
   memo text not null, 
   constraint fk_breed
   foreign key (breedid)
   references breed(id))")

(defn preval []
  [{:id 1 :name "irsfix" :breedid 1
    :initialcash "()"
    :interimcash "(* notional givenrate yearfrac)"
    :finalcash "()"
    :memo "Fixed Leg of Interest Rate Swap"}
   {:id 2 :name "irsflt" :breedid 1
    :initialcash "()"
    :interimcash "(* notional quotedrate yearfrac)"
    :finalcash "()"
    :memo "Float Leg of Interest Rate Swap"}])

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

