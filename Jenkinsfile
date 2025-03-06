pipeline {
    agent any
    
    tools {
        maven 'MAVEN_HOME'
    }
    
    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/SurajMishraNagarro/SpringMVC_Calculator.git'
            }
        }
        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Build and Sonar Analysis') {
            steps {
                // SonarQube environment
                withSonarQubeEnv('Sonarqube') {
                   
                    bat 'mvn clean package sonar:sonar'
                }
            }
        }
    }
    
    post {
        success {
            echo "Pipeline finished successfully."
        }
        failure {
            echo "Pipeline failed. Please check the Jenkins build logs and SonarQube analysis for details."
        }
    }
}
