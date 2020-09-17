
(ns claire.domain.var.quote
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   quote (
   id serial primary key,
   date date not null,
   rateid integer references rate (id) not null,
   value real not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<quote> table is set up."))