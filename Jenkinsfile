pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    environment {
        ARTIFACT_VERSION = "0.0.${BUILD_NUMBER}-SNAPSHOT" 
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/SurajMishraNagarro/SpringMVC_Calculator.git'
            }
        }
        
        stage('Build & Test') {
            steps {
                bat 'mvn clean package -Drevision=${ARTIFACT_VERSION}'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
        

    }
    
    post {
        success {

            echo "CI Pipeline finished successfully. Artifact version: ${ARTIFACT_VERSION}"

        }
        failure {
            echo "Pipeline failed. Please check the Jenkins build logs for details."
        }
    }
}
