(ns claire.domain.person
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   person (
   id serial primary key,
   username text unique not null,
   firstname text not null, 
   lastname text not null,
   email text unique not null,
   phone text)")

(defn set! []
  (db/execute! (schema))
  (println "<person> table is set up."))

;;; test
(def sample 
  {:firstname "Dar"
   :lastname "Ki"
   :email "daaaaakim@dsfl.com"
   :phone "3412-343"})