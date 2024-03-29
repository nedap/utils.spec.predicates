(ns nedap.utils.spec.predicates.impl)

(defn coercer [pred]
  #?(:clj  (fn [x]
             (cond
               (pred x)          x
               (not (string? x)) x
               :else             (try
                                   (let [v (BigInteger. ^String x)]
                                     (if-not (pred v)
                                       x
                                       (if (<= Long/MIN_VALUE v Long/MAX_VALUE)
                                         (Long/parseLong x)
                                         v)))
                                   (catch #?(:clj Exception :cljs js/Error) _
                                     x))))
     :cljs (fn [x]
             (cond
               (pred x)          x
               (not (string? x)) x
               :else             (try
                                   (let [v (js/parseInt x 10)]
                                     (if (and (number? v)
                                              (not (js/isNaN v))
                                              (pred v))
                                       v
                                       x))
                                   (catch js/Error _
                                     x))))))

(defmacro when-spec-coerce-available? [& body]
  (when (try
          (requiring-resolve 'spec-coerce.core/sym->coercer)
          true
          (catch Exception _
            false))
    `(do ~@body)))
