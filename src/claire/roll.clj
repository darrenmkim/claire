(ns claire.roll
  (:require
   [claire.db :as db]))

(def rollcreatesql
  (str "create table if not exists roll ("
       "id integer primary key autoincrement, "
       "ordertime text not null, "
       "systemstart text not null, "
       "systemend text not null, "
       "personid integer not null );"))

(db/createtable rollcreatesql)

(db/insert! :roll {:ordertime "2020-09-03 14:22:12.123"
                   :systemstart "2020-09-03 14:22:12.123"
                   :systemend "2020-09-03 14:22:12.123"
                   :personid 1})

(db/query "select * from roll as r")