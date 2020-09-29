(ns claire.core
  (:require 
   [claire.center.serve :as serve]
   [claire.center.load :as load]))

(defn -main []
  (load/set-domain-db!)
  (serve/run))
