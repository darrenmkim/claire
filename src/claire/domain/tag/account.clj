(ns claire.domain.tag.account
  (:require [claire.center.db :as db]))

(defn set-table! []
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

(defn set-db! []
  (set-table!)
  (println "<account> table is set up."))

;;; test
(def wrong
  {:name "Cash 2"
   :number "12345378"
   :statusid 999
   :memo "test second account"})