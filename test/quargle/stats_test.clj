(ns quargle.stats-test
  (:require [clojure.test :refer :all]
            [quargle.core :refer :all]
            [quargle.stats :refer :all :as sc]
))

(deftest basic-stats-test
  (testing "incrementing the statistics atom"
    (let [stats (sc/reset)
         ]
      (is 
          (= 0 (sc/get-stat 0 :correct))))
  )
  (testing "increment 0"
    (let [stats (sc/reset)
          increment-the-stats (sc/increment 0 0)
         ]
      (is (= 1 (sc/get-stat 0 :correct)))
  ))
)

(deftest printstats
  (testing "print stat string"
    (let [stats (sc/reset)
          i (sc/increment 0 0)
         ]
      (is (= (sc/print-stats) "0 :: 1 correct, 0 incorrect  1.0%\r\n1 :: 0 correct, 0 incorrect  NaN%\r\ntotal percentage correct 1.0"))
  )))
