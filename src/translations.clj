(ns translations)

(defmulti translate (fn [month] month))
(defmethod translate "SEPTEMBER" [month] "SEPTIEMBRE")
(defmethod translate "SEPTIEMBRE" [month] "SEPTIEMBRE")
