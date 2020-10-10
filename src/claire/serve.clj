(ns claire.serve
  (:require
   [ring.adapter.jetty :refer [run-jetty]]
  ;; [clojure.pprint :refer [pprint]]
   [compojure.core :refer [routes GET]] ;; PUT DELETE can be added 
   [compojure.route :refer [not-found]]
   [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
   [ring.middleware.cors :refer [wrap-cors]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :refer [response]]
   [claire.domain :as dm]
  ;; [claire.domain.proj :as p]
   ))

(def my-routes
  (routes
   (GET "/" [] (response "asdfdf"))
   (GET "/test" [] (response {:baz "qsssux"}))
   (GET "/ability" [] (response (->> dm/static :ability-value)))
   (GET "/stance" [] (response (:stance-value dm/static)))
   ;; (GET "/leg" [] (response b/testleg))
   ;; (GET "/deal" [] (response b/mydeal))
   ;; (GET "/proj" [] (response p/myprojs))
   ;; (POST "/debug" request (response (with-out-str (pprint request))))
   (not-found {:error "Not found"})
   ))

(def app (-> my-routes
             wrap-json-body
             wrap-json-response
             (wrap-cors
              :access-control-allow-origin [#".*"]
              :access-control-allow-methods [:get])))

(defn run
  "Loading on http://localhost:2317/"
  []
  (run-jetty (wrap-reload #'app)
             {:port 2317 
              :join? false}))
