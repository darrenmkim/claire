(ns claire.domain.var.roll
  (:require
   [claire.dock.db :as db]
   [java-time :as jt]
   ))

(defn schema []
  "create table if not exists 
   roll (
   id serial primary key,
   ordertime timestamptz not null,
   systemstart date not null,
   systemend date not null,
   personid integer 
   references person (id) not null ,
   statusid smallint 
   references status (id) not null,
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<roll> table is set up."))

; test
(def mocks 
  [{:ordertime (jt/instant)
    :systemstart (jt/local-date 2015 10 1)
    :systemend (jt/local-date 2015 10 1)
    :personid 3
    :statusid 2
    :memo "mock"}])

(db/insert! :roll mocks)




(jt/instant)
(type (jt/local-date 2015 10 1))
(type (jt/offset-date-time 2015 10 3 10 10))\


(jt/local-date 2015 10 1)