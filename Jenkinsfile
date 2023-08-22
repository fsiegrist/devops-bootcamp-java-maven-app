#!/usr/bin/env groovy

pipeline {
    agent any
    environment {
        ANSIBLE_SERVER = "104.248.19.107"
    }
    stages {
        stage("copy files to ansible server") {
            steps {
                script {
                    echo "copying all neccessary files to ansible control node"
                    sshagent(['ansible-server-key']) {
                        sh "scp -o StrictHostKeyChecking=no ansible/* root@${ANSIBLE_SERVER}:/root"
                    }
                }
            }
        }
    }   
}