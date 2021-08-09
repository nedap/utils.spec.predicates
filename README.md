# utils.spec.predicates

Selected, generic predicates that you might find handy when specing things.

## Installation

```clojure
[com.nedap.staffing-solutions/utils.spec.predicates "1.1.0"]
```

## Documentation

Please browse the public namespaces, which are documented, speced and tested.

`nedap.utils.spec.predicates.specs` contains generator-equipped specs alternatives:
```clojure
(require '[nedap.utils.spec.predicates.specs :as specs]
         '[clojure.spec.alpha :as spec]
         '[clojure.spec.gen.alpha :as gen])
=> nil
(gen/sample (spec/gen ::specs/pos-integer?))
=> (3 1 12 3 1 6 58 4 107 2)
```

## Development

The default namespace is `dev`. Under it, `(refresh)` is available, which should give you a basic "Reloaded workflow".

> It is recommended that you use `(clojure.tools.namespace.repl/refresh :after 'formatting-stack.core/format!)`.

## License

Copyright © Nedap
This program and the accompanying materials are made available under the terms of the [Eclipse Public License 2.0](https://www.eclipse.org/legal/epl-2.0)
