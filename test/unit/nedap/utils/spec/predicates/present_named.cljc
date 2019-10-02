(ns unit.nedap.utils.spec.predicates.present-named
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates :as sut]))

(deftest present-named?
  (are [input expected] (= expected
                           (sut/present-named? input))
    nil               false
    42                false
    []                false
    {}                false
    [1]               false
    {1 1}             false
    ["present"]       false
    (keyword "ns" "") false
    (symbol "ns" " ") false
    'ns/name          true
    :wat/name         true
    "wat/patat"       true
    :wat              true))
