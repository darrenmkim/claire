(ns claire.domain.tag.client
  (:require [claire.center.db :as db]))

(defn schema []
  (str
   "create table if not exists "
   "client ("
   "id text primary key, "
   "shortname text not null, "
   "legalname text not null, "
   "basecur text references currency(id) not null, "
   "functionalcur text references currency(id) not null, "
   "reportingcur text references currency(id) not null, "
   "memo text)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<client> table is set up."))

(defn set-mock! []
  (let [data
        [{:id "name"
          :shortname "FakeDefinable"
          :legalname "FakeDefinable, LP"
          :basecur "usd"
          :functionalcur "usd"
          :reportingcur "usd"
          :memo "Mock for testing."}]]
    (db/insert! :client data)))
