(ns claire.format
  (:refer-clojure
   :exclude [range iterate format max min])
  (:require
   [java-time :as jt]))

(defn make-date [year month day]
  (jt/zoned-date-time year month day))

(defn str->date [s]
  (jt/local-date "yyyy-MM-dd" s))

(defn date->str [d]
  (jt/format "yyyy-MM-dd" d))