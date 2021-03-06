(defproject claire "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :main claire.core
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clojure.java-time "0.3.2"]
                 [string "0.1.0"]
                 [ring "1.7.0"]
                 [ring/ring-json "0.5.0"]
                 [ring-cors "0.1.13"]
                 [compojure "1.6.1"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.postgresql/postgresql "9.4.1212"]
                 [org.xerial/sqlite-jdbc "3.23.1"]
                 [rm-hull/infix "0.3.3"]]
  :repl-options {:init-ns claire.core})
