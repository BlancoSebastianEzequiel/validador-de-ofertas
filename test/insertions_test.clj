(ns insertions_test
  (:require [clojure.test :refer :all]
            [insertions :refer :all]
            [convertions :refer :all]

  )
)


(def add_simple_offer
    (map_to_json [{
  "description" "10% de descuento en lácteos durante Septiembre"
  "code" "OF0001"
  "rule" {
          "type" "AND"
          "code" "LACTEO_SEPTIEMBRE"
          "rules" ["PRODUCTO_LACTEO" "MES_SEPTIEMBRE"]
          }
  "discount" { "type" "PERCENTAGE" "value" "10" }
  }
  ])
)

(def add_simple_rule
   (map_to_json [
      {
        "code" "PAGO_CAPRO"
        "description" "Pago con tarjeta de banco macro"
        "type" "EQUALS"
        "field" "PAYMENT.bank"
        "value" "CAPRO"
    }
    ])
)
(def offer_duplicated
  [ {"code" "75.10"} {"code" "75.10"} ]
)

(deftest add_rule_test
  (let
    [rule_test  (add_rule add_simple_rule)]
    (is (= rules_vector rule_test))
  )
)

(deftest add_offer_test
  (let
    [offer_test  (add_offer add_simple_offer)]
    (is (= offers_vector offer_test))
  )
)

(deftest offer_duplicated_test
    (is (thrown? Exception (add_offer offer_duplicated)))
  )

  (deftest rule_duplicated_test
      (is (thrown? Exception (add_rule offer_duplicated)))
    )
