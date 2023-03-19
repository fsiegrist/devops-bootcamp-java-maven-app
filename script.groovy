#!/usr/bin/env groovy

def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildAndPublishImage() {
    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
        echo "building the docker image..."
        sh 'docker build -t fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-1.0.1 .'
                        
        echo "publishing the docker image..."
        sh "echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin"
        sh 'docker push fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-1.0.1'
    }
} 

return this