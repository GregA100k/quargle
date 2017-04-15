(ns quargle.core-test
  (:require [clojure.test :refer :all]
            [quargle.core :refer :all]))

(deftest training-stats
  (testing "incrementing the statistics atom"
    (let [stats (atom {0 {:correct 0 :incorrect 0}
                       1 {:correct 0 :incorrect 0}})
         ]
      (is 
          (= 0 (:correct (get @stats 0))))
      )))
