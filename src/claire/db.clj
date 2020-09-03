(ns claire.db
  (:require [clojure.java.jdbc :as jdbc]))

(def dbspec 
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "tdb/tdb.db"})

(defn createtable [sql]
  (jdbc/execute! dbspec sql))

(defn insert! [table record]
  (jdbc/insert! dbspec table record))

(defn query [sql]
  (jdbc/query dbspec [sql]))