(ns nedap.utils.spec.predicates.test-runner
  (:require
   [cljs.nodejs :as nodejs]
   [cljs.test :refer-macros [run-tests]]
   [unit.nedap.utils.spec.predicates]))

(nodejs/enable-util-print!)

(defn -main []
  (run-tests
   'unit.nedap.utils.spec.predicates))

(set! *main-cli-fn* -main)
