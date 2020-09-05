(ns claire.domain.rate
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let
   [sql (str "create table if not exists rate ("
             "id integer primary key, "
             "code text not null unique, "
             "memo text not null);")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from rate"))

(defn count-all []
  (db/query
   "select count(*) as count from rate"))

(defn ensure-preset []
  (let
   [preset [{:id 1 :code "FIXED" :memo "fixed and not use market rates"}
            {:id 2 :code "LIBOR3M" :memo "3-months libor"}
            {:id 3 :code "EURIBOR" :memo "euribor"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :rate item)))))

(defn set-db []
  (ensure-table)
  (ensure-preset)
  (println "<rate> table is set up."))