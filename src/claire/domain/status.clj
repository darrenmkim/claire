(ns claire.domain.status
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (let
   [sql (str "create table if not exists status ("
             "id integer primary key, "
             "code text not null, "
             "memo text not null);")]
    (db/create-table! sql)))

(defn get-all []
  (db/query
   (str "select * "
        "from status ")))

(defn get-one-by-id [id]
  (db/query
   (str "select * "
        "from status as s "
        "where s.id = " id)))

(defn count-all []
  (db/query
   (str "select count(*) as count "
        "from status ")))

(defn set-preval! []
  (let
   [preset [{:id 1 :code "ACTIVE" :memo "Administrator"}
            {:id 2 :code "INACTIVE" :memo "Approver"}
            {:id 3 :code "PENDING" :memo "Preparer"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :status item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<status> table is set up."))