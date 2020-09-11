(ns claire.domain.pact
  (:require
   ;[clojure.string :refer [split includes?]]
   [claire.dock.db :as db]
   ;[infix.macros :refer [infix]]
   ))

(defn set-table! []
  (db/execute!
   "create table if not exists 
    pact (
    id serial primary key, 
    breedid integer not null, 
    name text unique not null, 
    initialcash text not null, 
    interimcash text not null, 
    finalcash text not null, 
    memo text not null)"))

(defn get-all []
  (db/query "select * from pact"))

(defn count-all []
  (:count
   (first
    (db/query
     "select count(*) as count from pact"))))

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

(defn set-preval! []
  (when (= (:count (first (count-all)))
           0)
    (doseq [item (preval)]
      (db/insert! :pact item))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<pact> table is set up."))