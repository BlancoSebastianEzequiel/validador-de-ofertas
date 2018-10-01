(ns translations)

(defmulti translate (fn [month] month))

(defmethod translate "JANUARY" [month] "ENERO")
(defmethod translate "ENERO" [month] "ENERO")

(defmethod translate "FEBRUARY" [month] "FEBRERO")
(defmethod translate "FEBRERO" [month] "FEBRERO")

(defmethod translate "MARCH" [month] "MARZO")
(defmethod translate "MARZO" [month] "MARZO")

(defmethod translate "APRIL" [month] "ABRIL")
(defmethod translate "ABRIL" [month] "ABRIL")

(defmethod translate "MAY" [month] "MAYO")
(defmethod translate "MAYO" [month] "MAYO")

(defmethod translate "JUNE" [month] "JUNIO")
(defmethod translate "JUNIO" [month] "JUNIO")

(defmethod translate "JULY" [month] "JULIO")
(defmethod translate "JULIO" [month] "JULIO")

(defmethod translate "AUGUST" [month] "AGOSTO")
(defmethod translate "AGOSTO" [month] "AGOSTO")

(defmethod translate "SEPTEMBER" [month] "SEPTIEMBRE")
(defmethod translate "SEPTIEMBRE" [month] "SEPTIEMBRE")

(defmethod translate "OCTOBER" [month] "OCTUBRE")
(defmethod translate "OCTUBRE" [month] "OCTUBRE")

(defmethod translate "NOVEMBER" [month] "NOVIEMBRE")
(defmethod translate "NOVIEMBRE" [month] "NOVIEMBRE")

(defmethod translate "DECEMBER" [month] "DICIEMBRE")
(defmethod translate "DICIEMBRE" [month] "DICIEMBRE")
