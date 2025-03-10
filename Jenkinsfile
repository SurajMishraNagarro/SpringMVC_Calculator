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
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
        
        stage('Deploy to Artifactory') {
            steps {
                script {
                    def server = Artifactory.server 'myJFrogInstance'
                    
                    def buildInfo = Artifactory.newBuildInfo()
                    
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.tool = 'MAVEN_HOME'
                    
                    rtMaven.deployer server: server, releaseRepo: 'clacmvcapp-libs-release', snapshotRepo: 'clacmvcapp-libs-snapshot'
                    
                    rtMaven.run pom: 'pom.xml', goals: 'clean deploy', buildInfo: buildInfo

                    server.publishBuildInfo buildInfo
                }
            }
        }
    }
    
    post {
        success {
            junit 'target/surefire-reports/*.xml'
            
            // Archive the coverage reports.
            archiveArtifacts artifacts: 'target/site/jacoco/**/*.*', allowEmptyArchive: true
            
            // Publish the HTML coverage report to Jenkins.
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
