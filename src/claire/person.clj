(ns claire.person
  (:require [claire.db :as db]))

(def personcreatesql
  (str "create table if not exists person ("
       "id integer primary key autoincrement, "
       "firstname text not null, "
       "lastname text not null, "
       "email text not null unique, "
       "phone text not null unique );"))

(db/createtable personcreatesql)

(db/insert! :person {:firstname "Darren"
                     :lastname "Kim"
                     :email "darrenkim@dsfl.com"
                     :phone "112312-343"})

(db/query "select * from person as p")