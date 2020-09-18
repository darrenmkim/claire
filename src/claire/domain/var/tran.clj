(ns claire.domain.var.tran
  (:require
   [claire.center.db :as db]
   ;[claire.help.time :as tm]
   ))

(defn schema []
  "create table if not exists 
   tran (
   id serial primary key,
   date date not null, 
   legid integer references leg (id) not null,
   eventid integer references event (id) not null,
   days integer not null,
   rate real not null,
   basecur text not null, 
   baseamt money not null,
   localcur text not null, 
   localamt money not null,
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<tran> table is set up."))

(defn project! []
  (let
   [sql
    (str
     "select * 
      from leg as l 
      left join pact as p 
      on l.pactid = p.id
      left join deal as d
      on l.dealid = d.id")]
    (db/query sql)
    ;(db/insert! :tran sql)  
    ))