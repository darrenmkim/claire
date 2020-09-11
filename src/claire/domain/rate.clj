(ns claire.domain.rate
  (:require [claire.dock.db :as db]))

(defn set-table! []
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

(defn set-preval! []
  (let
   [preset [{:id 1 :code "FIXED" :memo "fixed and not use market rates"}
            {:id 2 :code "LIBOR3M" :memo "3-months libor"}
            {:id 3 :code "EURIBOR" :memo "euribor"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :rate item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<rate> table is set up."))