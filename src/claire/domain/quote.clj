
(ns claire.domain.quote
  (:require [claire.dock.db :as db]))

(defn set-table! []
  (let [sql
        (str "create table if not exists quote ("
             "id integer primary key autoincrement, "
             "date text not null, "
             "rateid integer not null, "
             "value real not null, "
             "foreign key (rateid) references rate (id) "
             ");")]
    (db/create-table! sql)))

(defn write [quote]
  (db/insert! :quote quote))

(defn get-all []
  (db/query "select * from quote"))

(defn set-db! []
  (set-table!)
  (println "<quote> table is set up."))

;;; test
(def sample
  {:date "03-04-2020"
   :rateid 1
   :value 100.23})

(def wrong
  {:date "03-04-2020"
   :rateid 5
   :value 101.23})