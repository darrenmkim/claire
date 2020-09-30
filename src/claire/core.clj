(ns claire.core
  (:require
   [claire.center.serve :as serve]))

(defn -main []
  (serve/run))
