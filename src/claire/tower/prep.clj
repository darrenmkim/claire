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
  (ability/set!)
  (account/set!)
  (breed/set!)
  (day/set!)
  (event/set!)
  (freq/set!)
  (pact/set!)
  (person/set!)
  (quote/set!)
  (rate/set!)
  (roll/set!)
  (stance/set!)
  (status/set!))