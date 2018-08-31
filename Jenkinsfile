#!/usr/bin/env groovy
pipeline {
    agent any
    tools { 
        maven 'Maven 3.5.4' 
    }
    stages {
        stage ('Initialize') {
            steps {
                
            }
        }

        stage ('Build') {
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
