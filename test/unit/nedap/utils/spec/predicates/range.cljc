(ns unit.nedap.utils.spec.predicates.range
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates.range :as sut]))

(deftest long
  #?(:clj
     (are [v expected] (= ((juxt sut/long? sut/pos-long? sut/neg-long?)
                           v)
                          expected)
        (Integer. 1)                     [true  true  false]
        (Long. 1)                        [true  true  false]
        (clojure.lang.BigInt/fromLong 1) [true  true  false]
        (BigInteger/valueOf 1)           [true  true  false]
        (Short. "1")                     [true  true  false]
        (Long. 1)                        [true  true  false]
        (Double. 1.1)                    [true  true  false]
        (Float. 1.1)                     [true  true  false]
        (Long. -1)                       [true  false true]
        (Double. -1.1)                   [true  false true]
        (Float. -1.1)                    [true  false true]
        0                                [true  false false]
        Long/MIN_VALUE                   [true  false true]
        Long/MAX_VALUE                   [true  true  false]
        (inc (bigint Long/MAX_VALUE))    [false false false]
        (dec (bigint Long/MIN_VALUE))    [false false false])))
