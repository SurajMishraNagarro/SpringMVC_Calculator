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
    }
    
    post {
        success {
            
            

            junit 'target/surefire-reports/*.xml'
            
            // Archive the  coverage reports 
            archiveArtifacts artifacts: 'target/site/jacoco/**/*.*', allowEmptyArchive: true
            
            // Publish the HTML coverage report to jenkins
            publishHTML target: [
                reportDir: 'target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'Code Coverage Report',
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true
            ]

            echo "Pipeline finished successfully."
        }
       
        failure {
            echo "Pipeline failed. Please check the Jenkins build logs for details."
        }
    }
}
