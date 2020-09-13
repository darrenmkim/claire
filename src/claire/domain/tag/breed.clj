(ns claire.domain.tag.breed
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   breed (
   id serial primary key,
   name text unique not null,
   memo text not null)")

(defn preval []
  [{:name "irs" :memo "Interest Rate Swap"}
   {:name "crs" :memo "Currency Swap"}
   {:name "ftr" :memo "Future"}
   {:name "cal" :memo "Call Option"}
   {:name "put" :memo "Put Option"}
   {:name "cap" :memo "Interest Rate Cap"}
   {:name "cds" :memo "Credit Default Swap"}
   {:name "trs" :memo "Total Return Swap"}
   {:name "crd" :memo "Corridor Option"}
   {:name "spt" :memo "Foreign Currency Spot"}
   {:name "inf" :memo "Inflation Swap"}
   {:name "trl" :memo "Treasury Lock"}
   {:name "rtr" :memo "Reverse Treasury Lock"}
   {:name "swt" :memo "Swaption"}
   {:name "cms" :memo "Commodity Swap"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :breed (preval))
  (println "<breed> table is set up."))