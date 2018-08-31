#!/usr/bin/env groovy

pipeline {
 agent any
 tools {
  maven 'Maven 3.5.4'
 }
 stages {
  stage('Initialization') {
   steps {
    cleanWs()
    git 'https://github.com/kamilpajak/git-challenge.git'
    withCredentials([file(credentialsId: 'git-challenge.application.properties', variable: 'SECRET')]) {
     sh 'cp $SECRET application.properties'
    }
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
