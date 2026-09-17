# Python Docs Disabled Test Inventory

This file tracks the Python documentation examples of Micronaut RSS under `test-suite-python/src/test/python/micronaut/rss/docs`
that are disabled, or that carry a workaround because the direct port of the Java example does not compile or does not behave
like the Java example yet (Python compiler gaps). It is the bug-fixing task list for the Python compiler
(`micronaut-inject-python` / `micronaut-context-python`); every row references a `TODO(python)` comment in the sources.

The Python examples are compiled by every build and their tests run with `./gradlew pythonCheck -Ppython-ci`
(the "Python CI" GitHub workflow).

## Reconciliation

- Last generated active `@Disabled` count: 0.
- Last generated command: `rg -n "@Disabled\(" test-suite-python/src/test/python`.
- Last full-suite command: `./gradlew :test-suite-python:test -Ppython-ci`.
- Last full-suite result: build successful, 6 tests executed (4 test classes), 0 skipped.

## Migration Rules

- The snippet classes live in `io.micronaut.rss.docs` in every language: a Python source package cannot be the imported
  Java package `micronaut.rss` itself.
- The `RssFeedProvider` / `JsonFeedProvider` implementations subclass the imported Java interface and keep the Java method
  names (`fetch`, `fetchById`, `feed`) because they implement a Java interface; the builders of `RssChannel`, `RssItem`,
  `ItunesPodcast`, `JsonFeed`... are called exactly like in Java (a multi-line builder chain is wrapped in parentheses).
- `Flux.create(...)` takes a nested `def emit(emitter)` function for the Java `Consumer<FluxSink>` lambda, and the nested
  enum constant `FluxSink.OverflowStrategy.ERROR` is reached through the imported `FluxSink` class.
- The `List<List<String>>` argument of `ItunesPodcast.Builder.category(...)` is a Python list wrapping the Java list
  returned by `ItunesPodcastCategory.getCategories()`.
- A Python test class is a `@MicronautTest` with `@Test` methods and plain `assert` statements; the feed tests call the
  `FeedController` / `JsonFeedController` through the injected `HttpClient` and assert on the rendered XML / JSON
  (`json.loads` of the response body), so an unregistered provider fails the test instead of passing vacuously.
- `HttpClient.exchange(request)` is used without a body type (there is no Python spelling of `String.class` that the client
  accepts at runtime); the body is read with `retrieve(request)`.

## Active `@Disabled` Tests

None.

## Workarounds in the Sources

None.

## `java.type` usages

None.
