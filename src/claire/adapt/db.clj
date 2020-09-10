(ns claire.adapt.db
  (:require [clojure.java.jdbc :as jdbc]))

(comment
  "Psql commands to initialize database:
   $ sudo -u postgres psql 
   postgres=# create user defineadmin with password '1324';
   postgres=# create database clairedb owner defineadmin;")

(comment
  "Psql commands to check database:
   $ sudo -u postgres psql 
   postgres=# forwardslash-c clairedb;")

(defn dbspec []
  {:dbtype "postgresql"
   :dbname "clairedb"
   :host "localhost"
   :user "defineadmin"
   :password "1324"})

(defn execute! [sql]
  (jdbc/execute! (dbspec)
                 sql))

(defn insert! 
  "Inserts records by two arguments that are
   1) the table name in keyword and 
   2) the data in map, 
   respectively."
  [table records]
  (jdbc/insert! (dbspec) table records))

(defn query 
  "Runs query by given sql code and 
   returns the result in hashmap."
  [sql]
  (jdbc/query (dbspec) [sql]))