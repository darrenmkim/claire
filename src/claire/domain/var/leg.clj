(ns claire.domain.var.leg
  (:require [claire.center.db :as db]))

(defn schema []
  (str "create table if not exists "
       "leg ("
       "id serial primary key, "
       "name text unique not null, "
       "dealid int references deal(id) not null, "
       "pactid text references pact(id) not null, "
       "stanceid text references stance(id) not null, "
       "period smallint not null, "
       "localcur text references currency(id) not null, "
       "spanid text references span(id) not null, "
       "dayconvid text references dayconv(id) not null, "
       "notionalinlocalcur real not null, "
       "notionalcurrate real not null, " 
       "interestrateticker text references ticker(id) not null, "
       "givenrate real, "
       "memo text not null)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<leg> table is set up."))

;;;;;;;;;;;;;; MOCKING
(defn set-mock! []
  (let
      [data     
       [;;;;;;;;;;;
        {:id 1
         :name "mytest_irs_xyz"
         :dealid 2
         :pactid "irsfix"
         :stanceid "pay" ;; pay 
         :period 5 ;; for 5 years 
         :spanid "semiannual"
         :dayconvid "30360"
         :localcur "usd" ;; usd 
         :notionalinlocalcur 1400000.00
         :notionalcurrate 1.0
         :interestrateticker "fixed"
         :givenrate 0.045
         :memo "mock leg 01"}
        
        {:id 2
         :name "mytest_irs_abc"
         :dealid 2
         :pactid "irsflt"
         :stanceid "receiv"
         :period 5
         :spanid "semiannual"
         :dayconvid "30360"
         :localcur "eur"
         :notionalinlocalcur 1000000.00
         :notionalcurrate 1.4
         :interestrateticker "fixed"
         :givenrate 0.06
         :memo "mock leg 02"}


        
        ;; example exerted from
        ;; https://www.youtube.com/watch?v=IC5N_Dq9LEM
        {:id 3
         :name "collegefinance_crs_xyz"
         :dealid 2
         :pactid "crsfix"
         :stanceid "pay" ;; pay 
         :period 5 ;; for 5 years 
         :spanid "annual"
         :dayconvid "30360"
         :localcur "usd" ;; usd 
         :notionalinlocalcur 1400000.00
         :notionalcurrate 1.0
         :interestrateticker "fixed"
         :givenrate 0.045
         :memo "mock leg 01"}
        {:id 4
         :name "collegefinance_crs_abc"
         :dealid 2
         :pactid "crsfix"
         :stanceid "receiv"
         :period 5
         :spanid "annual"
         :dayconvid "30360"
         :localcur "eur"
         :notionalinlocalcur 1000000.00
         :notionalcurrate 1.4
         :interestrateticker "fixed"
         :givenrate 0.06
         :memo "mock leg 02"}]]
    (db/insert! :leg data)))
