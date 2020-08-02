(ns claire.core
  (:require [claire.domain]
            [claire.cash]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main []
  (foo "a"))
