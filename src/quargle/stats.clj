(ns quargle.stats
  (:require [clojure.string :as s]
            [quargle.wordmatch :as wm]
  ))

(defn reset []
  (def statatom (atom {0 {:correct 0 :incorrect 0}
                    1 {:correct 0 :incorrect 0}})
))

(defn get-stat
  "get count for answer value v and the result r, either :correct or :incorrect"
  [v r]
  (r (get @statatom v))
)

(defn increment
  "Increment the count of for the expected result"
  [expected actual]
  (if (= expected actual) 
    (swap! statatom update-in [expected :correct] inc)
    (swap! statatom update-in [expected :incorrect] inc)

    ))

(defn print-stats []
  (str 
  (apply str 
      (map #(let [m (get @statatom %)
                  correct (:correct m)
                  incorrect (:incorrect m)
                  percent-correct (/ correct (+ correct incorrect 0.0))
                 ]
             (str % " :: " (:correct m) " correct, " (:incorrect m) " incorrect  " percent-correct "%\r\n")) (keys @statatom))
  )
  (let [totals (reduce (fn [v i] (let [m (get @statatom i)
                          correct (:correct m)
                          incorrect (:incorrect m)
                          combo [correct incorrect]
                         ]
                      (vec (map + v combo)))) [0 0] (keys @statatom))
        ]
      (str "total percentage correct " (/ (first totals) (apply + totals) 1.0)))
  )
)


(defn process
  "take a vector of data representing the delimited input data return a vector of the row number and the result"
  [w]
  ;;[(first w)  0 (Integer/parseInt (last w))]
  (wm/process w)
)
