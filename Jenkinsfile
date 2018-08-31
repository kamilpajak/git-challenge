#!/usr/bin/env groovy

pipeline {
 agent any
 tools {
  maven 'Maven 3.5.4'
 }
 stages {
  stage('Initialization') {
   steps {
    sh 'ls -lA'
   }
  }

  stage('Testing') {
   steps {
    sh 'mvn clean install'
   }
   post {
    success {
     junit 'target/surefire-reports/**/*.xml'
    }
   }
  }
 }
}
