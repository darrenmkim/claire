(ns claire.domain.ability
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (db/execute!
   "create table if not exists 
    ability (
    id int primary key, 
    code varchar(16) not null, 
    memo varchar(50) not null)"))

(defn get-all []
  (db/query
   (str "select * "
        "from ability")))

(defn get-one-by-id [id]
  (db/query
   (str "select * " 
        "from ability as a "
        "where a.id = " id)))

(defn count-all []
  (db/query
   (str "select count(*) as count "
        "from ability")))

(defn set-preval! []
  (let
   [preset [{:id 1 :code "ADMIN" :memo "Administrator"}
            {:id 2 :code "APPROVER" :memo "Approver"}
            {:id 3 :code "PREPARER" :memo "Preparer"}
            {:id 4 :code "VIEWER" :memo "Viewer"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :ability item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<ability> table is set up."))