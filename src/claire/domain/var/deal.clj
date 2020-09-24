(ns claire.domain.var.deal
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]))

(defn schema []
  (str "create table if not exists "
       "deal ("
       "id serial primary key, "
       "name text unique not null, "
       "tradedate date not null, "
       "effectdate date not null, "
       "terminatedate date not null, "
       "maturedate date not null, "
       "memo text not null)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<deal> table is set up."))

;; MOCKING
(defn set-mock! []
  (let
   [data
    [{:id 1
      :name "abcirs01"
      :tradedate (tm/make-date 2013 1 3)
      :effectdate (tm/make-date 2013 1 5)
      :terminatedate (tm/make-date 2018 1 5)
      :maturedate (tm/make-date 2018 1 5)
      :memo "mock deal"}
     {:id 2
      :name "collegefinance_crs"
      :tradedate (tm/make-date 2013 1 3)
      :effectdate (tm/make-date 2013 1 5)
      :terminatedate (tm/make-date 2018 1 5)
      :maturedate (tm/make-date 2018 1 5)
      :memo "mock deal"}
     ]]
    (db/insert! :deal data)))
