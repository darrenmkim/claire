(ns claire.center.serve
  (:require
   [ring.adapter.jetty :refer [run-jetty]]
   [clojure.pprint :refer [pprint]]
   [compojure.core :refer [routes GET POST]] ;; PUT DELETE can be added 
   [compojure.route :refer [not-found]]
   [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
   [ring.middleware.cors :refer [wrap-cors]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :refer [response]]
   [claire.domain.ability :as ability]
   ))

(def my-routes
  (routes
   (GET "/" [] (response "asdfdf"))
   (GET "/abilities-all" [] (response (ability/get-all)))
   (GET "/ability-by-id/:id" [id] (response (ability/get-one-by-id id)))
   (GET "/abilities-count" [] (response (ability/count-all)))
   (GET "/test" [] (response {:baz "qsssux"}))
   (POST "/debug" request (response (with-out-str (pprint request))))
   (not-found {:error "Not found"})))

(def app (-> my-routes
             wrap-json-body
             wrap-json-response
             (wrap-cors
              :access-control-allow-origin [#".*"]
              :access-control-allow-methods [:get])))

(defn run []
  (run-jetty (wrap-reload #'app)
             {:port 2317
              :join? false}))
