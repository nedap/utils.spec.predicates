(ns unit.nedap.utils.spec.generators
  (:require
   #?(:clj [clojure.spec.gen.alpha :as gen] :cljs [cljs.spec.gen.alpha :as gen])
   #?(:clj [clojure.spec.alpha :as spec] :cljs [cljs.spec.alpha :as spec])
   #?(:clj [clojure.test :refer [deftest testing are is use-fixtures]] :cljs [cljs.test :refer-macros [deftest testing is are] :refer [use-fixtures]])
   [nedap.utils.spec.generators]
   [nedap.utils.spec.predicates :as sut]))

(deftest generate-all-predicates
  (are [pred] (every? pred (gen/sample (spec/gen pred)))
    sut/pos-integer?
    sut/neg-integer?
    sut/nat-integer?
    sut/named?
    sut/present-named?
    sut/present-string?))
