(ns rule_applier
  (require [clojure.string :only (split)]
           ;;[operators :refer :all]
           ;;[fields :refer :all]
           ;;[exceptions :refer :all]
           ;;[convertions :refer :all]
           ;;[insertions :refer :all] this is not implemented yet
  )
)
;; declaro este vector para poder testear esta unidad,
;; pero esto va a estar implementado en insertions
(def rules_vector [])
(defn add_rules [r] (def rules_vector r))

(defn get_rule [rule_code]
  """Como retorna un clojure.lang.LazySeq que es de esta forma ({rule})
  me quedo con el mapa que retorna que sabemos que es unico"""
  (first (filter (fn [x] (= (x "code") rule_code)) rules_vector))
)

(defn apply_rules [rules_codes prod]
  "funcion interfaz entre offer_applier.clj y rule_applier.clj"
  nil
)
