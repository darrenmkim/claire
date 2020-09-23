(ns claire.domain.var.tran
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
   [infix.macros :refer [infix]]
   ))

(defn schema []
  "create table if not exists 
   tran (
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
  (println "<tran> table is set up."))

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
   :interestpayment "(* notional givenrate (/ months 12))"
   :principalpayment "notional"})



(defn make-upfrontcost [leginfo]
  (cond
    (= (:upfrontcost leginfo) "") nil
    ))

(defn make-interestpayments [leginfo]
  (let [interestpayment (:interestpayment leginfo)
        notional (:notional leginfo)
        givenrate (:givenrate leginfo)
        months (:months leginfo)]
       (if (= interestpayment "") nil
           {:date (:tradedate leginfo)
            :legid legid
            :eventid (:eventid leginfo)
           :accrstart  
   accrend date not null,   
   rateid integer references rate (id) not null,
   quote real not null,
   basecur text not null, 
   baseamt real not null,
   localcur text not null, 
   localamt real not null,
   memo 


            }


         )))

(defn make-principalpayment [leginfo]
  (let [notional (:notional legino)]
    (if (= (:principalpayment leginfo) nil) nil
        {:id serial primary key,
         :date (:terminatedate leginfo)
         :legid legid 
         :eventid "terminate"
         :accrstart nil  
         :accrend nil   
         :tickerid nil
         :quote nil
         :basecur  
         :baseamt 
         :localcur 
         :localamt
         :memo
         })))


(defn pos-neg-or-zero
  "Determines whether or not n is positive, negative, or zero"
  [n]
  (cond
    (< n 0) "negative"
    (> n 0) "positive"
    :else "zero"))

(defn make-trans [legid]
  (let [leginfo (make-leginfo legid)]
    (make-upfrontcost leginfo)
    (make-interestpayments leginfo)
    (make-principalpayment leginfo)))
