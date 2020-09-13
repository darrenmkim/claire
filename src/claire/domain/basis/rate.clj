(ns claire.domain.basis.rate
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists
   rate (
   id smallserial primary key,
   name text unique not null,
   memo text not null)")

(defn preval []
  [{:id 1 :name "none" :memo "fixed and not use market rates"}
   {:id 2 :name "libor3m" :memo "3-months libor"}
   {:id 3 :name "euribor3m" :memo "euribor"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :rate (preval))
  (println "<rate> table is set up."))