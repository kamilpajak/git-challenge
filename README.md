## Git Challenge: My reference user acceptance testing framework

This project is designed to serve as my *reference user acceptance testing framework* I hope you'll find it useful!

## Prerequisites

Before running tests you need to create `application.properties` file and set login and password that will be used as GitHub credentials.

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

To run the tests execute following command

```
mvn clean test
```

## Q&A and issue tracking

If you have any questions, feedback, or feature requests, don't hesitate to contact me.