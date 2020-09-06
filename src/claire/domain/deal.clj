(ns claire.domain.deal
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let [sql
        (str "create table if not exists deal ("
             "id integer primary key autoincrement, "
             "name text not null unique, "
             "tradedate text not null, "
             "effectdate text not null, "
             "terminatedate text not null, "
             "maturedate text not null "
             ");")]
    (db/create-table! sql)))

(defn write [deal]
  (db/insert! :deal deal))

(defn get-all []
  (db/query "select * from deal"))

(defn set-db []
  (ensure-table)
  (println "<deal> table is set up."))

