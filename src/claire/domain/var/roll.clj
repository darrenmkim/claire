(ns claire.domain.var.roll
  (:require
   [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   roll (
   id serial primary key,
   ordertime TIMESTAMPTZ not null,
   systemstart date not null,
   systemend date not null,    
   personid integer not null, 
   memo text not null, 
   constraint fk_person
   foreign key (personid)
   references person(id)) 
   ")

(defn set-db! []
  (db/execute! (schema))
  (println "<roll> table is set up."))

;;; test
;(def sample 
;  {:ordertime "2020-09-03"
;   :systemstart "2020-09-03"
;   :systemend "2020-09-03"
;   :personid 5})