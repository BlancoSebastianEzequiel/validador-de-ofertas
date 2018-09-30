(ns fields
  ; (require '[clojure.string :as str])
)

; (str/split "Clojure is awesome!" #" ")

(defmulti get_field (fn [rule prod] (rule "field")))
(defmethod get_field "PRODUCT.category.code" [rule prod]
  (let
    [
      field (((prod "products") "category") "code")
    ]
  )
)
(defmethod get_field "PRODUCT.brand.code" [rule prod]
  (let
    [
      field (((prod "products") "brand") "code")
    ]
  )
)
(defmethod get_field "PRODUCT.code" [rule prod]
  (let
    [
      field ((prod "products") "code")
    ]
  )
)
(defmethod get_field "PRODUCT.price" [rule prod]
  (let
    [
      field ((prod "products") "price")
    ]
  )
)
(defmethod get_field "CALENDAR.month" [rule prod]
  (let
    [
      field ((prod "purchase_date") "month")
    ]
  )
)
(defmethod get_field "PAYMENT.bank" [rule prod]
  (let
    [
      field ((prod "payment") "bank")
    ]
  )
)
(defmethod get_field "PAYMENT.method" [rule prod]
  (let
    [
      field ((prod "payment") "method")
    ]
  )
)
