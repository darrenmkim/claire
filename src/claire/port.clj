(ns claire.port
  (:refer-clojure :exclude [range iterate format max min])
  (:require [clojure.spec.alpha :as s]
            [java-time :as t]
            [claire.domain :refer :all]
            [claire.mock :as mock]
            ))

(defn find-single [data k id]
  (first (filter #(= (k %) id) data)))

(defn find-presets [pact event]
;; temporarily referring to mock
  (filter
   (fn [x] (and (= (:pact x) pact)
                (= (:event x) event)))
   mock/presets))

(defn find-rate
;; temporarily referring to mock
  [date code]
  (:percent
   (first (filter
           (fn [x] (and (= (:date x) date)
                        (= (:code x) code)))
           mock/rates)))) 
