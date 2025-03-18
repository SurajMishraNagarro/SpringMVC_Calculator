//  CI Pipeline
pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    environment {
         ARTIFACT_VERSION = "1.0.${BUILD_NUMBER}-SNAPSHOT"  // Setting a dynamic version
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
        
      stage('Deploy to Artifactory') {
    steps {
        script {
            def server = Artifactory.server 'myJFrogInstance'
            def buildInfo = Artifactory.newBuildInfo()
            def rtMaven = Artifactory.newMavenBuild()
            rtMaven.tool = 'MAVEN_HOME'
            
            rtMaven.deployer server: server, 
                             releaseRepo: 'clacmvcapp-libs-release', 
                             snapshotRepo: 'clacmvcapp-libs-snapshot'
            
            rtMaven.deployer.artifactDeploymentPatterns.addExclude("*-sources.jar")
            rtMaven.deployer.artifactDeploymentPatterns.addExclude("*.pom")
            
           rtMaven.run pom: 'pom.xml', goals: ("deploy -Drevision=" + ARTIFACT_VERSION.toString()), buildInfo: buildInfo
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
            echo "CI Pipeline finished successfully. Artifact version: ${ARTIFACT_VERSION}"

            build job: 'com.nagarro.MvcCalculator.pipeline.CD.dev', parameters: [
                string(name: 'ARTIFACT_VERSION', value: ARTIFACT_VERSION)
            ]
        }
        failure {
            echo "Pipeline failed. Please check the Jenkins build logs for details."
        }
    }
}
