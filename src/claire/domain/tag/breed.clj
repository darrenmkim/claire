(ns claire.domain.tag.breed
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   breed (
   id text primary key,
   name text not null
   )")

(defn preval []
  [{:id "irs" :name "interest rate swap"}
   {:id "crs" :name "currency swap"}
   {:id "cal" :name "call option"}
   {:id "put" :name "put option"}
   {:id "cap" :name "interest rate cap"}
   {:id "cds" :name "credit default swap"}
   {:id "trs" :name "total return swap"}
   {:id "crd" :name "corridor option"}
   {:id "spt" :name "foreign currency spot"}
   {:id "inf" :name "inflation swap"}
   {:id "trl" :name "treasury lock"}
   {:id "rtr" :name "reverse treasury lock"}
   {:id "swt" :name "swaption"}
   {:id "cms" :name "commodity swap"}
   {:id "ftr" :name "future"}
   ])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :breed (preval))
  (println "<breed> table is set up."))
