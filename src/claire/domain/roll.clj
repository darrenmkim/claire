(ns claire.domain.roll
  (:require
   [claire.adapt.db :as db]))

(defn set-table! []
  (let [sql
        (str "create table if not exists roll ("
             "id integer primary key autoincrement, "
             "ordertime text not null, "
             "systemstart text not null, "
             "systemend text not null, "
             "personid integer not null );")]
    (db/create-table! sql)))

(defn write [roll]
  (db/insert! :roll roll))

(defn get-all []
  (db/query "select * from roll"))

(defn set-db! []
  (set-table!)
  (println "<roll> table is set up."))


;;; test
(def sample 
  {:ordertime "2020-09-03"
   :systemstart "2020-09-03"
   :systemend "2020-09-03"
   :personid 5})