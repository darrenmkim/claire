(ns claire.domain.basis.status
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   status (
   id smallserial primary key, 
   name text unique not null, 
   memo text not null)
   ")

(defn preval []
  [{:id 1 :name "posted" :memo ".."}
   {:id 2 :name "drafted" :memo ".."}
   {:id 3 :name "deleted" :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :status (preval))
  (println "<status> table is set up."))