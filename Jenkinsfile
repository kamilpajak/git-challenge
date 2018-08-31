pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        sh '''if (isUnix()) {
  sh "\'${mvnHome}/bin/mvn\' clean test"
} else {
  bat(/"${mvnHome}\\bin\\mvn" clean test/)
}'''
        }
      }
    }
    environment {
      mvnHome = 'tool \'M3\''
    }
  }