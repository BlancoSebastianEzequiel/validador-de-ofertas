(ns fields
  (require [clojure.string :as str]
           [exceptions :refer :all]
           [translations :refer [translate]
                         :rename {translate translate_month}
           ]
  )
)

(defmulti is_month
  (fn [path field] (= path ["purchase_date" "month"]))
)
(defmethod is_month true [path field]
  (translate_month field)
)
(defmethod is_month false [path field] field)

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
  (let
    [
      path (rule_field_path rule)
      field (get-in product path)
      field_translation (is_month path field)
    ]
    (check_field field_translation "invalid field")
  )
)
