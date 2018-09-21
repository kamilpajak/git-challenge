# git-challenge

Solution of `git-challenge`

### Prerequisites

Before running tests you need to copy `application.properties.example` to `application.properties` and set login and password that will be used as GitHub credentials.

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
