(ns unit_testing
  (:require [clojure.test :refer :all]
    [translations :refer :all]
    [convertions :refer :all]))

(deftest translate-some-month
  (testing "Test translations applied to SEPTEMBER"
  (is (= (translate "SEPTEMBER") "SEPTIEMBRE"))))
