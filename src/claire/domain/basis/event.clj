(ns claire.domain.basis.event
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   event (
   id smallserial primary key, 
   code text not null, 
   memo text not null)")

(defn preval []
  [{:id 1 :code "contract" :memo ".."}
   {:id 2 :code "effect" :memo ".."}
   {:id 3 :code "pay" :memo ".."}
   {:id 4 :code "receive" :memo ".."}
   {:id 5 :code "accrue" :memo ".."}
   {:id 6 :code "valuate" :memo ".."}
   {:id 7 :code "reduce" :memo ".."}
   {:id 8 :code "terminate" :memo ".."}
   {:id 9 :code "mature" :memo ".."}
   {:id 10 :code "fee" :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :event (preval))
  (println "<event> table is set up."))