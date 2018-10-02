(ns fields
  (require [clojure.string :as str]
  )
)

(defmulti translate (fn [value] value))
(defmethod translate "PRODUCT" [value] "products")
(defmethod translate "CALENDAR" [value] "purchase_date")
(defmethod translate "PAYMENT" [value] "payment")

(defn get_path [rule]
  (str/split (rule "field") #"\.")
)

(defn rule_field_path [rule]
  ; Translate the first value of the path in order to fit with our structure
  (assoc (get_path rule) 0 (translate (first (get_path rule))))
)

(defn get_field [product rule]
  ; Fetch the value in the path given from rule_field_path
  (get-in product (rule_field_path rule))
)