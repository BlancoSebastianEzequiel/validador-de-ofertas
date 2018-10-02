(ns exceptions
  (require [convertions :refer :all]
           [operators :refer :all]
  )
)


(def invalid_fields ["INVALID_METHOD" "NO_ONE"])

(defmulti check_field
  (fn [field msg] (.contains invalid_fields field))
)
(defmethod check_field true [field msg] (throw (Exception. msg)))
(defmethod check_field false [field msg] field)
;;------------------------------------------------------------------------------
(defmulti check_unknown_id
  (fn [id]
    (empty? (json_to_map id))
  )
)
(defmethod check_unknown_id false [id] id)
(defmethod check_unknown_id true [id] (throw (Exception. "empty id")))
;;------------------------------------------------------------------------------
(defn check_cycle [rule rules_vector]
  (for
    [
      r rules_vector
      :when (= (rule "code") (r "rules"))
    ]
    r
  )
)

(defn is_rule_a_cycle [rule rules_vector]
  (if (not (contains? rule "value"))
    (not (empty? (check_cycle rule rules_vector)))
    false
  )
)

(defn are_cycle_rules [rules_vector]
  (vec
    (for
      [r rules_vector]
      (is_rule_a_cycle r rules_vector)
    )
  )
)

(defmulti check_cycle_id
  (fn [rules]
    (apply_op "OR" (are_cycle_rules rules) nil)
  )
)
(defmethod check_cycle_id false [rules] rules)
(defmethod check_cycle_id true [rules] (throw (Exception. "cycle id")))
;;------------------------------------------------------------------------------
(defmulti check_duplicate_codes
  (fn [ids code]
    (let
      [
        grouped (group-by (fn [x] (x code)) ids)
        mapped (map (fn [[k v]] { code (str k) "size" (count v) }) grouped)
        duplicates (filter (fn [x] (> (x "size") 1)) mapped)
      ]
      (not (empty? duplicates))
    )
  )
)
(defmethod check_duplicate_codes true [ids code]
  (throw (Exception. "you have duplicated codes"))
)
(defmethod check_duplicate_codes false [ids code] ids)
