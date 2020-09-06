(ns claire.domain.pact
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
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

(defn ensure-preset []
  (let
   [preset [{:id 1
             :breedid 1
             :code "IRSFIX"
             :name "IR Swap Fixed"
             :initialcash "none"
             :interimcash "notional*givenrate*(freqmonths/12)"
             :finalcash "none"
             :memo "Fixed Leg of Interest Rate Swap"}
            {:id 2
             :breedid 1
             :code "IRSFLT"
             :name "IR Swap Float"
             :initialcash "none"
             :interimcash "notional*quote*(freqmonths/12)"
             :finalcash "none"
             :memo "Float Leg of Interest Rate Swap"}
            {:id 3
             :breedid 3
             :code "FXSPT"
             :name "FX Spot"
             :initialcash "none"
             :interimcash "none"
             :finalcash "notional*givenrate*(terminatedate-effectdate)/360"
             :memo "FX Spot"}
            {:id 4
             :breedid 3
             :code "FXFWD"
             :name "FX Forward"
             :initialcash "none"
             :interimcash "none"
             :finalcash "notional*givenrate*(terminatedate-effectdate)/360"
             :memo "FX Forward"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :pact item)))))

(defn set-db []
  (ensure-table)
  (ensure-preset)
  (println "<pact> table is set up."))