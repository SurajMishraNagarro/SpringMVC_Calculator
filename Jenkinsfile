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
                script {
                    bat 'mvn clean test'  
                }
            }
        }
    }

    post {
    success {
        echo "... wait"
        echo "Pipeline finished successfully"
    }
}

}