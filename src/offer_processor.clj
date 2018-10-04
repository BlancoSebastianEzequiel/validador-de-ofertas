(ns offer-processor
  (require [offer_applier :refer :all]
           [convertions :refer :all]
           [insertions :refer :all]
  )
)

(defn initialize-offers [offers rules]
  (do
    (add_rule rules) (add_offer offers)
  )
  "created"
)

(defn process-sale [state sale]
  (map_to_json
    (vec
      (apply concat
        (for
          [
            o offers_vector
            :let
            [
              no_value (pass_rules rules_vector)
              result (apply_offer o (json_to_map sale))
            ]
          ]
          result
        )
      )
    )
  )
)
