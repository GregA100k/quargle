(ns quargle.compare.exact-test
  (:require [clojure.test :refer :all]
            [quargle.compare.exact :as ex]
))

(deftest string-cleaning
  (testing "removal of punctuation and convert to lower case"
    (let [q "Why are rockets white?"
          ]
      (is (= "why are rockets white" (ex/cleaned q)))
  ))
)
