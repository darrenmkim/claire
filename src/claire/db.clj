(ns claire.db
  (:require [clojure.java.jdbc :as jdbc]))

(comment "
get into postgres environment: 
$ sudo -u postgres psql 
create user: 
postgres=# create user admin with password '4231';
initialize database: 
postgres=# create database clairedb owner admin;
")

(defn dbspec []
  {:dbtype "postgresql"
   :dbname "clairedb"
   :host "localhost"
   :user "admin"
   :password "4231"})

(defn execute! [sql]
  (jdbc/execute! (dbspec) sql))

(defn insert!
  "Inserts records by two arguments that are
   1) the table name in keyword and 
   2) the records in a list of maps, 
   respectively."
  [table records]
  (doseq [record records]
    (jdbc/insert! (dbspec) table record)))

(defn query
  [sql]
  (jdbc/query (dbspec) sql))

(defn get-value-by-query
  [sql]
  (first (vals (first (jdbc/query (dbspec) sql)))))

(defn count-records [table format]
  (let [raw
        (query
         (str "select count(*) as count from " (name table)))]
    (case format
      :num (:count (first raw))
      :data raw)))

(defn insert-pre! [table data]
  (when (= (count-records table :num)
           0)
    (insert! table data)))
