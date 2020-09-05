(ns claire.domain.account
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let [sql
        (str "create table if not exists "  
             "account " "( "
             "id integer primary key autoincrement, "
             "name text not null unique, "
             "number text not null unique, "
             "statusid integer not null , "
             "memo text not null, "
             "constraint fkstatus "
             "foreign key (statusid) "
             "references status (id) " ");")]
    (db/create-table! sql)))

(defn write [account]
  (db/insert! :account account))

(defn get-all []
  (db/query "select * from account"))

(defn set-db []
  (ensure-table)
  (println "<account> table is set up."))

;;; test
(def wrong
  {:name "Cash 2"
   :number "12345378"
   :statusid 999
   :memo "test second account"})