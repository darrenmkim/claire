(ns claire.domain.person
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let [sql
        (str "create table if not exists person ("
             "id integer primary key autoincrement, "
             "firstname text not null, "
             "lastname text not null, "
             "email text not null unique, "
             "phone text not null );")]
    (db/create-table! sql)))

(defn write [person]
  (db/insert! :person person))

(defn get-all []
  (db/query "select * from person"))

(defn set-db []
  (ensure-table)
  (println "<person> table is set up."))

;;; test
(def sample 
  {:firstname "Dar"
   :lastname "Ki"
   :email "daaaaakim@dsfl.com"
   :phone "3412-343"})