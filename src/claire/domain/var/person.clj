(ns claire.domain.var.person
  (:require [claire.dock.db :as db]))

(defn schema []
  "create table if not exists 
   person (
   id serial primary key,
   username text unique not null,
   firstname text not null, 
   lastname text not null,
   email text unique not null,
   phone text)")

(defn set-db! []
  (db/execute! (schema))
  (println "<person> table is set up."))

;;; test
(def mocks
 [{:username "dakio" :firstname "Dar" :lastname "Ki"
   :email "asdf@dsfl.com" :phone "123-312-8343"}
  {:username "talomo" :firstname "Abe" :lastname "Ki"
   :email "hgfd@dsfl.com" :phone "123-312-8343"}
  {:username "kouka" :firstname "Lom" :lastname "Ki"
   :email "bvcxdf@dsfl.com" :phone "123-312-8343"}
  {:username "wassa" :firstname "Bop" :lastname "Ki"
   :email "erttr@dsfl.com" :phone "123-312-8343"}
  {:username "hulou" :firstname "Ram" :lastname "Ki"
   :email "fgfgd@dsfl.com" :phone "123-312-8343"}
  {:username "bravi" :firstname "Fin" :lastname "Ki"
   :email "sdff@dsfl.com" :phone "123-312-8343"}
  {:username "assu" :firstname "Kal" :lastname "Ki"
   :email "sdfghrty@dsfl.com" :phone "123-312-8343"}
  {:username "lopolo" :firstname "Von" :lastname "Ki"
   :email "dfgertdf@dsfl.com" :phone "123-312-8343"}])

(db/insert! :person mocks)