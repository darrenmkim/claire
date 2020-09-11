(ns claire.domain.pact
  (:require
   ;[clojure.string :refer [split includes?]]
   [claire.dock.db :as db]
   ;[infix.macros :refer [infix]]
   ))

(defn schema []
  "create table if not exists 
   pact (
   id serial primary key,
   breedid int not null,   
   name text unique not null, 
   initialcash text not null, 
   interimcash text not null, 
   finalcash text not null, 
   memo text not null, 
   constraint fk_breed
   foreign key (breedid)
   references breed(id))")

(defn preval []
  [{:id 1 :breedid 1 :name "IRSFIX"
    :initialcash "()"
    :interimcash
    "(defn guesswhat
     [notional givenrate yearfrac]
     (* notional givenrate yearfrac))"
    :finalcash "()"
    :memo "Fixed Leg of Interest Rate Swap"}
   {:id 2 :breedid 1 :name "IRSFLT"
    :initialcash "()"
    :interimcash
    "(defn guesswhat 
     [notional quotedrate yearfrac] 
     (* notional quotedrate yearfrac))"
    :finalcash "()"
    :memo "Float Leg of Interest Rate Swap"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :pact (preval))
  (println "<pact> table is set up."))