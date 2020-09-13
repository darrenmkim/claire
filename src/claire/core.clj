(ns claire.core
  (:require 
   [claire.tower.serve :as serve]
   [claire.tower.prep :as prep]))

(defn -main []
  (prep/set-domain-db)
  (serve/run))
