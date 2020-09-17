(ns claire.domain.var.roll
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
   ))

(defn schema []
  "create table if not exists 
   roll (
   id serial primary key,
   ordertime timestamptz not null,
   systemstart date not null,
   systemend date not null,
   personid integer 
   references person (id) not null,
   statusid smallint 
   references status (id) not null,
   memo text not null)")

(defn set-db! []
  (db/execute! (schema))
  (println "<roll> table is set up."))

; test
(def mocks 
  [{:ordertime (tm/make-stamp)
    :systemstart (tm/make-date 2020 9 5)
    :systemend (tm/make-date 2020 9 5)
    :personid 3
    :statusid 2
    :memo "mock"}])

(defn get-systemstart []
  (db/query
   "select max(r.systemend) + 1
    from roll as r"))

(defn make-roll [systemend personid memo]
  (let [roll {:ordertime (tm/make-stamp)
              :systemstart (get-systemstart)
              :systemend systemend
              :personid personid
              :statusid 2
              :memo memo}]
    (println roll)))

; test-roll -> return result without booking to db
; make-roll -> create and return result and book in db
; update-roll -> update existing items in db
; delete-roll -> delete all items in db but record the roll order in roll table

;; call leg 
(db/insert! :roll mocks)
;(type (jt/instant))
;(type (jt/sql-timestamp 2015 9 28 10 20 30 4000000))
; INSERT INTO roll (ordertime, systemstart, systemend, personid, statusid, memo) VALUES ('January 8 04 :05:06 1999 PST', '1999-01-08', '1999-01-08', 3, 2, 'mock');
;(jt/instant)
;(type (jt/local-date 2015 10 1))
;(type (jt/offset-date-time 2015 10 3 10 10))
;(jt/local-date 2015 10 1)