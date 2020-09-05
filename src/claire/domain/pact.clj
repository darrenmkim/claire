(ns claire.domain.pact
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let
   [sql (str "create table if not exists pact ("
             "id integer primary key, "
             "code text not null, "
             "memo text not null);")]
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
   [preset [{:id 1 :code "IRSFIX" :memo "Fixed Leg of Interest Rate Swap"}
            {:id 2 :code "IRSFLT" :memo "Float Leg of Interest Rate Swap"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :pact item)))))

(defn set-db []
  (ensure-table)
  (ensure-preset)
  (println "<pact> table is set up."))