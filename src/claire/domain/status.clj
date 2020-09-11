(ns claire.domain.status
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   status (
   id int primary key, 
   name text unique not null, 
   memo text not null)")

(defn preval []
  [{:id 1 :name "active" :memo ".."}
   {:id 2 :name "inactive" :memo ".."}
   {:id 3 :name "pending" :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :status (preval))
  (println "<status> table is set up."))