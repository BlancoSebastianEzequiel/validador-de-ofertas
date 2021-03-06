(ns offer_applier_test
  (:require [clojure.test :refer :all]
            [offer_applier :refer :all]
            [convertions :refer :all]
  )
)

(def offer1
  {
    "description" "10% de descuento en lácteos durante Septiembre"
    "code" "OF0001"
    "rule" {
      "type" "AND"
      "code" "LACTEO_SEPTIEMBRE"
      "rules" ["PRODUCTO_LACTEO" "MES_SEPTIEMBRE"]
    }
    "discount" {
        "type" "PERCENTAGE"
        "value" "10"
    }
  }
)

(def competiton_offer
  {
    "description" "15 pesos de descuento llevando dos Leche Descremada1L, la Calmisima"
    "code" "OF0003"
    "rule" {
      "type" "EXCLUSIVE_AND"
      "code" "DOS_L_LA_CALMISIMA"
      "rules" ["PRODUCTO_LA_CALMISIMA_LECHE_1" "PRODUCTO_LA_CALMISIMA_LECHE_1"]
    }
    "discount" {
      "type" "FIXED"
      "value" "15"
    }
  }
)

(def sale1
  "
  {
      \"products\": [
          {
              \"name\": \"Leche Descremada 1L, la Calmisima\",
              \"brand\": {
                  \"code\": \"Z001ABC\",
                  \"name\": \"La Calmisima\"
              },
              \"category\": {
                  \"code\": \"X033AXX\",
                  \"name\": \"Lacteo\"
              },
              \"price\": 25.40,
              \"iva_porcentage\": 10.5,
              \"code\": \"AAR001\"
          },
          {
              \"name\": \"Leche Descremada 1L, Corsan\",
              \"brand\": {
                  \"code\": \"Z002ABC\",
                  \"name\": \"Corsan\"
              },
              \"category\": {
                  \"code\": \"X033AXX\",
                  \"name\": \"Lacteo\"
              },
              \"price\": 24.40,
              \"iva_porcentage\": 10.5,
              \"code\": \"AAR002\"
          }
      ],
      \"payment\": {
          \"method\": \"CREDIT\",
          \"bank\": \"PARISIA\"
      },
      \"purchase_date\": {
          \"year\": \"2018\",
          \"month\": \"SEPTEMBER\",
          \"day_number\": 20,
          \"week_day\": \"Thursday\",
          \"week_number\": 4
      }
    }
  "
)

(def sale_competition
  (map_to_json
    {
      "products" [
        {
          "name" "Leche Descremada 1L, la Calmisima"
          "brand" {
            "code" "Z001ABCD"
            "name" "La Calmisima"
          }
          "category" {
            "code" "X033AXX"
            "name" "Lacteo"
          }
          "price" 25.40
          "iva_porcentage" 10.5
          "code" "AAR001"
        }
        {
          "name" "Leche Descremada 1L, la Calmisima"
          "brand" {
            "code" "Z001ABCD"
            "name" "La Calmisima"
          }
          "category" {
            "code" "X033AXX"
            "name" "Lacteo"
          }
          "price" 25.40
          "iva_porcentage" 10.5
          "code" "AAR001"
        }
        {
          "name" "Leche Descremada 1L, la Calmisima"
          "brand" {
            "code" "Z001ABCD"
            "name" "La Calmisima"
          }
          "category" {
            "code" "X033AXX"
            "name" "Lacteo"
          }
          "price" 25.40
          "iva_porcentage" 10.5
          "code" "AAR001"
        }
        {
          "name" "Leche Descremada 1L, la Calmisima"
          "brand" {
            "code" "Z001ABCD"
            "name" "La Calmisima"
          }
          "category" {
            "code" "X033AXX"
            "name" "Lacteo"
          }
          "price" 25.40
          "iva_porcentage" 10.5
          "code" "AAR001"
        }
        {
          "name" "Leche Descremada 1L, la Calmisima"
          "brand" {
            "code" "Z001ABCD"
            "name" "La Calmisima"
          }
          "category" {
            "code" "X033AXX"
            "name" "Lacteo"
          }
          "price" 25.40
          "iva_porcentage" 10.5
          "code" "AAR001"
        }
    ]
      "payment" {
          "method" "CREDIT"
          "bank" "PARISIA"
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
)

(def result1
  [
    {
      "description" "10% de descuento en lácteos durante Septiembre"
      "offer_code" "OF0001"
      "discount" 2.54
    }
    {
      "description" "10% de descuento en lácteos durante Septiembre"
      "offer_code" "OF0001"
      "discount" 2.44
    }
  ]
)

(def rules
  "[
    {
        \"code\": \"MES_SEPTIEMBRE\",
        \"description\": \"EL MES ES SEPTIEMBRE\",
        \"type\": \"EQUALS\",
        \"field\": \"CALENDAR.month\",
        \"value\": \"SEPTIEMBRE\"
    },
    {
        \"code\": \"PRODUCTO_LACTEO\",
        \"description\": \"PRODUCTO ES CATEGORIA LACTEO\",
        \"type\": \"EQUALS\",
        \"field\": \"PRODUCT.category.code\",
        \"value\": \"X033AXX\"
    },
    {
      \"code\": \"PRODUCTO_LA_CALMISIMA_LECHE_1\",
      \"description\": \"Producto es Leche descremanda la calmisima por 1L\",
      \"type\": \"EQUALS\",
      \"field\": \"PRODUCT.brand.code\",
      \"value\": \"Z001ABCD\"
    }
  ]"
)

(def result_competition
  [
    {
      "description" "15 pesos de descuento llevando dos Leche Descremada1L, la Calmisima"
      "offer_code" "OF0003"
      "discount" 15
    }
    {
      "description" "15 pesos de descuento llevando dos Leche Descremada1L, la Calmisima"
      "offer_code" "OF0003"
      "discount" 15
    }
  ]
)

(deftest apply-offer-to-product
  (testing "This test applies any offer to any product only"
    (let
      [
        no_value (pass_rules (json_to_map rules))
        result (map_to_json (apply_offer offer1 (json_to_map sale1)))
        expected_result (map_to_json result1)
      ]
      (is (= result expected_result))
    )
  )
)

(def sale (json_to_map sale1))

(deftest apply-discount-to-product
  (testing "This test applies a discount to product"
  (is (= (apply_discount offer1 ((nth (sale "products") 0) "price")) (float 2.54)))))

(deftest competition-test
  (testing "This test"
    (let
      [
        no_value (pass_rules (json_to_map rules))
        result (map_to_json (apply_offer competiton_offer (json_to_map sale_competition)))
        expected_result (map_to_json result_competition)
      ]
      (is (= result expected_result))
    )
  )
)
