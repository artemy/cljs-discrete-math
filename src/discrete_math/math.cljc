(ns discrete-math.math)

(defn generate-sequence
  ([] (generate-sequence 5))
  ([len] (range 1 (+ len 1))))

(defn create-set
  ([] (create-set (fn [& _] '(true))))
  ([function] (create-set 5 5 function))
  ([x y function] (for [x (generate-sequence x)
                        y (generate-sequence y)
                        :when (function x y)]
                    [x y])))

(defn compose [set1 set2]
  (sort (distinct (for [[x1 y1] set1
                        [x2 y2] set2
                        :when (= y1 x2)]
                    [x1 y2]))))

(defn inverse [set]
  (sort (distinct (for [[x y] set]
                    [y x]))))

(defn transitive [set] (compose set set))