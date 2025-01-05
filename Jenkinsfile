pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'animal-crud:latest'  // Можно использовать просто имя образа без реестра
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/CryBaby734/AnimalRepository.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Используем Gradle для сборки
                    sh './gradlew clean build'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Строим Docker-образ с тегом
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    // Запускаем контейнер
                    sh 'docker run -d -p 8080:8080 $DOCKER_IMAGE'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Запуск тестов с использованием Gradle
                    sh './gradlew test'
                }
            }
        }

        // Этап "Deploy" можно опустить, если нет необходимости пушить образ в реестр
    }
}
