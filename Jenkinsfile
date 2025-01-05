pipeline {
    agent any
    environment {
       // DOCKER_REGISTRY = 'your-docker-registry'
        DOCKER_IMAGE = 'animal-crud'
        DOCKER_TAG = 'latest'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    sh './gradlew clean build'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh './gradlew test'
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker-compose build'
                }
            }
        }
        stage('Docker Compose Up') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }
//         stage('Push to Registry') {
//             steps {
//                 script {
//                     sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG}"
//                     sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG}"
//                 }
//             }
//         }
    }
}
