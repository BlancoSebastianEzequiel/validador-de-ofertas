(ns convertions
  (require [clojure.data.json :as json]))

(defn json_to_map [j] (json/read-str j))
(defn map_to_json [j] (json/write-str j))
