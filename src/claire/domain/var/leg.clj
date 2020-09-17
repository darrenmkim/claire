(ns claire.domain.var.leg
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   leg (
   id serial primary key,
   name text unique not null,
   dealid integer references deal (id) not null,
   pactid integer references pact (id) not null, 
   stanceid integer references stance (id) not null,           
   basecur text not null, 
   localcur text not null, 
   freqid integer references freq (id) not null,      
   dayid integer references day (id) not null,           
   notional money not null, 
   rateid integer references rate (id) not null,
   givenrate real not null, 
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<leg> table is set up."))