(ns claire.domain.var.tran
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
   ))


(defn schema []
  "create table if not exists 
   tran (
   id serial primary key,
   date date not null, 
   legid integer references leg (id) not null,
   eventid integer references event (id) not null,
   accrstart date not null, 
   accrend date not null,   
   rateid integer references rate (id) not null,
   quote real not null,
   basecur text not null, 
   baseamt real not null,
   localcur text not null, 
   localamt real not null,
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<tran> table is set up."))

(defn make-tran [legid accrstart accrend cashdate]
  (let [rateid
        (db/get-value-by-query
         ["select l.rateid from leg as l where l.id = ?" legid])
        givenrate
        (db/get-value-by-query
         ["select l.givenrate from leg as l where l.id = ?" legid])
        amount
        (* givenrate (/ (tm/until accrend accrstart) 360))]
    {:date cashdate
     :legid legid
     :eventid 1
     :accrstart accrstart
     :accrend accrend
     :rateid rateid
     :rateval givenrate
     :basecur 1
     :baseamt amount
     :localcur 1
     :localamt amount
     :memo 1}))

(def legid 1)
(def accrstart (tm/make-date 2020 1 3))
(def accrend (tm/make-date 2020 6 3))
(def cashdate (tm/make-date 2020 6 3))
(make-tran legid accrstart accrend cashdate)