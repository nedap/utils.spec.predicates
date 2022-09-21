(ns nedap.utils.spec.predicates
  (:require
   [clojure.spec.alpha :as spec]
   [clojure.spec.gen.alpha :as gen]
   [clojure.string :as string]
   [nedap.speced.def :as speced]
   [nedap.utils.spec.predicates.impl :as impl :refer [when-spec-coerce-available?]])
  #?(:cljs (:require-macros [nedap.utils.spec.predicates.impl :refer [when-spec-coerce-available?]]))
  #?(:clj (:import (java.time LocalDate LocalDateTime Instant ZonedDateTime OffsetDateTime Duration OffsetTime LocalTime ZoneId))))

(speced/defn ^boolean? neg-integer?
  "Is `x` negative (as per `clojure.core/neg?`) and integer (as per `clojure.core/integer?`)?

  This function is recommended over `clojure.core/neg?` and `clojure.pos/neg-int?` for maximum abstraction over specific types."
  [x]
  (and (integer? x)
       (neg? x)))

(speced/defn ^boolean? nat-integer?
  "Is `x` non-negative (as per `clojure.core/nat-int?`) and integer (as per `clojure.core/integer?`)?

  This function is recommended over `clojure.core/nat-int?`r maximum abstraction over specific types."
  [x]
  (and (integer? x)
       (not (neg? x))))

(speced/defn ^boolean? pos-integer?
  "Is `x` positive (as per `clojure.core/pos?`) and integer (as per `clojure.core/integer?`)?

  This function is recommended over `clojure.core/pos?` and `clojure.pos/pos-int?` for maximum abstraction over specific types."
  [x]
  (and (integer? x)
       (pos? x)))

(speced/defn ^boolean? named?
  "Is `x` something that `clojure.core/name` can handle?"
  [x]
  (or (string? x)
      (symbol? x)
      (keyword? x)))

(speced/defn ^boolean? present-string?
  "Is `x` a string, and not a `clojure.string/blank?` one?"
  [x]
  (and (string? x)
       (not (string/blank? x))))

(speced/defn ^boolean? present-named?
  "Is `x` a `#'named?` with a `#'present-string?` as its name"
  [x]
  (and (named? x)
       (present-string? (name x))))

#?(:clj
   (do
     (defn duration? [v]
       (instance? Duration v))

     (defn instant? [v]
       (instance? Instant v))

     (defn local-date? [v]
       (instance? LocalDate v))

     (defn local-date-time? [v]
       (instance? LocalDateTime v))

     (defn local-time? [v]
       (instance? LocalTime v))

     (defn offset-date-time? [v]
       (instance? OffsetDateTime v))

     (defn offset-time? [v]
       (instance? OffsetTime v))

     (defn zoned-date-time? [v]
       (instance? ZonedDateTime v))

     (defn zone-id? [v]
       (instance? ZoneId v))))

(def neg-integer-coercer (impl/coercer neg-integer?))

(def nat-integer-coercer (impl/coercer nat-integer?))

(def pos-integer-coercer (impl/coercer pos-integer?))

(when-spec-coerce-available?
 (defmethod spec-coerce.core/sym->coercer `neg-integer? [_] neg-integer-coercer)

 (defmethod spec-coerce.core/sym->coercer `nat-integer? [_] nat-integer-coercer)

 (defmethod spec-coerce.core/sym->coercer `pos-integer? [_] pos-integer-coercer))

(spec/def ::neg-integer
  (-> neg-integer?
      (spec/with-gen #(gen/large-integer* {:max 0}))))

(spec/def ::nat-integer
  (-> nat-integer?
      (spec/with-gen #(gen/large-integer* {:min 0}))))

(spec/def ::pos-integer
  (-> pos-integer?
      (spec/with-gen #(gen/large-integer* {:min 1}))))

(spec/def ::named
  (-> named?
      (spec/with-gen #(gen/one-of [(gen/string)
                                   (gen/symbol)
                                   (gen/keyword)]))))

(spec/def ::present-string
  (-> present-named?
      (spec/with-gen #(gen/such-that present-string? (gen/string)))))

(spec/def ::present-named
  (-> present-named?
      (spec/with-gen #(gen/such-that (comp present-string? name)
                                     (spec/gen ::named)))))

#?(:clj
   (do
     (spec/def ::duration
       (-> duration?
           (spec/with-gen (fn [] (gen/fmap #(Duration/ofSeconds %)
                                           (gen/large-integer))))))

     (spec/def ::instant
       (-> instant?
           (spec/with-gen (fn [] (gen/fmap #(Instant/ofEpochMilli %)
                                           (gen/large-integer))))))

     (spec/def ::zone-id
       (-> zone-id?
           (spec/with-gen (fn [] (gen/fmap #(ZoneId/of %)
                                           (gen/elements (ZoneId/getAvailableZoneIds)))))))

     (spec/def ::local-date
       (-> local-date?
           (spec/with-gen #(gen/fmap (fn [[millis zone]] (-> (Instant/ofEpochMilli millis)
                                                             (.atZone zone)
                                                             (.toLocalDate)))
                                     (gen/tuple (gen/large-integer) (spec/gen ::zone-id))))))

     (spec/def ::local-time
       (-> local-time?
           (spec/with-gen #(gen/fmap (fn [[millis zone]] (-> (Instant/ofEpochMilli millis)
                                                             (.atZone zone)
                                                             (.toLocalTime)))
                                     (gen/tuple (gen/large-integer) (spec/gen ::zone-id))))))

     (spec/def ::local-date-time
       (-> local-date-time?
           (spec/with-gen #(gen/fmap (fn [[millis zone]] (-> (Instant/ofEpochMilli millis)
                                                             (.atZone zone)
                                                             (.toLocalDateTime)))
                                     (gen/tuple (gen/large-integer) (spec/gen ::zone-id))))))

     (spec/def ::offset-date-time
       (-> offset-date-time?
           (spec/with-gen #(gen/fmap (fn [[millis zone]] (-> (Instant/ofEpochMilli millis)
                                                             (.atZone zone)
                                                             (.toOffsetDateTime)))
                                     (gen/tuple (gen/large-integer) (spec/gen ::zone-id))))))

     (spec/def ::offset-time
       (-> offset-time?
           (spec/with-gen #(gen/fmap (fn [[millis zone]] (-> (Instant/ofEpochMilli millis)
                                                             (.atZone zone)
                                                             (.toOffsetDateTime)
                                                             (.toOffsetTime)))
                                     (gen/tuple (gen/large-integer) (spec/gen ::zone-id))))))

     (spec/def ::zoned-date-time
       (-> zoned-date-time?
           (spec/with-gen #(gen/fmap (fn [[millis zone]] (-> (Instant/ofEpochMilli millis)
                                                             (.atZone zone)))
                                     (gen/tuple (gen/large-integer) (spec/gen ::zone-id))))))))
