(ns quargle.comparison
  (:require [quargle.compare.exact]))

(def comparisons [quargle.compare.exact/process]
  )

(defn process 
  "take a vector representing the quora questions which
   can be one of two formats

   training data will be:
      row number
      question 1 id
      question 2 id
      question 1
      question 2
      is_duplicate

   testing data will be:
     test_id
     question 1
     question 2

   function should return a vector of 
     row number(test_id)
     computed is_duplicate
     is_duplicate (if it exists)"
  [[row_number & others]]

  (let [[q1 q2]  (if (> (count others) 2)
                   [(nth others 2) (nth others 3)]
                   [(nth others 0) (nth others 1)])
        expected (if (> (count others) 2) (Integer/parseInt (nth others 4)) 0)
        ]
    [row_number
    (if (some #(% q1 q2) comparisons) 1 0)
    expected]
    ))

    
