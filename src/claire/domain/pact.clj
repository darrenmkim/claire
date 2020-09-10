(ns claire.domain.pact
  (:require
   [clojure.string :refer [split includes?]]
   [claire.adapt.db :as db]
   [infix.macros :refer [infix]]))

(defn set-table! []
  (let
   [sql (str "create table if not exists pact ("
             "id integer primary key, "
             "breedid integer not null, "
             "code text not null, "
             "name text not null, "
             "initialcash text not null, "
             "interimcash text not null, "
             "finalcash text not null, "
             "memo text not null "
             ");")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from pact"))

(defn count-all []
  (:count
   (first
    (db/query
     "select count(*) as count from pact"))))

(defn set-preval! []
  (let
   [preset [{:id 1
             :breedid 1
             :code "IRSFIX"
             :name "IR Swap Fixed"
             :initialcash "()"
             :interimcash
             "(defn guesswhat 
              [notional givenrate yearfrac] 
              (* notional givenrate yearfrac))"
             :finalcash "()"
             :memo "Fixed Leg of Interest Rate Swap"}
            {:id 2
             :breedid 1
             :code "IRSFLT"
             :name "IR Swap Float"
             :initialcash "()"
             :interimcash
             "(defn guesswhat 
              [notional quotedrate yearfrac] 
              (* notional quotedrate yearfrac))"
             :finalcash "()"
             :memo "Float Leg of Interest Rate Swap"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :pact item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<pact> table is set up."))