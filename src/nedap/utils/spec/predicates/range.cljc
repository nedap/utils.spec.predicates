(ns nedap.utils.spec.predicates.range
  (:require
   [nedap.speced.def :as speced]))

(speced/defn ^boolean? long?
  "Is `x` a number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= -9223372036854775808N x 9223372036854775807N)) ;; Long/MIN_VALUE and Long/MAX_VALUE are unavailable in cljs
