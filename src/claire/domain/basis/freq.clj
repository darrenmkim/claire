(ns claire.domain.basis.freq
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   freq (
   id smallserial primary key, 
   name text not null, 
   months int not null, 
   memo text not null)")

(defn preval []
  [{:id 1 :name "continuously" :months 0 :memo ".."}
   {:id 2 :name "monthly" :months 1 :memo ".."}
   {:id 3 :name "quarterly" :months 3 :memo ".."}
   {:id 4 :name "semiannually" :months 6 :memo ".."}
   {:id 5 :name "annually" :months 12 :memo ".."}
   {:id 6 :name "biannually" :months 24 :memo ".."}
   {:id 7 :name "none" :months 0 :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :freq (preval))
  (println "<freq> table is set up."))