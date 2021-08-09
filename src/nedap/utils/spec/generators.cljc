(ns nedap.utils.spec.generators
  (:require
   #?(:clj [clojure.spec.gen.alpha :as gen] :cljs [cljs.spec.gen.alpha :as gen])
   [clojure.test.check.generators]
   [nedap.utils.spec.predicates :refer [named? nat-integer? neg-integer? pos-integer? present-named? present-string?]]))

(defonce ^:private gen-builtins @@#'gen/gen-builtins)

(def builtins
  (delay
    (let [gen-named (gen/one-of
                     [(gen/string)
                      (gen/symbol)
                      (gen/keyword)])]
      (merge gen-builtins
             {neg-integer?    (gen/such-that neg? (gen/large-integer {:max 0}))
              nat-integer?    (gen/such-that (complement neg?) (gen/large-integer {:min 0}))
              pos-integer?    (gen/such-that pos? (gen/large-integer {:min 1}))
              present-named?  (gen/such-that (comp present-string? name) gen-named)
              named?          gen-named
              present-string? (gen/such-that present-string? (gen/string))}))))

#?(:clj (alter-var-root #'gen/gen-builtins (constantly builtins))
   :cljs (set! gen/gen-builtins builtins))
