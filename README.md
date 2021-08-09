# utils.spec.predicates

Selected, generic predicates that you might find handy when specing things.

## Installation

```clojure
[com.nedap.staffing-solutions/utils.spec.predicates "1.1.0"]
```

## Documentation

Please browse the public namespaces, which are documented, speced and tested.

By requiring `nedap.utils.spec.generators` generators are added to `clojure.spec.gen.alpha` to enable generating samples:
```clojure
(require '[nedap.utils.spec.generators]
         '[clojure.spec.alpha :as spec]
         '[clojure.spec.gen.alpha :as gen])
=> nil
(gen/sample (spec/gen pos-integer?))
=> (3 1 12 3 1 6 58 4 107 2)
```

## Development

The default namespace is `dev`. Under it, `(refresh)` is available, which should give you a basic "Reloaded workflow".

> It is recommended that you use `(clojure.tools.namespace.repl/refresh :after 'formatting-stack.core/format!)`.

## License

Copyright © Nedap
This program and the accompanying materials are made available under the terms of the [Eclipse Public License 2.0](https://www.eclipse.org/legal/epl-2.0)
