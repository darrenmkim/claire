(ns claire.domain.deal
  (:require
   [claire.center.db :as db]
   [claire.help.time :as tm]
   [clojure.spec.alpha :as s]
   [claire.domain.base :as b]
   ;; [claire.domain.quote :as q]
   ))



(defn make-fixedquote [t q]
  (s/conform ::b/fixedquote
             {::b/ticker t
              ::b/quote q}))

(defn make-leg
  [id name pact stance period span day
   contractnum notional t q
   movingticker memo]
  (s/conform ::b/leg
             {::b/id id
              ::b/name name 
              ::b/pact pact
              ::b/stance stance
              ::b/period period
              ::b/span span
              ::b/day day
              ::b/contractnum contractnum
              ::b/notional notional 
              ::b/fixedquote (make-fixedquote t q)
              ::b/movingticker movingticker
              ::b/memo memo}))

(def myleg
  (make-leg 1
            "abc"
            :irsfix
            :receive
            10
            :annual
            :d30360
            1
            100000.0
            :libor1y 0.012
            :libor1m
            "my test"))

(def test-deal
  (s/explain ::deal
             {::id 1
              ::name "abc"
              ::tradedate (tm/make-date 2013 1 3)
              ::effectdate (tm/make-date 2013 1 5)
              ::terminatedate (tm/make-date 2018 1 5)
              ::maturedate (tm/make-date 2018 1 5)
              ::leg [(s/conform ::leg
                                {::id 1
                                 ::name "abcleg1"
                                 ::pact (s/conform pact :irsfix)
                                 ::stance (s/conform b/stance :receive)
                                 ::period 10
                                 ::span (s/conform b/span :semiannual)
                                 ::day (s/conform b/day :d30360)
                                 ::notional {:currency (s/conform b/currency :usd)
                                             :amount 10000}
                                 ::memo "test leg 1"})
                     (s/conform ::leg
                                {::id 2
                                 ::name "abcleg2"
                                 ::pact (s/conform pact :irsflt)
                                 ::stance (s/conform b/stance :pay)
                                 ::period 10
                                 ::span (s/conform b/span :semiannual)
                                 ::day (s/conform b/day :d30360)
                                 ::notional {:currency (s/conform b/currency :usd)
                                             :amount 10000}
                                 ::memo "test leg 2"})]
              ::memo "testing"}))






