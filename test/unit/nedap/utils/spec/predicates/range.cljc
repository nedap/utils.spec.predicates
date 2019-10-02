(ns unit.nedap.utils.spec.predicates.range
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates.range :as sut]))

(deftest long
  #?(:clj
     (are [v
           f ef
           g eg
           h eh] (do (is (= ef (f v)))
                     (is (= eg (g v)))
                     (is (= eh (h v))))
        (Integer. 1)                      sut/long? true  sut/pos-long? true  sut/neg-long? false
        (Long. 1)                         sut/long? true  sut/pos-long? true  sut/neg-long? false
        (clojure.lang.BigInt/fromLong 1)  sut/long? true  sut/pos-long? true  sut/neg-long? false
        (BigInteger/valueOf 1)            sut/long? true  sut/pos-long? true  sut/neg-long? false
        (Short. "1")                      sut/long? true  sut/pos-long? true  sut/neg-long? false
        (Long. 1)                         sut/long? true  sut/pos-long? true  sut/neg-long? false
        (Double. 1.1)                     sut/long? true  sut/pos-long? true  sut/neg-long? false
        (Float. 1.1)                      sut/long? true  sut/pos-long? true  sut/neg-long? false
        (Long. -1)                        sut/long? true  sut/pos-long? false sut/neg-long? true
        (Double. -1.1)                    sut/long? true  sut/pos-long? false sut/neg-long? true
        (Float. -1.1)                     sut/long? true  sut/pos-long? false sut/neg-long? true
        Long/MIN_VALUE                    sut/long? true  sut/pos-long? false sut/neg-long? true
        Long/MAX_VALUE                    sut/long? true  sut/pos-long? true  sut/neg-long? false
        (inc (bigint Long/MAX_VALUE))     sut/long? false sut/pos-long? false sut/neg-long? false
        (dec (bigint Long/MIN_VALUE))     sut/long? false sut/pos-long? false sut/neg-long? false)))
