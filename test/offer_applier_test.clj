(ns offer_applier_test
  (:require [clojure.test :refer :all]
    [clojure.data.json :as json]))

(deftest apply-offer-to-product
  (testing "This test applies any offer to any product only"
  (is (= (apply_offer [offer product]) "{
      \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
      \"offer_code\": \"OF0002\",
      \"discount\": {
          \"type\": \"PERCENTAGE\",
          \"value\": \"15\"
      }
  }")
