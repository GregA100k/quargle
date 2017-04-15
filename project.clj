(defproject quargle "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clojure-csv/clojure-csv "2.0.1"]
                 [instaparse "1.4.1"]
                 [clojure-opennlp "0.3.3"] ;; uses Opennlp 1.5.3
                ]
  :main quargle.core)
