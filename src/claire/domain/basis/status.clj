(ns claire.domain.basis.status
  (:require [claire.center.db :as db]))

(defn schema []
  (str
   "create table if not exists "
   "status ("
   "id text primary key, "
   "memo text not null)"))

(defn preval []
  [{:id "posted" :memo ".."}
   {:id "drafted" :memo ".."}
   {:id "deleted" :memo ".."}
   {:id "activated" :memo ".."}
   {:id "deactivated" :memo ".."}])
   
(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :status (preval))
  (println "<status> table is set up."))
