(ns claire.core
  (:require 
   [claire.center.serve :as serve]
   [claire.center.prep :as prep]))

(defn -main []
  (prep/set-domain-db!)
  (serve/run))
