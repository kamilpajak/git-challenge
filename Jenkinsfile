#!/usr/bin/env groovy

pipeline {
    agent any
    triggers {
        cron('H H/3 * * *')
    }
    tools {
        maven 'Maven 3.5.4'
    }
    stages {
        stage('Initialization') {
            steps {
                withCredentials([file(credentialsId: 'git-challenge.application_properties', variable: 'SECRET')]) {
                    sh 'cp $SECRET application.properties'
                }
            }
        }
        stage('Testing') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
