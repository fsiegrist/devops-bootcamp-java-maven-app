#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("Increment Version") {
            steps {
                script {
                    echo 'incrementing the bugfix version of the application...'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
      
                    def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                    env.IMAGE_TAG = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("Build Application JAR") {
            steps {
                script {
                    echo "building the application..."
                    sh 'mvn clean package'
                }
            }
        }
        stage("Build and Publish Docker Image") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        echo "building the docker image..."
                        sh "docker build -t fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-${IMAGE_TAG} ."
                        
                        echo "publishing the docker image..."
                        sh "echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin"
                        sh "docker push fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-${IMAGE_TAG}"
                    }
                }
            }
        }
        stage('Deploy Application') {
            steps {
                script {
                    echo 'deploying Docker image to EC2 server...'
                    // def dockerComposeCmd = "IMAGE_TAG=${IMAGE_TAG} docker-compose -f docker-compose.yaml up -d"
                    def shellCmd = "bash ./server-cmds.sh ${IMAGE_TAG}"
                    sshagent(['ec2-server-key']) {
                        // sh 'scp docker-compose.yaml ec2-user@35.156.226.244:/home/ec2-user'
                        // sh "ssh -o StrictHostKeyChecking=no ec2-user@35.156.226.244 ${dockerComposeCmd}"
                        sh 'scp server-cmds.sh docker-compose.yaml ec2-user@35.156.226.244:/home/ec2-user'
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@35.156.226.244 ${shellCmd}"
                    }
                }
            }
        }
        stage('Commit Version Update') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'GitHub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/fsiegrist/devops-bootcamp-java-maven-app.git"
                        sh 'git add .'
                        sh 'git commit -m "jenkins: version bump"'
                        sh 'git push origin HEAD:main'
                    }
                }
            }
        }
    }   
}
