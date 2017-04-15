(ns quargle.wordmatch
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(defn get-words [str]
  (s/split str #" "))

(defn process
  "take a vector of data representing the delimited input data return a vector of the row number and the result"
  [w]
  (let [[q1 q2]  (if (> (count w) 3)
                   [(nth w 3) (nth w 4)]
                   [(nth w 1) (nth w 2)])
        q1-words (get-words q1)
        q2-words (get-words q2)
        q1-set (into #{} q1-words)
        q2-set (into #{} q2-words)
        intersect (set/intersection q1-set q2-set)
       ]

   [(first w)  (if (> (count intersect) 4) 1 0) (if (> (count w) 3) (Integer/parseInt (last w)))]
    ))
