(ns claire.domain.matrix
  (:require [claire.dock.db :as db]))

(defn set-table! []
  (let [sql
        (str "create table if not exists "
             "matrix " "( "
             "id integer primary key autoincrement, "
             "name text not null unique, "
             "pactid integer not null , "
             "eventid integer not null , "
             "accountid integer not null , "
             "direct integer not null, "
             "memo text not null, "
             "constraint fkstatus "
             "foreign key (statusid) "
             "references status (id) " ");")]
    (db/create-table! sql)))

(defn write [matrix]
  (db/insert! :matrix matrix))

(defn get-all []
  (db/query "select * from matrix"))

(defn set-db! []
  (set-table!)
  (println "<matrix> table is set up."))

;;; test
(def wrong
  {:name "Cash 2"
   :number "12345378"
   :statusid 999
   :memo "test second matrix"})