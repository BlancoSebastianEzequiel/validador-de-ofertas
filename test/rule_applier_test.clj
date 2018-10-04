(ns rule_applier_test
  (:require [clojure.test :refer :all]
            [rule_applier :refer :all]
  )
)

(def rule_1
  {
    "code" "MES_SEPTIEMBRE"
    "description" "EL MES ES SEPTIEMBRE"
    "type" "EQUALS"
    "field" "CALENDAR.month"
    "value" "SEPTIEMBRE"
  }
)

(def rule_2
  {
    "code" "PRODUCTO_LACTEO"
    "description" "PRODUCTO ES CATEGORIA LACTEO"
    "type" "EQUALS"
    "field" "PRODUCT.category.code"
    "value" "X033AXX"
  }
)

(def rule_3
  {
    "code" "ELECTRO_LIQ"
    "description" "PRODUCTO CON DESCUENTO ESPECIAL"
    "type" "IN"
    "field" "PRODUCT.code"
    "value" ["X033XXX" "X034XXX" "X037XXX"]
  }
)

(def rule_5
  {
    "code" "PRICE_LOWER_10000"
    "description" "Producto con precio menor a 10000"
    "type" "LOWER"
    "field" "PRODUCT.price"
    "value" 10000
  }
)

(def rule5
  {
    "code" "PAGO_TARJETA_DEBITO_CREDITO"
    "description" "Pago con tarjeta debito o credito"
    "type" "IN"
    "field" "PAYMENT.method"
    "value" ["CREDIT" "DEBIT"]
  }
)

(def rule_6
  {
    "code" "PRODUCTO_NO_PHILLEP"
    "description" "Producto no es marca Phillep"
    "type" "EQUALS"
    "field" "PRODUCT.brand.code"
    "value" "Z001ABCC"
  }
)


(def rule_7
  {
    "code" "PAGO_CAPRO"
    "description" "Pago con tarjeta de banco macro"
    "type" "EQUALS"
    "field" "PAYMENT.bank"
    "value" "CAPRO"
  }
)

(def rule_8
  {
    "code" "NOT_CAPRO"
    "description" "Pago con tarjeta de banco macro"
    "type" "NOT"
    "rules" "PAGO_CAPRO"
  }
)

(def prod_1
  {
    "products" {
      "name" "Leche Descremada 1L, la Calmisima"
      "brand" { "code" "Z001ABC" "name" "La Calmisima" }
      "category" { "code" "X033AXX" "name" "Lacteo" }
      "price"  25.40
      "iva_porcentage" 10.5
      "code" "X033XXX"
    }
    "payment" {
      "method" "CASH"
      "bank" "CAPRO"
    }
    "purchase_date" {
        "year" "2018"
        "month" "SEPTEMBER"
        "day_number" 20
        "week_day" "Thursday"
        "week_number" 4
    }
  }
)

(def prod_2
  {
    "products" {
      "name" "Leche Descremada 1L, la Calmisima"
      "brand" { "code" "Z001ABC" "name" "La Calmisima" }
      "category" { "code" "X033AXX" "name" "Lacteo" }
      "price"  25.40
      "iva_porcentage" 10.5
      "code" "X033XXX"
    }
    "payment" {
      "method" "CASH"
      "bank" "FRANCES"
    }
    "purchase_date" {
        "year" "2018"
        "month" "SEPTEMBER"
        "day_number" 20
        "week_day" "Thursday"
        "week_number" 4
    }
  }
)



(put_rules [rule_1 rule_2 rule_3 rule_5 rule5 rule_6 rule_7 rule_8])

(deftest get-rule-test
  (is (= (get_rule "PRICE_LOWER_10000") rule_5))
)

(deftest get-not-existence-rule-test
  (is (thrown? Exception (get_rule "DOES_NOT_EXIST")))
)

(def codes1 ["PAGO_CAPRO" "PRODUCTO_NO_PHILLEP"])

(deftest apply-rules-test
  (let
    [
      no_value (put_rules [rule_1 rule_2 rule_3 rule_5 rule5 rule_6 rule_7 rule_8])
    ]
    (is (= (apply_rules codes1 prod_1) [true false]))
  )
)

(def codes2 ["NOT_CAPRO" "PRODUCTO_NO_PHILLEP"])

(deftest apply-not-atomic-rules-test
  (let
    [
      no_value (put_rules [rule_1 rule_2 rule_3 rule_5 rule5 rule_6 rule_7 rule_8])
    ]
    (is (= (apply_rules codes2 prod_2) [true false]))
  )
)
