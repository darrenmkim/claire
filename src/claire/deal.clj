(ns claire.deal
  (:require
   [claire.db :as db]
   [claire.time :as tm]
   [clojure.spec.alpha :as s]
   [claire.domain :as b]))


(def ttt (s/conform b/stances :pay))
(def mymonth
  (if (s/valid? b/stances :pay)
    (:memo (:pay b/stances))))

(def myusd (s/conform b/currency :usd))



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
