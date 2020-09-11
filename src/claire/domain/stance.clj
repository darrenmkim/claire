(ns claire.domain.stance
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   stance (
   id int primary key, 
   name text unique not null,     
   memo text not null)")

(defn preval []
  [{:id 1 :name "payer" :memo ".."}
   {:id 2 :name "receiver" :memo ".."} 
   {:id 3 :name "buyer" :memo ".."}
   {:id 4 :name "seller" :memo ".."}])

(defn set! []
  (db/execute! (schema))
  (db/insert-pre! :stance (preval))
  (println "<stance> table is set up."))