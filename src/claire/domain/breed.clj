
(ns claire.domain.breed
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let
   [sql (str "create table if not exists breed ("
             "id integer primary key, "
             "code text not null, "
             "memo text not null);")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from breed"))

(defn count-all []
  (:count
   (first
    (db/query
     "select count(*) as count from breed"))))

(defn ensure-preset []
  (let 
   [preset [{:id 1 :code "IRS" :memo "Interest Rate Swap"}
            {:id 2 :code "CRS" :memo "Currency Swap"}
            {:id 3 :code "FTR" :memo "Future"}
            {:id 4 :code "CAL" :memo "Call Option"}
            {:id 5 :code "PUT" :memo "Put Option"}
            {:id 6 :code "CAP" :memo "Interest Rate Cap"}
            {:id 7 :code "CDS" :memo "Credit Default Swap"}
            {:id 8 :code "TRS" :memo "Total Return Swap"}
            {:id 9 :code "CRD" :memo "Corr:idor Option"}
            {:id 10 :code "SPT" :memo "Foreign Currency Spot"}
            {:id 11 :code "INF" :memo "Inflation Swap"}
            {:id 12 :code "TRL" :memo "Treasury Lock"}
            {:id 13 :code "RTR" :memo "Reverse Treasury Lock"}
            {:id 14 :code "SWT" :memo "Swaption"}
            {:id 15 :code "CMS" :memo "Commodity Swap"}]]
    (if (>= (count-all)
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :breed item)))))

(defn set-db []
  (ensure-table)
  (ensure-preset)
  (println "<breed> table is set up."))


