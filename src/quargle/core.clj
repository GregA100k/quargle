(ns quargle.core
  (:require [clojure.java.io :as io]
            [clojure-csv.core :as csv]
            [quargle.comparison :as q]
            [quargle.stats :as sc]))

(defn process-test-file 
  "function to run each line in the input file through a function f to produce the output file"
  [f inputfile outputfile]
  (with-open [in (java.io.BufferedReader. (java.io.FileReader. inputfile))
              out (io/writer outputfile)]
    (let [seq (rest (csv/parse-csv in))  ; skip the header line
          output-header (str "test_id,is_duplicate\r\n")
          write-header (.write out output-header)]
      (loop [s seq]
        (when (first s)
          (do
            (let [result (f (first s))]
              (.write out (str (first result) "," (second result))))
            (.write out "\r\n")
            (recur (rest s))))))))

(defn process-training-file
  "function to run each line fo the input file through a function f to produce the output file with a side effect of also printing statistics about the run"
  [f inputfile outputfile]
  (with-open [in (java.io.BufferedReader. (java.io.FileReader. inputfile))
              out (io/writer outputfile)
              ]
    (let [seq (rest (csv/parse-csv in)) ; skip the header line
          output-header (str "test_id, is_duplicate")
          ]
      (loop [s seq]
        (when (first s)
          (do
            (let [result (f (first s))
                  row-num (first result)
                  row-result (second result)
                  expected-result (nth result 2)
                 ]
              (.write out (str row-num "," row-result))
            (.write out "\r\n")
            (sc/increment expected-result row-result)
            (recur (rest s))))
         )))))



(defn -main [& args]
  (if (< (count args) 3)
    (println "Must provide test|train inputfile and outputfile as command line args")
    (let [mode (first args)
          infile (second args)
          outfile (nth args 2)]
      (cond
        (= mode "test")  (process-test-file q/process infile outfile)
        (= mode "train") (do
                           (sc/reset)
                           (process-training-file q/process infile outfile)
                           (println (sc/print-stats)))
      ))))
