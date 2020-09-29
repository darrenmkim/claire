(ns claire.domain.var.deal
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
   [clojure.spec.alpha :as s]
   [claire.domain.base :as b]))


(def ttt (s/conform b/stances :pay))
(def mymonth
  (if (s/valid? b/stances :pay)
    (:memo (:pay b/stances))))

(def myusd (s/conform b/currency :usd))


(defn schema []
  (str "create table if not exists "
       "deal ("
       "id serial primary key, "
       "name text unique not null, "
       "categorya text references category(name) not null, "
       "categoryb text references category(name) not null, "
       "categoryc text references category(name) not null, "
       "categoryd text references category(name) not null, "
       "categorye text references category(name) not null, "
       "categoryf text references category(name) not null, "
       "categoryg text references category(name) not null, "    
       "breedid text references breed(id) not null, "
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
      :breedid "irs"
      :categorya "Hedge"
      :categoryb "America"
      :categoryc "Rider"
      :categoryd "Rock"
      :categorye "Golf"
      :categoryf "Piano"
      :categoryg "Math"      
      :tradedate (tm/make-date 2013 1 3)
      :effectdate (tm/make-date 2013 1 5)
      :terminatedate (tm/make-date 2018 1 5)
      :maturedate (tm/make-date 2018 1 5)
      :memo "mock deal"}
     {:id 2
      :name "collegefinance_crs"
      :breedid "crs"
      :categorya "Ease"
      :categoryb "America"
      :categoryc "Biker"
      :categoryd "Rock"
      :categorye "Gold"
      :categoryf "Flute"
      :categoryg "Science"       
      :tradedate (tm/make-date 2013 1 3)
      :effectdate (tm/make-date 2013 1 5)
      :terminatedate (tm/make-date 2018 1 5)
      :maturedate (tm/make-date 2018 1 5)
      :memo "mock deal"}
     ]]
    (db/insert! :deal data)))
