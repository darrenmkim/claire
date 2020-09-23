(ns claire.domain.var.leg
  (:require [claire.center.db :as db]))



(defn schema []
  (str "create table if not exists "
       "leg ("
       "id serial primary key, "
       "name text unique not null, "
       "dealid integer references deal(id) not null, "
       "pactid integer references pact(id) not null, "
       "stanceid integer references stance (id) not null, "
       "periods integer not null, "
       "currencyid text references currency(id) not null, "
       "spanid text references span(id) not null, "
       "dayconvid integer references dayconv(id) not null, "
       "notional real not null, "
       "tickerid integer references rate (id) not null, "
       "givenrate real not null, "
       "memo text not null)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<leg> table is set up."))


;;;;;;;;;;;;;; MOCKING

(defn set-mock! []
  (let
   [data
    [

     {:name "abcirs01fix"
      :dealid 1
      :pactid 1
      :stanceid 1     
      :basecur "USD"
      :localcur "USD"
      :spanid 3
      :dayconvid 1
      :notional 1000000.00
      :tickerid 6
      :givenrate 0.4
      :memo "mock leg 01"}
     {:name "abcirs01float"
      :dealid 1
      :pactid 2
      :stanceid 2
      :basecur "USD"
      :localcur "USD"
      :spanid 3
      :dayconvid 1
      :notional 1000000.00
      :tickerid 6
      :givenrate 0.0
      :memo "mock leg 02"}



     

     ;; example exerted from
     ;; https://www.youtube.com/watch?v=IC5N_Dq9LEM
     {:name "collegefinance_crs_xyz"
      :dealid 2
      :pactid "crsfix"
      :stanceid "pay" ;; pay 
      :periods 5 ;; for 5 years 
      :spanid "annual"
      :dayconvid "30360"
      :currencyid "usd" ;; usd 
      :notional 1400000.00
      :tickerid "fixed"
      :givenrate 0.045
      :memo "mock leg 01"}
     
     {:name "collegefinance_crs_abc"
      :dealid 2
      :pactid "crsfix"
      :stanceid "receive"
      :periods 5  
      :spanid "annual"
      :dayconvid "30360"
      :currencyid "eur"
      :notional 1000000.00
      :tickerid "fixed"
      :givenrate 0.06
      :memo "mock leg 02"}





















     ]]
    (db/insert! :leg data)))

(defn q []
  (db/query 
   "select * from leg"))


(comment
  " ")
