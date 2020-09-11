(ns claire.domain.event
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   event (
   id int primary key, 
   code text not null, 
   memo text not null)")

(defn preval []
  [{:id 1 :code "trade" :memo ".."}
   {:id 2 :code "effect" :memo ".."}
   {:id 3 :code "pay" :memo ".."}
   {:id 4 :code "receive" :memo ".."}
   {:id 5 :code "accrue" :memo ".."}
   {:id 6 :code "valuate" :memo ".."}
   {:id 7 :code "terminate" :memo ".."}
   {:id 8 :code "mature" :memo ".."}])

(defn set! []
  (db/execute! (schema))
  (db/insert-pre! :event (preval))
  (println "<event> table is set up."))