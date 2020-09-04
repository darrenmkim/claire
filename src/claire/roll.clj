(ns claire.roll
  (:require
   [claire.db :as db]))

(defn ensure-roll-table []
  (let [sql
        (str "create table if not exists roll ("
             "id integer primary key autoincrement, "
             "ordertime text not null, "
             "systemstart text not null, "
             "systemend text not null, "
             "personid integer not null );")]
    (db/create-table! sql)))

(defn make-roll
  [{:keys [ordertime 
           systemstart 
           systemend 
           personid]}]
  {:ordertime ordertime
   :systemstart systemstart
   :systemend systemend
   :personid personid})

(defn write-roll [roll]
  (db/insert! :roll roll))

(defn get-rolls []
  (db/query "select * from roll as r"))

;;; test
(def sample-roll 
  (make-roll {:ordertime "2020-09-03"
              :systemstart "2020-09-03"
              :systemend "2020-09-03"
              :personid 5}))