(ns quargle.opennlp-test
  (:require [clojure.test :refer :all]
            [quargle.compare.exact :refer [process]]
))

(deftest exactmatch
  (testing "the exact same question"
    (let [q1 "Is this a question"
          q2 q1
          ]
      (is (= 1 (quargle.compare.exact/process q1 q2))))
    )
  (testing "different questions"
    (let [q1 "is this a question?"
          q2 "is this different?"
          ]
      (is (nil? (quargle.compare.exact/process q1 q2))))
    )
)

