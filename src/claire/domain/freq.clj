(ns claire.domain.freq
  (:require [claire.adapt.db :as db]))

(defn set-table! []
  (let
   [sql (str "create table if not exists freq ("
             "id integer primary key, "
             "code text not null, "
             "months integer not null, "
             "yearfrac read not null  "
             ");")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from freq"))

(defn count-all []
  (db/query
   "select count(*) as count from freq"))

(defn set-preval! []
  (let [preset [{:id 1 :code "continuously" :months 0 :yearfrac 999.0}
                {:id 2 :code "monthly" :months 1 :yearfrac 0.083333333 }
                {:id 3 :code "quarterly" :months 3 :yearfrac 0.25}
                {:id 4 :code "semiannually" :months 6 :yearfrac 0.5}
                {:id 5 :code "annually" :months 12 :yearfrac 1.0} 
                {:id 6 :code "biannually" :months 24 :yearfrac 2.0}
                {:id 7 :code "none" :months 0 :yearfrac 999.0}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset]
        (db/insert! :freq item)))))

(defn set-db! []
  (set-table!)
  (set-preval!)
  (println "<freq> table is set up."))





















;let FREQS = [
;    { Id = 1 ; Code = "CONTINUOUSLY" ; Months = 0 ; Memo = ".." } ;
;    { Id = 2 ; Code = "MONTHLY" ; Months = 1 ; Memo = ".." } ;
;    { Id = 3 ; Code = "QUARTERLY" ; Months = 3 ;Memo = ".." } ; 
;    { Id = 4 ; Code = "SEMIANNUALLY" ; Months = 6 ;Memo = ".." } ;
;    { Id = 5 ; Code = "ANNUALLY" ; Months = 12 ;Memo = ".." } ; 
;    { Id = 6 ; Code = "BIANNUALLY" ; Months = 24 ;Memo = ".." } ; 
;    { Id = 7 ; Code = "NONE" ; Months = 0 ;Memo = ".." } ]