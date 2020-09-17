(ns claire.help.time
  (:refer-clojure
   :exclude [range iterate format max min])
  (:require
   [java-time :as jt]))

(defn make-date-zoned [year month day]
  (jt/zoned-date-time year month day))

(defn make-date [year month day]
  (jt/local-date year month day))

(defn make-stamp []
  (jt/instant->sql-timestamp 
   (jt/instant)))

(defn str->date [s]
  (jt/local-date "yyyy-MM-dd" s))

(defn date->str [d]
  (jt/format "yyyy-MM-dd" d))