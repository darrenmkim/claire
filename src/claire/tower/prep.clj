(ns claire.tower.prep
  (:require
   [claire.domain.ability :as ability]
   [claire.domain.account :as account]
   [claire.domain.breed :as breed]
   [claire.domain.day :as day]
   [claire.domain.event :as event]
   [claire.domain.freq :as freq]
   [claire.domain.pact :as pact]
   [claire.domain.person :as person]
   [claire.domain.quote :as quote]
   [claire.domain.rate :as rate]
   [claire.domain.roll :as roll]
   [claire.domain.stance :as stance]
   [claire.domain.status :as status]))

(defn set-domain-db []
  (ability/set-db!)
  (account/set-db!)
  (breed/set-db!)
  (day/set-db!)
  (event/set-db!)
  (freq/set-db!)
  (pact/set-db!)
  (person/set-db!)
  (quote/set-db!)
  (rate/set-db!)
  (roll/set-db!)
  (stance/set-db!)
  (status/set-db!))