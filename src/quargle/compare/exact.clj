(ns quargle.compare.exact)

(defn cleaned [q]
  (-> q
       .toLowerCase
       (.replaceAll  "[.,?;:\"]" "")
  ))

(defn process [q1 q2]
  (if (= (cleaned q1) (cleaned q2)) 1))

