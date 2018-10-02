(ns rule_applier
  (require [clojure.string :only (split)]
           [operators :refer :all]
           [fields :refer :all]
           [exceptions :refer :all]
           ;;[convertions :refer :all]
           ;;[insertions :refer :all] this is not implemented yet
  )
)
;; declaro este vector para poder testear esta unidad,
;; pero esto va a estar implementado en insertions
(def rules_vector [])
(defn add_rules [r] (def rules_vector r))

(defn get_rule [rule_code]
  """
  Como retorna un clojure.lang.LazySeq que es de esta forma
  ({rule}) me quedo con el mapa que retorna que sabemos que
  es unico
  """
  (let
    [
      rule (first (filter (fn [x] (= (x "code") rule_code)) rules_vector))
    ]
    (check_unknown_id rule)
  )
)

(defn apply_atomic_rule [rule prod]
  (let
    [
      field (get_field prod rule)
      op (rule "type") ;;rule["type"]
      values (rule "value")  ;;rule["value"]
    ]
    (apply_op op values field)
  )
)


(defn apply_multiple_rules [rules_codes prod]
  (for
    [
      code rules_codes
      :let [ rule (get_rule code) ]
    ]
    (apply_atomic_rule rule prod)
  )
)

(defmulti multiple_rules (fn [rules_codes prod] (type rules_codes)))
(defmethod multiple_rules clojure.lang.PersistentVector [rules_codes prod]
  (apply_multiple_rules rules_codes prod)
)
(defmethod multiple_rules java.lang.String [rules_codes prod]
  (let [rule (get_rule rules_codes)] (apply_atomic_rule rule prod))
)

(defmulti atomic_rule (fn [rule prod] (contains? rule "value")))
(defmethod atomic_rule true [rule prod] (apply_atomic_rule rule prod))
(defmethod atomic_rule false [rule prod]
  (let
    [
      op (rule "type")
      values (multiple_rules (rule "rules") prod)
    ]
    (apply_op op values nil)
  )
)


(defn apply_rules [rules_codes prod]
  "
  un producto en esta funcion esta representado como el siguiente ejemplo:
  {
    'products' {
      'name' 'Leche Descremada 1L, la Calmisima'
      'brand' { 'code' 'Z001ABC' 'name' 'La Calmisima' }
      'category' { 'code' 'X033AXX' 'name' 'Lacteo' }
      'price'  25.40
      'iva_porcentage' 10.5
      'code' 'X033XXX'
    }
    'payment' { 'method' 'CASH' 'bank' 'CAPRO' }
    'purchase_date' {
        'year' '2018'
        'month' 'SEPTEMBER'
        'day_number' 20
        'week_day' 'Thursday'
        'week_number' 4
    }
  }
  Esta funcion retorna un vector de boleanos donde cada uno representa si
  la regla cumple o no con el producto
  "
  (vec
    (for
      [
        code rules_codes
        :let [ rule (get_rule code) ]
      ]
      (atomic_rule rule prod)
    )
  )
)
