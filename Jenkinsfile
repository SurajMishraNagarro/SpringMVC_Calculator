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
                    // Reference the Artifactory instance you configured in Jenkins.
                    def server = Artifactory.server 'artifactoryCloud'
                    
                    // Create a build info object to capture build metadata.
                    def buildInfo = Artifactory.newBuildInfo()
                    
                    // Set up Maven build integration with Artifactory.
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.tool = 'MAVEN_HOME'
                    
                    // Configure the deployer with the appropriate repositories.
                    // 'releaseRepo' and 'snapshotRepo' should match your Artifactory configuration.
                    rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'snapshots'
                    
                    // Run the Maven build with the deploy goal.
                    // This uses the pom.xml's distributionManagement settings.
                    rtMaven.run pom: 'pom.xml', goals: 'clean deploy', buildInfo: buildInfo
                    
                    // Publish the build info to Artifactory for tracking.
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
