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



(add_rules [rule1 rule2 rule3 rule4])

(deftest apply-equal-test
  (is (= (get_rule "PRICE_LOWER_10000") rule4))
)
