(ns unit_testing
  (:require [clojure.test :refer :all]))

(deftest translate-some-month
  (testing "Test translations applied to SEPTEMBER"
  (is (= (translations "SEPTEMBER") "SEPTIEMBRE"))))
