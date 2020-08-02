(ns claire.date
  (:import java.util.Date)
  (:import java.util.Calendar))

(defn get-date-now []
  (Date.))

(defn add-days [date num-days]
  (.getTime (let [cal (Calendar/getInstance)]
              (doto cal
                (.setTime date)
                (.add Calendar/DATE num-days)))))

(defn add-months [date num-months]
  (.getTime (let [cal (Calendar/getInstance)]
              (doto cal
                (.setTime date)
                (.add Calendar/MONTH num-months)))))
