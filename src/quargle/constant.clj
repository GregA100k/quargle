(ns quargle.constant
  (:require [clojure.string :as s]))

(defn process
  "take a string of ID,\"question 1\", \"question 2\" and return a line if ID,[0,1]"
  [s]
  (let [parts (s/split s #",")]
    (str (first parts) "," "1")))
