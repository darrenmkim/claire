(ns claire.domain.basis.event
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   event (
   id text primary key,  
   memo text not null)")

(defn preval []
  [{:id "contract" :memo ".."}
   {:id "effect" :memo ".."}
   {:id "pay" :memo ".."}
   {:id "receive" :memo ".."}
   {:id "accrue" :memo ".."}
   {:id "valuate" :memo ".."}
   {:id "reduce" :memo ".."}
   {:id "terminate" :memo ".."}
   {:id "mature" :memo ".."}
   {:id "fee" :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :event (preval))
  (println "<event> table is set up."))
