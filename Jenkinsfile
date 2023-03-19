#!/usr/bin/env groovy

def gs

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gs = load "script.groovy"
                }
            }
        }
        stage("Build Application JAR") {
            steps {
                script {
                    gs.buildJar()
                }
            }
        }
        stage("Build and Publish Docker Image") {
            steps {
                script {
                    gs.buildAndPublishImage()
                }
            }
        }
    }   
}