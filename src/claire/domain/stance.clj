(ns claire.domain.stance
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (db/execute!
   "create table if not exists 
    stance (
    id int primary key, 
    code varchar(16) not null)"))

(defn get-all []
  (db/query "select * from stance"))

(defn count-all []
  (db/query
   "select count(*) as count from stance"))

(defn set-preval! []
  (let [preset [{:id 1 :code "payer"}
                {:id 2 :code "receiver"}
                {:id 3 :code "buyer"}
                {:id 4 :code "seller"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset] 
        (db/insert! :stance item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<stance> table is set up."))