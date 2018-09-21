# Git Challenge
*Reference user acceptance testing framework*

This project is designed to serve as my reference user acceptance testing framework. I hope you'll find it useful!

## Prerequisites

Before running tests you need to create an `application.properties` file and set your GitHub credentials:

```
# ===================================================================
# GIT CHALLENGE
# ===================================================================
github.environment=production
github.login=
github.password=
# ===================================================================
selenide.grid=
selenide.timeout=
```

## Running the tests

To run the tests just execute `mvn clean test`

## Pull requests welcome!

Spotted an error? Something doesn't make sense? Send me a [pull
request](https://github.com/kamilpajak/git-challenge/pulls). Thanks!
