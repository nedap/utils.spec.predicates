(ns nedap.utils.spec.predicates.ranges
  (:require
   #?(:clj [nedap.utils.spec.predicates.ranges.impl :refer :all])
   [nedap.speced.def :as speced])
  #?(:cljs (:require-macros [nedap.utils.spec.predicates.ranges.impl :refer [min-long-value max-long-value]])))

(speced/defn ^boolean? long?
  "Is `x` a number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= (min-long-value) x (max-long-value)))

(speced/defn ^boolean? pos-long?
  "Is `x` a positive number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= 1 x (max-long-value)))

(speced/defn ^boolean? neg-long?
  "Is `x` a negative number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= (min-long-value) x -1))

(speced/defn ^boolean? nat-long?
  "Is `x` a non-negative number in the range of `java.lang.Long`?

  Works for any numeric type"
  [^number? x]
  (<= 0 x (max-long-value)))