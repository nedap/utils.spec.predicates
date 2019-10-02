(ns unit.nedap.utils.spec.predicates.range
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates.range :as sut]))

(deftest long?
  #?(:clj
     (are [x expected] (= expected
                          (sut/long? x))
       (Integer. 1)                      true
       (Long. 1)                         true
       (clojure.lang.BigInt/fromLong 1)  true
       (BigInteger/valueOf 1)            true
       (Short. "1")                      true
       (Long. 1)                         true
       (Double. 1.1)                     true
       (Float. 1.1)                      true
       (Long. -1)                        true
       (Double. -1.1)                    true
       (Float. -1.1)                     true
       Long/MIN_VALUE                    true
       Long/MAX_VALUE                    true
       (inc (bigint Long/MAX_VALUE))     false
       (dec (bigint Long/MIN_VALUE))     false)))

(deftest pos-long?
  #?(:clj
     (are [x expected] (= expected
                          (sut/pos-long? x))
       (Integer. 1)                      true
       (Long. 1)                         true
       (clojure.lang.BigInt/fromLong 1)  true
       (BigInteger/valueOf 1)            true
       (Short. "1")                      true
       (Long. 1)                         true
       (Double. 1.1)                     true
       (Float. 1.1)                      true
       (Long. -1)                        false
       (Double. -1.1)                    false
       (Float. -1.1)                     false
       Long/MAX_VALUE                    true
       Long/MIN_VALUE                    false
       (inc (bigint Long/MAX_VALUE))     false
       (dec (bigint Long/MIN_VALUE))     false)))

(deftest neg-long?
  #?(:clj
     (are [x expected] (= expected
                          (sut/neg-long? x))
       (Integer. 1)                      false
       (Long. 1)                         false
       (clojure.lang.BigInt/fromLong 1)  false
       (BigInteger/valueOf 1)            false
       (Short. "1")                      false
       (Long. 1)                         false
       (Double. 1.1)                     false
       (Float. 1.1)                      false
       (Long. -1)                        true
       (Double. -1.1)                    true
       (Float. -1.1)                     true
       Long/MAX_VALUE                    false
       Long/MIN_VALUE                    true
       (inc (bigint Long/MAX_VALUE))     false
       (dec (bigint Long/MIN_VALUE))     false)))
