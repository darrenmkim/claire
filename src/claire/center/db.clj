(ns claire.center.db
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
   2) the records in a list of maps, 
   respectively."
  [table records]
  (doseq [record records]
    (jdbc/insert! (dbspec) table record)))

(defn get-records-by-query
  [sql]
  (jdbc/query (dbspec) sql))

(defn get-value-by-query
  [sql]
  (first (vals (first (jdbc/query (dbspec) sql)))))

(defn count-records [table format]
  (let [raw
        (get-records-by-query
         ["select count(*) as count from " (name table)])]
    (case format
      :num (:count (first raw))
      :data raw)))

(defn insert-pre! [table data]
  (when (= (count-records table :num)
           0)
    (insert! table data)))