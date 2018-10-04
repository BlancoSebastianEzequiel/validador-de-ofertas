(ns offer_applier
  (require [rule_applier :refer :all]
           [operators :refer :all]
  )
)

(defn pass_rules [r] (put_rules r))

(defn str_to_double [s] (Double/parseDouble s))

(defn apply_discount [offer price]
  ;; price * p / 100
  (let
    [ p (str_to_double ((offer "discount") "value")) ]
    (.floatValue (* price (/ p 100)))
  )
)

(defn get_offer_result [offer products]
  (vec
    (for [p products]
      {
        "description" (offer "description")
        "offer_code" (offer "code")
        "discount" (apply_discount offer ((p "products") "price"))
      }
    )
  )
)

(defn get_products_that_met_the_rules [offer sale]
  (vec
    (for
      [
        p (sale "products")
        :let
        [
          a {"products" p}
          b {"payment" (sale "payment")}
          c {"purchase_date" (sale "purchase_date")}
          prod (merge a b c)
          op ((offer "rule") "type")
          rules_code ((offer "rule") "rules")
          results (apply_rules rules_code prod)
        ]
        :when (apply_op op results nil)
      ]
      prod
    )
  )
)

(defn apply_offer [offer sale]
  (let
    [ products (get_products_that_met_the_rules offer sale) ]
    (get_offer_result offer products)
  )
)
