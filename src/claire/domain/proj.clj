(ns claire.domain.proj
  (:require
   [claire.center.db :as db]
   [claire.domain.base :as b]
   
  ;; [claire.help.time :as tm]
  ;; [infix.macros :refer [infix]]
   ))

(defn schema []
  (str "create table if not exists "
       "proj ("
       "id serial primary key, "
       "date date not null, "
       "leg-id int references leg(id) not null, "
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

(defn make-contract-proj [deal leg-index]
  (let [breed (:breed deal)
        leg (nth (:leg deal) leg-index)]
    (case breed
      :irs [(b/make-proj {:id 0
                          :date (:tradedate deal)
                          :dealid (:id deal)
                          :legid (:id leg)
                          :event :contract
                          :periodstart nil
                          :periodend nil
                          :interestticker (:tickerfixed leg)
                          :interestnumber (:fixedrate leg)
                          :notionalcurrency (:notionalcurrency leg)
                          :notionalamount (:notionalamount leg)
                          :actual true})]
      :crs []
      :cal []
      :put []
      :cap []
      :cds []
      :trs []
      :crd []
      :spt []
      :inf []
      :trl []
      :rtr []
      :swt []
      :cms []
      :ftr []
      [])))

(defn make-projs-for-leg [deal leg-index]
  (let [contract-proj (make-contract-proj deal leg-index)]
    (cons contract-proj contract-proj)))

(defn make-projs-for-deal [deal]
  (let [leg-ids (map :id (:leg deal))]
    (letfn [(combine
              [deal leg-index]
              (if (>= leg-index (count leg-ids))
                []
                (cons (make-projs-for-leg deal leg-index)
                      (combine deal (+ leg-index 1)))))]
      (combine deal 0))))

(def myprojs (make-projs-for-deal b/mydeal))