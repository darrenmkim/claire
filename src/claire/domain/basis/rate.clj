(ns claire.domain.basis.rate
  (:require
   [claire.center.db :as db]
   [claire.help.format :as frmt]))

(defn schema []
  "create table if not exists
   rate (
   id smallserial primary key,
   name text unique not null,
   memo text not null)")

(defn preval []
  [{:name "none" :memo "fixed and not use market rates"}
   {:name "libor1d" :memo "libor 1 day"}
   {:name "libor1w" :memo "libor 1 week"}
   {:name "libor1m" :memo "libor 1 month"}
   {:name "libor2m" :memo "libor 2 months"}
   {:name "libor3m" :memo "libor 3 months"}
   {:name "libor6m" :memo "libor 6 months"}
   {:name "libor1y" :memo "libor 1 year"}
   {:name "euribor1w" :memo "euribor 1 week"}
   {:name "euribor2w" :memo "euribor 1 week"}
   {:name "euribor1m" :memo "euribor 1 month"}
   {:name "euribor2m" :memo "euribor 2 months"}
   {:name "euribor3m" :memo "euribor 3 months"}
   {:name "euribor6m" :memo "euribor 6 months"}
   {:name "euribor9m" :memo "euribor 9 months"}
   {:name "euribor1y" :memo "euribor 1 year"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :rate (preval))
  (println "<rate> table is set up."))

(defn find-id-by-name [name]
  (let [sql "select id from rate where name = "]
    (:id (first
          (db/query
           (str sql (frmt/quote-single name)))))))