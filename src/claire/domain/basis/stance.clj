(ns claire.domain.basis.stance
  (:require [claire.center.db :as db]))

(defn schema []
  (str "create table if not exists "
       "stance ("
       "id text primary key, "
       "memo text not null)"))

(defn preval []
  [{:id "pay" :memo
    "entity takes pay side of mutual contract."}
   {:id "receiv" :memo
    "entity takes receive side of mutial contract."} 
   {:id "buy" :memo
    "entity buys a contingent contract."}
   {:id "sell" :memo
    "entity sells a contingent contract."}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :stance (preval))
  (println "<stance> table is set up."))
