(ns unit.nedap.utils.spec.predicates.present-string
  (:require
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.predicates :as sut]
   [spec-coerce.core :as spec-coerce]))

(deftest present-string?
  (are [input expected] (= expected
                           (sut/present-string? input))
    nil         false
    42          false
    []          false
    {}          false
    [1]         false
    {1 1}       false
    ["present"] false
    ""          false
    "     "     false
    "nil"       true
    "hi"        true))
