(ns claire.domain.basis.span
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   span (
   id text primary key,
   months int not null, 
   memo text not null)")

(defn preval []
  [{:id "continuous" :months 0 :memo ".."}
   {:id "month" :months 1 :memo ".."}
   {:id "quarter" :months 3 :memo ".."}
   {:id "semiannual" :months 6 :memo ".."}
   {:id "annual" :months 12 :memo ".."}
   {:id "biannual" :months 24 :memo ".."}
   {:id "none" :months 0 :memo ".."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :span (preval))
  (println "<span> table is set up."))
