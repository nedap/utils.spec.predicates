(ns nedap.utils.spec.predicates.range
  (:require
   [nedap.speced.def :as speced]))

(def max-long-value ;; unavailable in cljs
  9223372036854775807N)

(def min-long-value ;; unavailable in cljs
  -9223372036854775808N)

(speced/defn ^boolean? long?
  "Is `x` a number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= min-long-value x max-long-value))

(speced/defn ^boolean? pos-long?
  "Is `x` a positive number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= 1 x max-long-value))

(speced/defn ^boolean? neg-long?
  "Is `x` a negative number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= min-long-value x 0))
