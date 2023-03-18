//def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
//        stage("init") {
//            steps {
//                script {
//                    gv = load "script.groovy"
//                }
//            }
//        }
        stage("Build Application JAR") {
            steps {
                script {
                    echo "building the application..."
                    sh 'mvn package'
                    //gv.buildJar()
                }
            }
        }
        stage("Build and Publish Docker Image") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        sh 'docker build -t fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-1.0.1 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push fsiegrist/fesi-repo:devops-bootcamp-java-maven-app-1.0.1'
                    }
                  //gv.buildImage()
                }
            }
        }
//        stage("deploy") {
//            steps {
//                script {
//                    echo "deploying"
//                    //gv.deployApp()
//                }
//            }
//        }
    }   
}