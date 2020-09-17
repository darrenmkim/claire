(ns claire.domain.tag.breed
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   breed (
   id serial primary key,
   name text unique not null,
   memo text not null
   )")

(defn preval []
  [{:name "irs" :memo "interest rate swap"}
   {:name "crs" :memo "currency swap"}
   {:name "ftr" :memo "future"}
   {:name "cal" :memo "call option"}
   {:name "put" :memo "put option"}
   {:name "cap" :memo "interest rate cap"}
   {:name "cds" :memo "credit default swap"}
   {:name "trs" :memo "total return swap"}
   {:name "crd" :memo "corridor option"}
   {:name "spt" :memo "foreign currency spot"}
   {:name "inf" :memo "inflation swap"}
   {:name "trl" :memo "treasury lock"}
   {:name "rtr" :memo "reverse treasury lock"}
   {:name "swt" :memo "swaption"}
   {:name "cms" :memo "commodity swap"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :breed (preval))
  (println "<breed> table is set up."))