(ns quargle.compare.phrasecompare-test
  (:require [clojure.test :refer :all]
            [quargle.compare.phrasecompare :as pc]
))

(deftest chunk-test
  (testing "that sentences can be broken into chunks"
    (let [q "Why are rockets white?"
          chunks (pc/get-chunks q)
          ]
      (is (= 3 (count chunks)))
  ))

  (testing "the last verb phrase"
    (let [q "Why are rockets painted white"
          chunks (pc/get-chunks q)
          vp (pc/get-last-verb-phrase chunks)]
      (is (= "painted" (first (:phrase vp))))
      (is (= 2 (:index vp)))
    ))

  (testing "comparing phrases"
    (let [p1 {:phrase ["to" "learn"], :tag "VP"}
          ]
      (is (= true (pc/similar-phrase p1 p1)))
    ))
)
