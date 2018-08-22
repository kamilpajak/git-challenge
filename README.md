# git-challenge

Solution of `git-challenge`

### Prerequisites

Before running tests you need to copy `application.properties.example` to `application.properties` and set login and password that will be used as GitHub credentials.

```
# ===================================================================
# GITHUB CREDENTIALS
# ===================================================================
github.login=login
github.password=password
# ===================================================================
mode.headless=false
```

## Running the tests

To run the tests execute following command

```
mvn clean test
```
