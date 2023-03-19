#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
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
                    buildAndPublishImage 'fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-1.0.3'
                }
            }
        }
    }   
}