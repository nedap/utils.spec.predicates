(ns nedap.utils.spec.generators
  (:require
   [clojure.spec.gen.alpha :as gen]
   [nedap.utils.spec.predicates :refer :all]))

(defonce ^:private builtins @@#'gen/gen-builtins)

(alter-var-root #'gen/gen-builtins
                (constantly
                 (delay
                   (let [gen-named (gen/one-of
                                    [(gen/string)
                                     (gen/symbol)
                                     (gen/keyword)])]
                     (assoc builtins
                            neg-integer?    (gen/such-that neg? (gen/large-integer {:max 0}))
                            nat-integer?    (gen/such-that (complement neg?) (gen/large-integer {:min 0}))
                            pos-integer?    (gen/such-that pos? (gen/large-integer {:min 1}))
                            present-named?  (gen/such-that (comp present-string? name) gen-named)
                            named?          gen-named
                            present-string? (gen/such-that present-string? (gen/string)))))))
