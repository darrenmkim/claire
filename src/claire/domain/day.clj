(ns claire.domain.day
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (let
   [sql (str "create table if not exists day ("
             "id integer primary key, "
             "code text not null, "
             "memo text not null);")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from day"))

(defn count-all []
  (db/query
   "select count(*) as count from day"))

(defn set-preval! []
  (let
   [preset [{:id 1 :code "30360" :memo ".."}
            {:id 2 :code "AC360" :memo ".."}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :day item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<day> table is set up."))