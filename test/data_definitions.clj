(ns data-definitions
  (:require [clojure.data.json :as json]))

(defn normalize-data [j]
  (json/write-str (json/read-str j)))

;; atomic rules
(def common-rules (normalize-data "[
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
            \"code\": \"ELECTRO_LIQ\",
            \"description\": \"PRODUCTO CON DESCUENTO ESPECIAL\",
            \"type\": \"IN\",
            \"field\": \"PRODUCT.code\",
            \"value\": [\"X033XXX\", \"X034XXX\", \"X037XXX\"]
        },
        {
            \"code\": \"PRICE_LOWER_10000\",
            \"description\": \"Producto con precio menor a 10000\",
            \"type\": \"LOWER\",
            \"field\": \"PRODUCT.price\",
            \"value\": 10000
        },
        {
            \"code\": \"PRICE_HIGHER_50000\",
            \"description\": \"Producto con precio menor a 50000\",
            \"type\": \"HIGHER\",
            \"field\": \"PRODUCT.price\",
            \"value\": 50000
        },
        {
            \"code\": \"PRODUCTO_NO_PHILLEP\",
            \"description\": \"Producto no es marca Phillep\",
            \"type\": \"DISTINCT\",
            \"field\": \"PRODUCT.brand.code\",
            \"value\": \"Phillep\"
        },
        {
            \"code\": \"PAGO_CAPRO\",
            \"description\": \"Pago con tarjeta de banco macro\",
            \"type\": \"EQUALS\",
            \"field\": \"PAYMENT.bank\",
            \"value\": \"CAPRO\"
        },
        {
            \"code\": \"PAGO_TARJETA_DEBITO_CREDITO\",
            \"description\": \"Pago con tarjeta debito o credito\",
            \"type\": \"IN\",
            \"field\": \"PAYMENT.method\",
            \"value\": [\"CREDIT\", \"DEBIT\"]
        },
        {
            \"code\": \"PAGO_EFECTIVO\",
            \"description\": \"Pago en efectivo\",
            \"type\": \"EQUALS\",
            \"field\": \"PAYMENT.method\",
            \"value\": \"CASH\"
        },
        {
            \"type\": \"AND\",
            \"code\": \"PAGO_TARJETA_CAPRO\",
            \"rules\": [\"PAGO_CAPRO\", \"PAGO_TARJETA_DEBITO_CREDITO\"]
        },
        {
            \"type\": \"OR\",
            \"code\": \"ELECTRO_LIQ_TARJETA_MACRO\",
            \"rules\": [\"ELECTRO_LIQ\", \"PAGO_EFECTIVO\"]
        },
        {
            \"type\": \"NOT\",
            \"code\": \"NO_CAPRO\",
            \"rules\": \"PAGO_CAPRO\"
        }
    ]"))

;; offers
(def common-offers (normalize-data "[
        {
            \"description\": \"10% de descuento en lácteos durante Septiembre\",
            \"code\": \"OF0001\",
            \"rule\": {
                \"type\": \"AND\",
                \"code\": \"LACTEO_SEPTIEMBRE\",
                \"rules\": [\"PRODUCTO_LACTEO\", \"MES_SEPTIEMBRE\"]
            },
            \"discount\": {
                \"type\": \"PERCENTAGE\",
                \"value\": \"10\"
            }
        },
        {
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"code\": \"OF0002\",
            \"rule\": {
                \"type\": \"AND\",
                \"code\": \"PAGO_TARJETAS_NO_CAPRO\",
                \"rules\": [\"PAGO_TARJETA_DEBITO_CREDITO\", \"NO_CAPRO\"]
            },
            \"discount\": {
                \"type\": \"PERCENTAGE\",
                \"value\": \"15\"
            }
        }
    ]"))

;; SHOPPING_CART
(def first-sale (normalize-data "{
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
        },
        {
            \"name\": \"Gaseosa Cola\",
            \"brand\": {
                \"code\": \"Z003ABC\",
                \"name\": \"Cola\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 14.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR003\"
        },
        {
            \"name\": \"Gaseosa Naranja\",
            \"brand\": {
                \"code\": \"Z003ABC\",
                \"name\": \"Cola\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 13.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR004\"
        },
        {
            \"name\": \"Gaseosa Limon\",
            \"brand\": {
                \"code\": \"Z003ABC\",
                \"name\": \"Cola\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 11.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR005\"
        },
        {
            \"name\": \"Gaseosa Cola\",
            \"brand\": {
                \"code\": \"Z004ABC\",
                \"name\": \"Sipse\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 12.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR006\"
        },
        {
            \"name\": \"Gaseosa Limon\",
            \"brand\": {
                \"code\": \"Z004ABC\",
                \"name\": \"Sipse\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 14.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR007\"
        },
        {
            \"name\": \"Agua Mineral\",
            \"brand\": {
                \"code\": \"Z004ABC\",
                \"name\": \"Natura\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 8.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR008\"
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
  }"))

;; Results
(def first-sale-result (normalize-data "[
        {
            \"description\": \"10% de descuento en lácteos durante Septiembre\",
            \"offer_code\": \"OF0001\",
            \"discount\": 2.54
        },
        {
            \"description\": \"10% de descuento en lácteos durante Septiembre\",
            \"offer_code\": \"OF0001\",
            \"discount\": 2.44
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 3.81
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 3.66
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 2.16
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 2.01
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 1.71
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 1.86
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 2.16
        },
        {
            \"offer_code\": \"OF0002\",
            \"description\": \"15% de descuento pagando con credito o debito, excepto banco CAPRO\",
            \"discount\": 1.26
        }
    ]"))

;; SHOPPING_CART
(def second-sale (normalize-data "{
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
        },
        {
            \"name\": \"Gaseosa Cola\",
            \"brand\": {
                \"code\": \"Z003ABC\",
                \"name\": \"Cola\"
            },
            \"category\": {
                \"code\": \"X034AXX\",
                \"name\": \"Bebidas\"
            },
            \"price\": 14.40,
            \"iva_porcentage\": 10.5,
            \"code\": \"AAR003\"
        }
    ],
    \"payment\": {
        \"method\": \"CREDIT\",
        \"bank\": \"CAPRO\"
    },
    \"purchase_date\": {
        \"year\": \"2018\",
        \"month\": \"SEPTEMBER\",
        \"day_number\": 20,
        \"week_day\": \"Thursday\",
        \"week_number\": 4
    }
  }"))

;; Results
(def second-sale-result (normalize-data "[
        {
            \"description\": \"10% de descuento en lácteos durante Septiembre\",
            \"offer_code\": \"OF0001\",
            \"discount\": 2.54
        },
        {
            \"description\": \"10% de descuento en lácteos durante Septiembre\",
            \"offer_code\": \"OF0001\",
            \"discount\": 2.44
        }
    ]"))

;; SHOPPING_CART​
(def bad-payment-sale (normalize-data "{
    \"products\": [{
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
        }
    ],
    \"payment\": {
        \"method\": \"INVALID_METHOD\",
        \"bank\": \"NO_ONE\"
    },
    \"purchase_date\": {
        \"year\": \"2018\",
        \"month\": \"SEPTEMBER\",
        \"day_number\": 20,
        \"week_day\": \"Thursday\",
        \"week_number\": 4
    }
  }"))
