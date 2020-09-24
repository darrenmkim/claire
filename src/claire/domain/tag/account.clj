(ns claire.domain.tag.account
  (:require [claire.center.db :as db]))

(defn schema []
  (str "create table if not exists "  
       "account ("
       "id serial primary key, "
       "name text unique not null, "
       "number text unique not null, "
       "memo text not null, "
       "statusid text "
       "references status(id) not null)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<account> table is set up."))
