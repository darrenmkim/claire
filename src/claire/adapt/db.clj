(ns claire.adapt.db
  (:require [clojure.java.jdbc :as jdbc]))

(defn dbspec []
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "tdb/tdb.db"})

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