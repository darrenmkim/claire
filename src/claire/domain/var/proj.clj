(ns claire.domain.var.proj
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
   [infix.macros :refer [infix]]
   ))

(defn schema []
  "create table if not exists 
   proj (
   id serial primary key,
   date date not null, 
   legid integer references leg (id) not null,
   eventid integer references event (id) not null,
   accrstart date, 
   accrend date,   
   tickerid text references ticker(id),
   quote real,
   basecur text not null, 
   baseamt real not null,
   localcur text not null, 
   localamt real not null,
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<proj> table is set up."))

(defn make-leginfo [legid]
  {:pactid "crsfix"
   :stanceid "pay"
   :periods 5
   :spanid "annual"
   :currencyid "usd"
   :notional 1400000.00
   :tickerid "fixed"
   :givenrate 0.045
   :upfrontcost ""
   :interestpayments "(* notional givenrate (/ months 12))"
   :principalpayment "notional"})

;; mocking 
(def leginfo 
  {:legid 3
   :pactid "crsfix"
   :effectdate (tm/make-date 2013 1 3)
   :maturedate (tm/make-date 2018 1 3)
   :stanceid "pay"
   :periods 5
   :months 12
   :spanid "annual"
   :localcur "usd"
   :notionalinlocalcur 1400000.00
   :tickerid "fixed"
   :givenrate 0.045
   :upfrontcost nil
   :interestpayments "(* notionalinlocalcur givenrate (/ months 12))"
   :principalpayment "notionalinlocalcur"
   })

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
        maturedate (:maturedate leginfo)
        effectdate (:effectdate leginfo)]
    (letfn [(make-instance [startdate]
              (if (tm/later? startdate maturedate) nil
                  (cons
                   {:date (tm/add startdate (tm/make-months months))
                    :legid (:legid leginfo)
                    :eventid (:eventid leginfo)
                    :accrstart startdate 
                    :accrend (tm/add startdate (tm/make-months months))
                    :tickerid (:tickerid leginfo)
                    :quote (:quote leginfo)
                    :basecur (:basecur leginfo) 
                    :baseamt (load-string (:interestpayments leginfo))
                    :localcur (:localcur leginfo)
                    :localamt (load-string (:interestpayments leginfo))
                    :memo "mock"}
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
    
