(ns unit_testing
  (:require [clojure.test :refer :all]
    [translations :refer :all]
    [convertions :refer :all]
    [clojure.data.json :as json]))

(deftest translate-some-month
  (testing "Test translations applied to SEPTEMBER"
  (is (= (translate "SEPTEMBER") "SEPTIEMBRE"))))

(deftest convert-to-empty-vector
  (testing "Test convert a json empty vector to vector"
  (is (= (json_to_map "[]") []))))

(deftest convert-to-empty-vector-string
  (testing "Test convert empty vector to json"
  (is (= (map_to_json []) "[]"))))

(deftest test-access-to-map-value
  (testing "Test to convert json to map and access to any value"
  (is (= (get (json_to_map "{\"a\":4,\"b\":8,\"c\":5}") "b") 8))))
