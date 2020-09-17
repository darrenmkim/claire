(ns claire.domain.var.deal
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   deal (
   id serial primary key,
   name text unique not null,
   tradedate date not null, 
   effectdate date not null, 
   terminatedate date not null, 
   maturedate date not null,
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<deal> table is set up."))