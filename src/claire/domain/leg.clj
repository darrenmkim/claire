(ns claire.domain.leg
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let [sql
        (str "create table if not exists leg ("
             "id integer primary key autoincrement, "
             "name text not null unique, "
             "dealid integer not null, "
             "pactid integer not null, "
             "stanceid integer not null, "
             "basecur text not null, "
             "localcur text not null, "
             "freqid integer not null, "
             "dayid integer not null, "
             "notional real not null, "
             "rateid integer not null, "
             "givenrate real );")]
    (db/create-table! sql)))

(defn write [leg]
  (db/insert! :leg leg))

(defn get-all []
  (db/query "select * from leg"))

(defn set-db []
  (ensure-table)
  (println "<leg> table is set up."))