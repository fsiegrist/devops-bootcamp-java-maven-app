#!/usr/bin/env groovy

@Library('jenkins-shared-library')_

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
                    buildAndPublishImage 'fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-1.0.2'
                }
            }
        }
    }   
}