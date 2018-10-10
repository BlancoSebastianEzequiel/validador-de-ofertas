(ns offer_applier
  (require [rule_applier :refer :all]
           [operators :refer :all]
           [clojure.string :as str]
           [clojure.string :only [index-of]]
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

(defn get_path [rule] (str/split (rule "field") #"\."))

(defmulti get_products_that_met_the_rules
  (fn [offer sale]
    (let
      [
        op (get-in offer ["rule" "type"])
        key_word (first (str/split op #"\_"))

      ]
      (= key_word "EXCLUSIVE")
    )
  )
)

(defn change_product_format [p sale]
  (let
    [
      a {"products" p}
      b {"payment" (sale "payment")}
      c {"purchase_date" (sale "purchase_date")
    ]
    (merge a b c)
  )
)

(defn get_products [sale]
  (vec
    (for
      [ p (get-in sale  ["products"]) ]
      (change_product_format p sale)
    )
  )
)

(defn get_product_that_met_each_rule [offer sale]
  (vec
    (for
      [
        code (get-in offer  ["rule" "rules"])
        :let [ prods (get_products sale) ]
      ]
      (apply_rule_to_products code prods)
    )
  )
)

(defn cart [colls]
  "hace un producto cartesiano de todos contra todos"
  (if (empty? colls)
    [[]]
    (vec
      (for
        [
          x (first colls)
          more (cart (rest colls))
        ]
        (vec (cons x more))
      )
    )
  )
)

(defn vec_remove [coll value]
  "remove elem in coll"
  (let
    [
      pos (.indexOf coll value)
    ]
    (vec (concat (subvec coll 0 pos) (subvec coll (inc pos))))
  )
)

(defn filter_repeated [vecs]
  "Esta funcion no anda, pero quiero que si existe esto: vecs = [[1 2 3] [4 5 6] [1 7 8]]
  filtre el vector [1 2 3] o [1 7 8] ya que el uno esta en ambos y solo puede cada numero
  puede aparecer una vez. Esta funcion deberia hacer eso. Cada numero seria un producto"
  (for
    [ v1 vecs ]
    (for
      [
        v2 vecs
        :let
        [
          is_repeated (not (= nil (some (set v2) v1)))
          is_equal (= v1 v2)
        ]
      ]
    )
  )
)

(def filter_tuples [tuples]
  (let
    [
      not_equals (vec (distinct tuples))
      not_repeated (filter_repeated not_equals)
    ]
    not_repeated
  )
)

(defn find_products_tuples [offer sale]
  (let
    [
      prods (get_product_that_met_each_rule offer sale)
      combination (cart prods)
    ]
    (filter_tuples combination)
  )
)

(defmethod get_products_that_met_the_rules true [offer sale]
  (let
    [
      rules_size (count (get-in offer ["rule" "rules"]))
      prods_size (count (get-in offer ["products"]))
    ]
    (if (< prods_size rules_size)
      []
      (find_products_tuples offer sale)
    )
  )
)
(defmethod get_products_that_met_the_rules false [offer sale]
  (vec
    (for
      [
        p (sale "products")
        :let
        [
          prod (change_product_format p sale)
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
