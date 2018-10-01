(ns unit_testing
  (:require [clojure.test :refer :all]
    [translations :refer :all]
    [convertions :refer :all]))

(deftest translate-some-month
  (testing "Test translations applied to SEPTEMBER"
  (is (= (translate "SEPTEMBER") "SEPTIEMBRE"))))

(deftest convert-to-empty-vector
  (testing "Test convert a json empty vector to vector"
  (is (= (json_to_map "[]") []))))

(deftest convert-to-empty-vector-string
  (testing "Test convert empty vector to json"
  (is (= (map_to_json []) "[]"))))
