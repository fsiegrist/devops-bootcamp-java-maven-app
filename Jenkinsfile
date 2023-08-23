#!/usr/bin/env groovy

pipeline {
    agent any
    environment {
        ANSIBLE_SERVER = "206.81.26.120"
    }
    stages {
        stage("copy files to ansible server") {
            steps {
                script {
                    echo "copying all neccessary files to ansible control node"
                    sshagent(['ansible-server-key']) {
                        sh "scp -o StrictHostKeyChecking=no ansible/* root@${ANSIBLE_SERVER}:/root"

                        withCredentials([sshUserPrivateKey(credentialsId: 'ansible-ec2-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
                            sh 'scp $keyfile root@$ANSIBLE_SERVER:/root/ssh-key.pem'
                        }
                    }
                }
            }
        }
        stage("execute ansible playbook") {
            steps {
                script {
                    echo "calling ansible playbook to configure ec2 instances"
                    def remote = [:]
                    remote.name = "ansible-server"
                    remote.host = env.ANSIBLE_SERVER
                    remote.allowAnyHosts = true

                    withCredentials([sshUserPrivateKey(credentialsId: 'ansible-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]){
                        echo user
                        echo keyfile.path
                        // remote.user = user
                        // remote.identityFile = keyfile
                        // // sshScript remote: remote, script: "prepare-ansible-server.sh"
                        // sshCommand remote: remote, command: "ansible-playbook my-playbook.yaml"
                    }

                    // or using sshagent
                    // sshagent(['ansible-server-key']) {
                    //     sh 'ssh root@$ANSIBLE_SERVER "ansible-playbook my-playbook.yaml"'
                    // }
                }
            }
        }
    }   
}