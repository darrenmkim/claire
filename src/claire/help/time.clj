(ns claire.help.time
  (:refer-clojure
   :exclude [range iterate format max min])
  (:require
   [java-time :as jt]))

(defn make-date-zoned [year month day]
  (jt/zoned-date-time year month day))

(defn make-years [n]
  (jt/years n))

(defn make-months [n]
  (jt/months n))

(defn make-days [n]
  (jt/days n))

(defn make-date [year month day]
  (jt/local-date year month day))

(defn sqldate->localdate [sqldate]
  (jt/local-date sqldate))

(defn make-stamp []
  (jt/instant->sql-timestamp 
   (jt/instant)))

(defn str->date [s]
  (jt/local-date "yyyy-MM-dd" s))

(defn date->str [d]
  (jt/format "yyyy-MM-dd" d))

(defn add [date span]
  (jt/plus date span))

(defn subtract [date span]
  (jt/minus date span))

(defn until [x y]
  (.until y x (java.time.temporal.ChronoUnit/DAYS)))

(defn later? [x y]
  (> (until x y) 0))
