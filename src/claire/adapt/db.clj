(ns claire.adapt.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as string]))

(comment
  "Psql commands to initialize database:   
   postgres=# create user defineadmin with password '1324';
   postgres=# create database clairedb owner defineadmin;")


(def dbspec {:dbtype "postgresql"
             :dbname "clairedb"
             :host "localhost"
             :user "defineadmin"
             :password "1324"})

(def statesql
  (jdbc/create-table-ddl
   :testtable [[:id :serial "primary key"]
               [:state "varchar(32)"]
               [:abrv "varchar(2)"]]))


(jdbc/create-table-ddl :fruit
                       [[:name "varchar(32)" :primary :key]
                        [:appearance "varchar(32)"]
                        [:cost :int]
                        [:grade :real]])

(def fruit-table-ddl
  (jdbc/create-table-ddl :fruit
                         [[:name "varchar(32)"]
                          [:appearance "varchar(32)"]
                          [:cost :int]
                          [:grade :real]]))


(jdbc/execute! dbspec statesql)

(defn create-table! 
  "Create a table according to given sql code.
   As a convention, all creation code must 
   attemtp to create if the table does not exist 
   in the connected database."
  [sql]
  (jdbc/execute! (dbspec) sql))

(defn insert! 
  "Inserts a record by two arguments that are
   1) the table name in keyword and 
   2) the data in map, 
   respectively."
  [table record]
  (jdbc/insert! (dbspec) table record))

(defn query 
  "Runs query by given sql code and 
   returns the result in hashmap."
  [sql]
  (jdbc/query (dbspec) [sql]))