(ns claire.center.load
  (:require
   [claire.domain.basis.ability :as ability]
   [claire.domain.basis.day :as day]
   [claire.domain.basis.event :as event]
   [claire.domain.basis.freq :as freq]
   [claire.domain.basis.rate :as rate]
   [claire.domain.basis.stance :as stance]
   [claire.domain.basis.status :as status]
   ;[claire.domain.tag.breed :as breed]
   ;[claire.domain.tag.pact :as pact]
   ;[claire.domain.tag.rate :as rate]
   ))

(defn set-domain-db! []
  ;; basis
  (ability/set-db!)
  (day/set-db!)
  (event/set-db!)
  (freq/set-db!)
  (rate/set-db!)
  (stance/set-db!)
  (status/set-db!)
  ;; tag
  ;;(breed/set-db!)
  ;;(pact/set-db!)
  ;;(rate/set-db!)
  )
