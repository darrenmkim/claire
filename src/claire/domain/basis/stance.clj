(ns claire.domain.basis.stance
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   stance (
   id smallserial primary key, 
   name text unique not null,     
   memo text not null)")

(defn preval []
  [{:id 1 :name "pay" :memo ".."}
   {:id 2 :name "receive" :memo ".."} 
   {:id 3 :name "buy" :memo ".."}
   {:id 4 :name "sell" :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :stance (preval))
  (println "<stance> table is set up."))