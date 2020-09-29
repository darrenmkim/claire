(ns claire.domain.quote
  (:require
   [clojure.spec.alpha :as s]
   [claire.center.db :as db]
   [claire.help.time :as tm]
   [claire.help.format :as frmt]
   [claire.domain.base :as b]))

(comment
  "
(defn schema []
  "create table if not exists 
   quote (
   id serial primary key,
   date date not null,
   rateid integer references rate (id) not null,
   value real not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<quote> table is set up."))

(defn find-quote
  "requires two inputs which are
     1) date in local-date format of java-time, and 
     2) rateid in integer that refers to rate table. 
     it returns a map with two elements, :valid? and :data.
   :valid? is boolean and :data is the whole record from the table."
  [date rateid]
  (let [actual
        (db/query (str "select * from quote "
                       "where date = " (frmt/quote-single date)
                       "and rateid = " rateid))
        latest
        (db/query (str "select * from quote "
                       "where rateid = " rateid
                       "order by date desc limit 1"))]
    (if (= (count actual) 1)
      {:valid? true :data actual}
      {:valid? false :data latest})))


;;;;;;;;;;;;;; MOCKING

(defn set-mock! []
  (let
   [data
    [{:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor1d") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor1w") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor1m") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor2m") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor3m") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor6m") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "libor1y") :value 0.3}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor1w") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor2w") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor1m") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor2m") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor3m") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor6m") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor9m") :value 0.2}
     {:date (tm/make-date 1950 1 1) :rateid (rate/find-id-by-name "euribor1y") :value 0.2}]]
    (db/insert! :quote data)))
 ")

(defn make-quote [t v]
  {:ticker (s/conform b/ticker t)  
   :value  (s/conform double? v)})
