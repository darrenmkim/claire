(ns claire.domain.basis.dayconv
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   dayconv (
   id text primary key, 
   memo text not null)")

(defn preval []
  [{:id "30360" :memo ".."}
   {:id "AC360" :memo ".."}]) 

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :dayconv (preval))
  (println "<dayconv> table is set up."))
