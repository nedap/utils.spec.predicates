(ns unit.nedap.utils.spec.predicates.ranges
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [nedap.utils.spec.predicates.ranges.impl :refer :all])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates.ranges :as sut])
  #?(:cljs (:require-macros [nedap.utils.spec.predicates.ranges.impl :refer [min-long-value max-long-value]])))

(deftest long-predicates

  (assert (not *unchecked-math*) "Sanity check")

  (are [v
        f ef
        g eg
        h eh
        i ei] (do
                (is (= ef (f v)))
                (is (= eg (g v)))
                (is (= eh (h v)))
                (is (= ei (i v))))

    (double 0.0)                      sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (float 0.0)                       sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (long 0)                          sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (short 0)                         sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    (int 0)                           sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
    0                                 sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false

    (double 1.1)                      sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (float 1.1)                       sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (long 1)                          sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (short 1)                         sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (int 1)                           sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false

    (double -1.1)                     sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (float -1.1)                      sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (long -1)                         sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (short -1)                        sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (int -1)                          sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true

    (double (min-long-value))         sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
    (- (double (min-long-value)) 0.1) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false
    (- (double (min-long-value)) 1.0) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false

    (double (max-long-value))         sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
    (+ (double (max-long-value)) 0.1) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false
    (+ (double (max-long-value)) 1.0) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false)

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

       (dec (bigint Long/MIN_VALUE))             sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false
       (bigint -1)                               sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
       (bigint 0)                                sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
       (bigint 1)                                sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
       (inc (bigint Long/MAX_VALUE))             sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false

       (dec (BigInteger/valueOf Long/MIN_VALUE)) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false
       (BigInteger/valueOf -1)                   sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true
       (BigInteger/valueOf 0)                    sut/long? true  sut/nat-long? true  sut/pos-long? false sut/neg-long? false
       (BigInteger/valueOf 1)                    sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
       (inc (BigInteger/valueOf Long/MAX_VALUE)) sut/long? false sut/nat-long? false sut/pos-long? false sut/neg-long? false

       Long/MAX_VALUE                            sut/long? true  sut/nat-long? true  sut/pos-long? true  sut/neg-long? false
       Long/MIN_VALUE                            sut/long? true  sut/nat-long? false sut/pos-long? false sut/neg-long? true))

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
