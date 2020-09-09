(ns claire.domain.event
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (let
   [sql (str "create table if not exists event ("
             "id integer primary key, "
             "code text not null, "
             "memo text not null);")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from event"))

(defn count-all []
  (db/query
   "select count(*) as count from event"))

(defn set-preval! []
  (let
   [preset [{:id 13 :code "TRADE" :memo ".."}
            {:id 23 :code "EFFECT" :memo ".."}
            {:id 33 :code "PAY" :memo ".."}
            {:id 43 :code "RECEIVE" :memo ".."}
            {:id 53 :code "ACCRUE" :memo ".."}
            {:id 63 :code "VALUATE" :memo ".."}
            {:id 73 :code "TERMINATE" :memo ".."}
            {:id 83 :code "MATURE" :memo ".."}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :event item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<event> table is set up."))