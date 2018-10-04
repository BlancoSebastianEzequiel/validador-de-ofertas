(ns operators)

(defmulti apply_op (fn [op values field] op))
(defmethod apply_op "EQUALS" [op values field]
  (= values field)
)
(defmethod apply_op "AND" [op values field]
  (every? #{true} values)
)
(defmethod apply_op "OR" [op values field]
  (if (some #{true} values)
    true
    false
  )
)
(defmethod apply_op "IN" [op values field]
  (= field (some #{field} values))
)
(defmethod apply_op "LOWER" [op values field]
  (< field values)
)
(defmethod apply_op "HIGHER" [op values field]
  (> field values)
)
(defmethod apply_op "NOT" [op values field]
  (not values)
)

(defmethod apply_op "DISTINCT" [op values field]
  (not (= values field))
)
