(ns rule_applier_test
  (:require [clojure.test :refer :all]
            [rule_applier :refer :all]
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
    "type" "EQUALS"
    "field" "PRODUCT.brand.code"
    "value" "Z001ABCC"
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

(def product1
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



(add_rules [rule1 rule2 rule3 rule4 rule5 rule6 rule7])

(deftest get-rule-test
  (is (= (get_rule "PRICE_LOWER_10000") rule4))
)

(def codes ["PAGO_CAPRO" "PRODUCTO_NO_PHILLEP"])

(deftest apply-rules-test
  (is (= (apply_rules codes product1) [true false]))
)
