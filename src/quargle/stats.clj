(ns quargle.stats
  (:require [clojure.string :as s]))

(defn reset []
  (def stats (atom {0 {:correct 0 :incorrect 0}
                    1 {:correct 0 :incorrect 0}})
))

(defn get-stat
  "get count for answer value v and the result r, either :correct or :incorrect"
  [v r]
  (r (get @stats v))
)

(defn increment
  "Increment the count of for the expected result"
  [expected actual]
  (if (= expected actual) 
    (swap! stats update-in [expected :correct] inc)
    (swap! stats update-in [expected :incorrect] inc)

    ))

(defn print-stats []
  (apply str 
      (map #(let [m (get @stats %)]
             (str % " :: " (:correct m) " correct, " (:incorrect m) " incorrect\r\n")) (keys @stats))
  )
)


(defn process
  "take a vector of data representing the delimited input data return a vector of the row number and the result"
  [w]
  [(first w)  0 (Integer/parseInt (last w))])
