(ns claire.domain.var.proj
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
  ;; [infix.macros :refer [infix]]
   ))

(defn schema []
  (str "create table if not exists "
       "proj ("
       "id serial primary key, "
       "date date not null, "
       "legid int references leg(id) not null, "
       "eventid int references event(id) not null, "


       "notionallocal real, "
       "notionalbase real, "
       "cashlocal real, "
       "cashbase real, "
       
       
       
       
       "accrstart date, "
       "accrend date,   "
       "tickerid text references ticker(id), "
       "quote real, "
       "basecur text not null, "
       "baseamt real not null, "
       "localcur text not null, "
       "localamt real not null, "
       "memo text not null)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<proj> table is set up."))

(defn make-leginfo [legid]
  (let [sql
        (str "select "
             "l.id as legid, "
             "l.name as legname, "
             "d.id as dealid, "
             "d.name as dealname, "
             "l.stanceid, "
             "d.tradedate, "
             "d.effectdate, "
             "d.terminatedate, "
             "d.maturedate, "
             "b.id as breedid, "
             "p.id as pactid, "
             "s.id as spanid, "
             "s.months, "
             "l.period, "
             "l.localcur, "
             "l.notionalinlocalcur, "
             "l.notionalcurrate, "
             "l.interestrateticker, "
             "l.givenrate, "
             "p.upfrontcost, "
             "p.interestpayments, "
             "p.principalpayment "
             "from leg as l "
             "join deal as d on l.dealid = d.id "
             "join breed as b on d.breedid = b.id "
             "join pact as p on l.pactid = p.id "
             "join span as s on l.spanid = s.id "
             "where "
             "l.id = " legid)]
    (first (db/query sql))))

;; mocking 
(def leginfo
  (make-leginfo 3))


(defn make-upfrontcost [leginfo]
  (if (nil? (:upfrontcost leginfo)) ()
      {:date (:effectdate leginfo)
       :legid (:legid leginfo)
       :eventid nil
       :accrstart nil
       :accrend nil
       :tickerid nil
       :quote nil
       :basecur nil
       :baseamt nil
       :localcur (:localcur leginfo)
       :localamt (load-string (:upfrontcost leginfo))
       :memo "mock"}))


(defn make-interestpayments [leginfo]
  (let [notionalinlocalcur (:notionalinlocalcur leginfo)
        givenrate (:givenrate leginfo)
        months (:months leginfo)
        maturedate (tm/sqldate->localdate (:maturedate leginfo))
        effectdate (tm/sqldate->localdate (:effectdate leginfo))
        ]
    (letfn [(make-instance [startdate]
              (if (tm/later? startdate maturedate) nil
                  (cons
                   {:date (tm/add startdate (tm/make-months months))
                    :legid (:legid leginfo)
                    :eventid 
                    :accrstart startdate 
                    :accrend (tm/add startdate (tm/make-months months))
                    :tickerid (:tickerid leginfo)
                    :quote (:quote leginfo)
                    :basecur (:basecur leginfo) 
                    :baseamt (load-string (:interestpayments leginfo))
                    :localcur (:localcur leginfo)
                    :localamt (load-string (:interestpayments leginfo))
                    :memo "mock"
                    }
                   (make-instance (tm/add startdate (tm/make-months months))))))]
      (make-instance effectdate))))


(defn make-principalpayment [leginfo]
  (let [notionalinlocalcur (:notionalinlocalcur leginfo)]
    (if (nil? (:principalpayment leginfo)) ()
        {:date (:maturedate leginfo)
         :legid (:legid leginfo) 
         :eventid "terminate"
         :accrstart nil  
         :accrend nil   
         :tickerid nil
         :quote nil
         :basecur "usd" 
         :baseamt (/ (load-string (:principalpayment leginfo))
                     0.2) ;; mock function, needs to work
         :localcur (:localcur leginfo)
         :localamt (load-string (:principalpayment leginfo))
         :memo "abc"})))



(defn make-projs [legid]
  (let [leginfo (make-leginfo legid)
        legproj            ;;(make-upfrontcost leginfo)
        ;; (make-interestpayments leginfo)
        (make-principalpayment leginfo)
        ]
    legproj))    
