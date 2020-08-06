(ns claire.server
  (:require
   ;; [clojure.spec.alpha :as sp :refer :all]
   [java-time :as t]
   [ring.adapter.jetty :refer [run-jetty]]
   [clojure.pprint :refer [pprint]]
   [compojure.core :refer [routes GET POST PUT DELETE]]
   [compojure.route :refer [not-found]]
   [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
   [ring.middleware.cors :refer [wrap-cors]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :refer [response]]
   [clojure.java.jdbc :refer :all]
   [claire.mock :refer :all]
   [claire.cash :refer :all]
   ))

(def my-routes
  (routes
   (GET "/" [] (response "asdfdf"))
   (GET "/mockcash" [] (response cash-test))
   (GET "/mockpreset" [] (response mock-presets))
   (GET "/test" [] (response {:baz "qsssux"}))
   (POST "/debug" request
         (response
          (with-out-str (clojure.pprint/pprint request))))
   (not-found {:error "Not found"})))

(def app (-> my-routes
             wrap-json-body
             wrap-json-response
             (wrap-cors :access-control-allow-origin [#".*"]
                        :access-control-allow-methods [:get])))

(defn serve []
  (run-jetty (wrap-reload #'app)
             {:port 2317
              :join? false}))
