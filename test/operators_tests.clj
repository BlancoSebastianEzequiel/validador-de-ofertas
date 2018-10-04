(ns operators_tests
  (:require [clojure.test :refer :all]
            [operators :refer :all]
  )
)

(deftest apply-equal-test
  (is (= (apply_op "EQUALS" [2 2] [2 2]) true))
)
(deftest apply-and-test
  (is (= (apply_op "AND" [false true] nil) false))
)
(deftest apply-or-test
  (is (= (apply_op "OR" [false true] nil) true))
)
(deftest apply-in-test
  (is (= (apply_op "IN" [2 3 4 5 6] 5) true))
)
(deftest apply-lower-test
  (is (= (apply_op "LOWER" 6 5) true))
)
(deftest apply-higher-test
  (is (= (apply_op "HIGHER" 4 5) true))
)
(deftest apply-not-test
  (is (= (apply_op "NOT" [true] nil) false))
)
