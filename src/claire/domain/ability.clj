(ns claire.domain.ability
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
    ability (
    id int primary key, 
    name text unique not null, 
    memo text not null)")

(defn preval []
  [{:id 1 :name "admin" :memo ".."}
   {:id 2 :name "approver" :memo ".."}
   {:id 3 :name "preparer" :memo ".."}
   {:id 4 :name "viewer" :memo ".."}])

(defn set! []
  (db/execute! (schema))
  (db/insert-pre! :ability (preval))
  (println "<ability> table is set up."))