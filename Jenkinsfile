pipeline {
    agent any

    environment {
        // Задайте переменные окружения для вашего проекта, если необходимо
        DOCKER_IMAGE = 'your-docker-image:latest'  // Название вашего Docker-образа
        DOCKER_REGISTRY = 'your-docker-registry'   // Адрес вашего Docker-реестра (если используете)
    }

    stages {
        stage('Checkout') {
            steps {
                // Клонируем репозиторий
                git 'https://github.com/yourusername/yourproject.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Выполняем сборку проекта с помощью Gradle
                    sh './gradlew clean build'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Строим Docker-образ
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    // Запускаем контейнер Docker (по необходимости можно настроить порты)
                    sh 'docker run -d -p 8080:8080 $DOCKER_IMAGE'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Запускаем тесты с помощью Gradle (если у вас есть тесты)
                    sh './gradlew test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Деплой вашего приложения (например, пушим образ в Docker-реестр)
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }

        stage('Clean up') {
            steps {
                script {
                    // Останавливаем и удаляем контейнеры
                    sh 'docker stop $(docker ps -q)'
                    sh 'docker rm $(docker ps -a -q)'
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
