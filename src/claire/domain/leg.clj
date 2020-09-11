(ns claire.domain.leg
  (:require [claire.dock.db :as db]))

(defn set-table! []
  (let [sql
        (str "create table if not exists leg ("
             "id integer primary key autoincrement, "
             "name text not null unique, "
             "dealid integer not null, "
             "pactid integer not null, "
             "stanceid integer not null, "
             "basecur text not null, "
             "localcur text not null, "
             "freqid integer not null, "
             "dayid integer not null, "
             "notional real not null, "
             "rateid integer not null, "
             "givenrate real );")]
    (db/create-table! sql)))


(defn schema []
  "create table if not exists 
   leg (
   id serial primary key,
   name text unique not null, 
   pactid int not null, 
   stanceid int not null,              
   basecur text not null, 
   localcur text not null, 
   freqid int not null,    
   dayid int not null,              
   notional numeric(16, 2) not null, 
   rateid int not null, 
   givenrate numeric(6, 16) not null, 
   memo text not null,


   
   constraint fk_pact
   foreign key (pactid)
   references pact(id)),    
   constraint fk_stance
   foreign key (stanceid)
   references stance(id)), 

   constraint fk_freq
   foreign key (freqid)
   references freq(id)),
             
   constraint fk_day
   foreign key (dayid)
   references day(id)), 
   
   
   constraint fk_rate
   foreign key (rateid)
   references rate(id)), 
   
   
   ")






(defn set-db! []
  (db/execute! (schema))
  (println "<leg> table is set up."))