(ns insertions
  (require [convertions :refer :all]
           [exceptions :refer :all]
  )
)

(def offers_vector [])
(def rules_vector [])
(defn add_offer [o]
  (do
    (def offers_vector (json_to_map (check_unknown_id o)))
    (check_duplicate_codes offers_vector "code")
  )
)
(defn add_rule [r]
  (do
    (def rules_vector (json_to_map (check_unknown_id r)))
    (check_cycle_id rules_vector)
    (check_duplicate_codes rules_vector "code")
  )
)
