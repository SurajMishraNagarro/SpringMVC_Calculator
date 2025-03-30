pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    environment {
        DOCKER_IMAGE = "mvc_calc_app:latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/SurajMishraNagarro/SpringMVC_Calculator.git'
            }
        }
        
        stage('Build & Test') {
            steps {
                bat 'mvn clean package'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

         stage('Clean Docker Environment') {
            steps {
                script {
                    echo "Stopping any running container and pruning unused Docker images..."
                    bat 'wsl bash -c "docker rm -f mvc_calc_app || true"'
                    bat 'wsl bash -c "docker rmi mvc_calc_app || true"'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker image using the WAR file from target folder..."
                    bat 'wsl docker build -t %DOCKER_IMAGE% .'
                }
            }
        }
        
        stage('Deploy Container') {
            steps {
                script {
                    echo "Deploying container on WSL..."
                    bat 'wsl bash -c "docker rm -f mvc_calc_app || true"'
                    bat 'wsl docker run -d -p 8090:8080 --name mvc_calc_app %DOCKER_IMAGE%'
                }
            }
        }
        

    }
    
    post {
        success {

            echo "Pipeline finished successfully"

        }
        failure {
            echo "Pipeline failed. Please check the Jenkins build logs for details."
        }
    }
}
