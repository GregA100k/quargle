(ns quargle.core
  (:require [clojure.java.io :as io]
            [quargle.constant :as q]))

(defn process-test-file 
  "function to run each line in the input file through a function f to produce the output file"
  [f inputfile outputfile]
  (with-open [in (java.io.BufferedReader. (java.io.FileReader. inputfile))
              out (io/writer outputfile)]
    (let [seq (rest (line-seq in))  ; skip the header line
          output-header (str "test_id,is_duplicate\r\n")
          write-header (.write out output-header)]
      (loop [s seq]
        (when (first s)
          (do
            (.write out (f (first s)))
            (.write out "\r\n")
            (recur (rest s))))))))


(defn -main [& args]
  (if (< (count args) 2)
    (println "Must provide inputfile and outputfile as command line args")
    (let [infile (first args)
          outfile (second args)]
      (process-test-file q/process infile outfile))))
