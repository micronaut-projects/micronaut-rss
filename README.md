# Micronaut RSS

[![Maven Central](https://img.shields.io/maven-central/v/io.micronaut.rss/micronaut-rss.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.micronaut.rss%22%20AND%20a:%22micronaut-rss%22)
[![Build Status](https://github.com/micronaut-projects/micronaut-rss/workflows/Java%20CI/badge.svg)](https://github.com/micronaut-projects/micronaut-rss/actions)
[![Revved up by Gradle Enterprise](https://img.shields.io/badge/Revved%20up%20by-Gradle%20Enterprise-06A0CE?logo=Gradle&labelColor=02303A)](https://ge.micronaut.io/scans)

Micronaut RSS is...

## Documentation

See the [Documentation](https://micronaut-projects.github.io/micronaut-rss/latest/guide/) for more information. 

See the [Snapshot Documentation](https://micronaut-projects.github.io/micronaut-rss/snapshot/guide/) for the current development docs.

## Examples

Examples can be found in the [examples](https://github.com/micronaut-projects/micronaut-rss/tree/master/examples) directory.

## Snapshots and Releases

Snaphots are automatically published to [JFrog OSS](https://oss.jfrog.org/artifactory/oss-snapshot-local/) using [Github Actions](https://github.com/micronaut-projects/micronaut-rss/actions).

See the documentation in the [Micronaut Docs](https://docs.micronaut.io/latest/guide/index.html#usingsnapshots) for how to configure your build to use snapshots.

Releases are published to JCenter and Maven Central via [Github Actions](https://github.com/micronaut-projects/micronaut-rss/actions).

A release is performed with the following steps:

- [Publish the draft release](https://github.com/micronaut-projects/micronaut-rss/releases). There should be already a draft release created, edit and publish it. The Git Tag should start with `v`. For example `v1.0.0`.
- [Monitor the Workflow](https://github.com/micronaut-projects/micronaut-rss/actions?query=workflow%3ARelease) to check it passed successfully.
- Celebrate!
