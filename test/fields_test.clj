(ns fields_test
  (:require  [clojure.test :refer :all]
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


(deftest get-field-calendar-test
  (testing "field calendar"
  (is (= (get_field rule1 prod1) "SEPTIEMBRE")))
)
