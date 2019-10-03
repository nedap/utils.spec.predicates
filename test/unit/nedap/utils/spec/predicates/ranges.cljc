(ns unit.nedap.utils.spec.predicates.ranges
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates.ranges :as sut]))

(deftest long-predicates
  (are [v
        f ef
        g eg
        h eh
        i ei] (do
                (is (= ef (f v)))
                (is (= eg (g v)))
                (is (= eh (h v)))
                (is (= ei (i v))))

    (double 0.0)  sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (float 0.0)   sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (long 0)      sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (int 0)       sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    0             sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false

    (double 1.1)  sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (float 1.1)   sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (long 1N)     sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (int 1)       sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    94e18         sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false

    (double -1.1) sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (float -1.1)  sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (long -1N)    sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (int -1)      sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    -94e18        sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false)

  #?(:clj
     (are [v
           f ef
           g eg
           h eh
           i ei] (do
                   (is (= ef (f v)))
                   (is (= eg (g v)))
                   (is (= eh (h v)))
                   (is (= ei (i v))))

       (bigint 0)                    sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
       Long/MAX_VALUE                sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
       (bigint 1)                    sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
       (inc (bigint Long/MAX_VALUE)) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false
       Long/MIN_VALUE                sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
       (bigint -1)                   sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
       (dec (bigint Long/MIN_VALUE)) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false))

  #?(:cljs
     (are [v
           f ef
           g eg
           h eh
           i ei] (do
                   (is (= ef (f v)))
                   (is (= eg (g v)))
                   (is (= eh (h v)))
                   (is (= ei (i v))))

       Number.MIN_SAFE_INTEGER sut/long? true sut/nat-long? false sut/pos-long? false sut/neg-long? true
       Number.MAX_SAFE_INTEGER sut/long? true sut/nat-long? true  sut/pos-long? true  sut/neg-long? false)))
