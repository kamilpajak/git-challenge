pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        sh 'sh \'mvn clean test\''
      }
    }
  }
}