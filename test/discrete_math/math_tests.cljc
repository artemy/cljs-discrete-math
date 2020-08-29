(ns discrete-math.math-tests
  (:require [clojure.test :refer [deftest is run-tests]]
            [discrete-math.math :refer [create-set compose inverse transitive]]))

(deftest set-creation
  (let
   [expected [[1 1] [1 2] [1 3] [2 1] [2 2] [3 1]]
    function (fn [x y] (> 5 (+ x y)))]
    (is (= expected (create-set function)))))

(deftest composition
  (let
   [set1 [[1 3] [2 2] [2 3] [2 4] [3 1] [3 2]
          [3 3] [3 4] [3 5] [4 2] [4 3] [4 4] [5 3]]
    set2 [[1 1] [1 2] [1 3] [2 1] [2 2] [3 1]]
    expected [[1 1] [2 1] [2 2] [3 1] [3 2] [3 3] [4 1] [4 2] [5 1]]]
    (is (= expected (compose set1 set2)))))

(deftest inverse-relation
  (let
   [set [[1 3] [2 4] [5 2]]
    expected [[2 5] [3 1] [4 2]]]
    (is (= expected (inverse set)))))

(deftest transitive-relation
  (let
   [set [[1 1] [1 2] [1 3] [2 1] [2 2] [3 1]]
    expected [[1 1] [1 2] [1 3] [2 1] [2 2] [2 3] [3 1] [3 2] [3 3]]]
    (is (= expected (transitive set)))))

(run-tests)