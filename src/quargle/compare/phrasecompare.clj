(ns quargle.compare.phrasecompare
  (:require [opennlp.nlp :as nlp]
            [opennlp.treebank :as nlptb]
            [clojure.set :as set]
))

(def get-sentences (nlp/make-sentence-detector "resources/en-sent.bin"))
(def tokenize (nlp/make-tokenizer "resources/en-token.bin"))
(def pos-tag (nlp/make-pos-tagger "resources/en-pos-maxent.bin"))
(def chunker (nlptb/make-treebank-chunker "resources/en-chunker.bin"))

(defn get-chunks [q]
   (chunker (pos-tag (tokenize q))))

(defn get-last-verb-phrase 
  "find the last verb phrase identified by the :tag VP, in the sequence of phrase maps"
  [s]
  (let [indexed-phrases (map-indexed #(assoc %2 :index %1) s)]
    (last (filter #(= "VP" (:tag %)) indexed-phrases))))

(defn similar-phrase 
  "compare the words in the :phrase vector of each phrase map and return true if they have a match"
  [p1 p2]
  (let [v1 (:phrase p1)
        v2 (:phrase p2)]
    (if (not-empty (set/intersection (set v1) (set v2))) true false)
))

(defn process 
  "compare the two questions by parsing them into phrases and comparing
  the last noun phrase, verb phrase, noun phase"
  [q1 q2]
  (let [q1-chunks (get-chunks (if (= \? (last q1)) q1 (str q1 "?")))
        q2-chunks (get-chunks (if (= \? (last q2)) q2 (str q2 "?")))
        ;q1-verb  (get-last-verb-phrase q1-chunks)
        ;q2-verb  (get-last-verb-phrase q2-chunks)
       ]
    (if (similar-phrase (last q1-chunks) (last q2-chunks)) 1)
))
