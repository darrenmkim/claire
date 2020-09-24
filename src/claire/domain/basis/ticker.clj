(ns claire.domain.basis.ticker
  (:require
   [claire.center.db :as db]
   [claire.help.format :as frmt]))

(defn schema []
  (str
   "create table if not exists "
   "ticker ("
   "id text primary key, "
   "name text unique not null)"))
  
(defn preval []
  [{:id "fixed" :name "fixed and not use market tickers"}
   {:id "libor1d" :name "libor 1 day"}
   {:id "libor1w" :name "libor 1 week"}
   {:id "libor2w" :name "libor 2 weeks"}
   {:id "libor1m" :name "libor 1 month"}
   {:id "libor2m" :name "libor 2 months"}
   {:id "libor3m" :name "libor 3 months"}
   {:id "libor6m" :name "libor 6 months"}
   {:id "libor1y" :name "libor 1 year"}
   {:id "euribor1w" :name "euribor 1 week"}
   {:id "euribor2w" :name "euribor 2 weeks"}
   {:id "euribor1m" :name "euribor 1 month"}
   {:id "euribor2m" :name "euribor 2 months"}
   {:id "euribor3m" :name "euribor 3 months"}
   {:id "euribor6m" :name "euribor 6 months"}
   {:id "euribor9m" :name "euribor 9 months"}
   {:id "euribor1y" :name "euribor 1 year"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :ticker (preval))
  (println "<ticker> table is set up."))
