(ns quargle.comparison-test
  (:require [clojure.test :refer :all]
            [quargle.comparison :refer :all]))

(deftest default-comparisons
  (testing "simple comparison"
    (let [ q1 "test question?"
          q2 "test question2?"
          w [1 q1 q2]
         ]
      (is 
          (= [1 0 0] (process w)))
      )))
