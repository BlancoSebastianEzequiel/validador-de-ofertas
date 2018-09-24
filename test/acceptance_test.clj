(ns acceptance-test
  (:require [clojure.test :refer :all]
            [offer-processor :refer :all]
            [data-definitions :refer :all]))

(defn common-state []
  (initialize-offers common-offers common-rules))

(deftest first-sale-test
  (is (= first-sale-result
         (->> first-sale
              (process-sale (common-state))
              normalize-data))))

(deftest second-sale-test
  (is (= second-sale-result
         (->> second-sale
              (process-sale (common-state))
              normalize-data))))

(deftest unknown-id-test
  (testing "Referencing an unknown id causes some exception"
    (is (thrown? Exception
                 (initialize-offers common-offers "[]")))))

(deftest bad-payment-test
  (testing "Bad payment causes some exception"
    (is (thrown? Exception
                 (process-sale (common-state)
                               bad-payment-sale)))))

(deftest parse-error-test
  (testing "Incorrect JSON causes some exception"
    (is (thrown? Exception
                 (initialize-offers (str "[" common-offers)
                                    common-rules)))))

(deftest cycle-id-test
  (testing "Reference cycle in rules causes some exception"
    (let [cycle-id-offers (normalize-data "[
        {
            \"description\": \"Por una contradiccion, todo es gratis\",
            \"code\": \"OF0001\",
            \"rule\": \"X\",
            \"discount\": {
                \"type\": \"PERCENTAGE\",
                \"value\": \"100\"
            }
        }
    ]")
          cycle-id-rules (normalize-data "[
        {
            \"type\": \"NOT\",
            \"code\": \"X\",
            \"rules\": \"NOT_X\"
        },{
            \"type\": \"NOT\",
            \"code\": \"NOT_X\",
            \"rules\": \"X\"
        }
    ]")]
      (is (thrown? Exception
                   (initialize-offers cycle-id-offers
                                      cycle-id-rules))))))

(deftest duplicate-code-test
  (testing "Duplicate rule codes cause some exception"
    (let [duplicate-id-offers (normalize-data "[
        {
            \"description\": \"Bajo ciertas condiciones, todo es gratis\",
            \"code\": \"OF0001\",
            \"rule\": \"X\",
            \"discount\": {
                \"type\": \"PERCENTAGE\",
                \"value\": \"100\"
            }
        }
    ]")
          duplicate-id-rules (normalize-data "[
        {
            \"code\": \"PRICE_RULE\",
            \"description\": \"Producto con precio menor a 10000\",
            \"type\": \"LOWER\",
            \"field\": \"PRODUCT.price\",
            \"value\": 10000
        },
        {
            \"code\": \"PRICE_RULE\",
            \"description\": \"Producto con precio menor a 50000\",
            \"type\": \"HIGHER\",
            \"field\": \"PRODUCT.price\",
            \"value\": 50000
        }
    ]")]
      (is (thrown? Exception
                   (initialize-offers duplicate-id-offers
                                      duplicate-id-rules))))))
