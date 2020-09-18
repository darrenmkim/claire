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

;;;;;;;;;;;;;; MOCKING

(defn set-mock! []
  (let
   [data
    [{:name "abcirs01fix"
      :dealid 1
      :pactid 1
      :stanceid 1
      :basecur "USD"
      :localcur "USD"
      :freqid 3
      :dayid 1
      :notional 1000000
      :rateid 6
      :givenrate 0.4
      :memo "mock leg 01"}
     {:name "abcirs01float"
      :dealid 1
      :pactid 2
      :stanceid 2
      :basecur "USD"
      :localcur "USD"
      :freqid 3
      :dayid 1
      :notional 1000000
      :rateid 6
      :givenrate 0.0
      :memo "mock leg 02"}]]
    (db/insert! :leg data)))

(defn q []
  (db/query 
   "select * from leg"))


(comment
  " ")