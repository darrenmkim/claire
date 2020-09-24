(ns claire.domain.basis.ability
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists
   ability (
   id text primary key,
   memo text not null)")

(defn preval []
  [{:id "admin" :memo "can do everything."}
   {:id "approver" :memo "can approve."}
   {:id "preparer" :memo "can prepare."}
   {:id "viewer" :memo "can view."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :ability (preval))
  (println "<ability> table is set up."))
