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
        stage("Build Docker Image") {
            steps {
                script {
                    echo "building the docker image..."
                    sh 'docker build -t java-maven-app:1.0.0 .'
//                    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
//                        sh 'docker build -t nanajanashia/demo-app:jma-2.0 .'
//                        sh "echo $PASS | docker login -u $USER --password-stdin"
//                        sh 'docker push nanajanashia/demo-app:jma-2.0'
//                    }
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