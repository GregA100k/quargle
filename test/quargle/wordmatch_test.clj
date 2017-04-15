(ns quargle.wordmatch-test
  (:require [clojure.test :refer :all]
            [quargle.core :refer :all]
            [quargle.wordmatch :refer :all :as wm]
))

(deftest question-splitting
  (testing "splitting on spaces"
    (let [str (str "test question with five words")
         ]
      (is 
          (= "test" (first (wm/get-words str)))))
  )
)
