(ns claire.person
  (:require [claire.db :as db]))

(defn ensure-person-table []
  (let [sql
        (str "create table if not exists person ("
             "id integer primary key autoincrement, "
             "firstname text not null, "
             "lastname text not null, "
             "email text not null unique, "
             "phone text not null unique );")]
    (db/create-table! sql)))

(defn make-person 
  [{:keys [firstname 
           lastname 
           email 
           phone]}]
  {:firstname firstname
   :lastname lastname
   :email email
   :phone phone})

(defn write-person [person]
  (db/insert! :person person))

(defn get-persons []
  (db/query "select * from person as p"))

(def sample-person 
  (make-person {:firstname "Darren"
                :lastname "Kim"
                :email "darrenkim@dsfl.com"
                :phone "112312-343"}))