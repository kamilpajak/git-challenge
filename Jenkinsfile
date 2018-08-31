pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        sh 'sh "\'${mvnHome}/bin/mvn\' clean test"'
      }
    }
  }
  environment {
    mvnHome = 'tool \'M3\''
  }
}