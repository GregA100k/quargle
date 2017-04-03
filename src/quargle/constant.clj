(ns quargle.constant
  (:require [clojure.string :as s]))

(defn process
  "take a vector of data representing the delimited input data"
  [w]
  (str (first w) "," "1"))
