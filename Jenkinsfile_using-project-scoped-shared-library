#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@1.0', retriever: modernSCM(
  [
    $class: 'GitSCMSource',
    remote: 'https://github.com/fsiegrist/devops-bootcamp-jenkins-shared-library.git',
    credentialsId: 'GitHub'
  ]
)

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("Build Application JAR") {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("Build and Publish Docker Image") {
            steps {
                script {
                    buildAndPublishImage()
                }
            }
        }
    }   
}