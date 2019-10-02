(ns nedap.utils.spec.predicates.test-runner
  (:require
   [cljs.nodejs :as nodejs]
   [nedap.utils.test.api :refer-macros [run-tests]]
   [unit.nedap.utils.spec.predicates]
   [unit.nedap.utils.spec.predicates.present-named]
   [unit.nedap.utils.spec.predicates.present-string]
   [unit.nedap.utils.spec.predicates.ranges]))

(nodejs/enable-util-print!)

(defn -main []
  (run-tests
   'unit.nedap.utils.spec.predicates
   'unit.nedap.utils.spec.predicates.present-named
   'unit.nedap.utils.spec.predicates.present-string
   'unit.nedap.utils.spec.predicates.ranges))

(set! *main-cli-fn* -main)
