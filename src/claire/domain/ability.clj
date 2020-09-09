(ns claire.domain.ability
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (let
   [sql (str "create table if not exists ability ("
             "id integer primary key, "
             "code text not null, "
             "memo text not null);")]
    (db/create-table! sql)))

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

(defn ensure-preset []
  (let
   [preset [{:id 131 :code "ADMIN" :memo "Administrator"}
            {:id 231 :code "APPROVER" :memo "Approver"}
            {:id 331 :code "PREPARER" :memo "Preparer"}
            {:id 431 :code "VIEWER" :memo "Viewer"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :ability item)))))

(defn set-db! []
  (set-table!)
  (ensure-preset)
  (println "<ability> table is set up."))