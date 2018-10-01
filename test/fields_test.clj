(ns fields_test
  (:require [clojure.test :refer :all]
            [fields :refer :all]
  )
)

(def rule1
  {
    "code" "MES_SEPTIEMBRE"
    "description" "EL MES ES SEPTIEMBRE"
    "type" "EQUALS"
    "field" "CALENDAR.month"
    "value" "SEPTIEMBRE"
  }
)

(def rule2
  {
    "code" "PRODUCTO_LACTEO"
    "description" "PRODUCTO ES CATEGORIA LACTEO"
    "type" "EQUALS"
    "field" "PRODUCT.category.code"
    "value" "X033AXX"
  }
)

(def rule3
  {
    "code" "ELECTRO_LIQ"
    "description" "PRODUCTO CON DESCUENTO ESPECIAL"
    "type" "IN"
    "field" "PRODUCT.code"
    "value" ["X033XXX" "X034XXX" "X037XXX"]
  }
)

(def rule4
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

(def rule6
  {
    "code" "PRODUCTO_NO_PHILLEP"
    "description" "Producto no es marca Phillep"
    "type" "DISTINCT"
    "field" "PRODUCT.brand.code"
    "value" "Phillep"
  }
)


(def rule7
  {
    "code" "PAGO_CAPRO"
    "description" "Pago con tarjeta de banco macro"
    "type" "EQUALS"
    "field" "PAYMENT.bank"
    "value" "CAPRO"
  }
)

(def prod1
  {
    "products" {
      "name" "Leche Descremada 1L, la Calmisima"
      "brand" { "code" "Z001ABC" "name" "La Calmisima" }
      "category" { "code" "X033AXX" "name" "Lacteo" }
      "price"  25.40
      "iva_porcentage" 10.5
      "code" "X033XXX"
    }
    "payment" { "method" "CASH" "bank" "CAPRO" }
    "purchase_date" {
        "year" "2018"
        "month" "SEPTIEMBRE"
        "day_number" 20
        "week_day" "Thursday"
        "week_number" 4
    }
  }
)


(deftest get-field-rule1-test
  (testing "field rule1"
  (is (= (get_field prod1 rule1) "SEPTIEMBRE")))
)

(deftest get-field-rule2-test
  (testing "field rule2"
  (is (= (get_field prod1 rule2) "X033AXX")))
)

(deftest get-field-rule3-test
  (testing "field rule3"
  (is (= (get_field prod1 rule3) "X033XXX")))
)

(deftest get-field-rule4-test
  (testing "field rule4"
  (is (= (get_field prod1 rule4) 25.40)))
)

(deftest get-field-rule5-test
  (testing "field rule5"
  (is (= (get_field prod1 rule5) "CASH")))
)

(deftest get-field-rule6-test
  (testing "field rule6"
  (is (= (get_field prod1 rule6) "Z001ABC")))
)

(deftest get-field-rule7-test
  (testing "field rule7"
  (is (= (get_field prod1 rule7) "CAPRO")))
)
