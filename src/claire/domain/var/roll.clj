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

(defn get-systemstart []
  (db/query
   "select max(r.systemend) + 1
    from roll as r"))

(defn make-roll [systemend personid memo]
  (let [roll
        {:ordertime (tm/make-stamp)
         :systemstart (get-systemstart)
         :systemend systemend
         :personid personid
         :statusid 2
         :memo memo}]
    (println roll)))



;;;;;;;;;;;;;; MOCKING

; test
(defn set-mock! []
  (let
   [data
    [{:ordertime (tm/make-stamp)
      :systemstart (tm/make-date 2012 12 1)
      :systemend (tm/make-date 2012 12 31)
      :personid 3
      :statusid 2
      :memo "mock for starting point"}]]
    (db/insert! :roll data)))


; test-roll -> return result without booking to db
; make-roll -> create and return result and book in db
; update-roll -> update existing items in db
; delete-roll -> delete all items in db but record the roll order in roll table