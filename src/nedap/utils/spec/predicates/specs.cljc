(ns nedap.utils.spec.predicates.specs
  (:require
   [clojure.spec.alpha :as spec]
   [clojure.spec.gen.alpha :as gen]
   [clojure.test.check.generators]
   [nedap.utils.spec.predicates :as predicates]))

(spec/def ::nat-integer?
  (-> predicates/nat-integer?
      (spec/with-gen #(gen/large-integer* {:min 0}))))

(spec/def ::pos-integer?
  (-> predicates/pos-integer?
      (spec/with-gen #(gen/large-integer* {:min 1}))))

(spec/def ::neg-integer?
  (-> predicates/neg-integer?
      (spec/with-gen #(gen/large-integer* {:max 0}))))

(spec/def ::named?
  (-> predicates/named?
      (spec/with-gen #(gen/one-of [(gen/string)
                                   (gen/symbol)
                                   (gen/keyword)]))))

(spec/def ::present-named?
  (-> predicates/present-named?
      (spec/with-gen #(gen/such-that (comp predicates/present-string? name)
                                     (spec/gen ::named?)))))


(spec/def ::present-string?
  (-> predicates/present-named?
      (spec/with-gen #(gen/such-that predicates/present-string? (gen/string)))))
