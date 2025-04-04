pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    environment {
        DOCKER_IMAGE = "mvc_calc_app:latest"
        //AWS ECR details:
        AWS_REGION = "ap-south-1"
        AWS_ACCOUNT_ID = "876724398547"
        ECR_REPO = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/surajmishra/mvc_calc_app"
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
        
        // stage('SonarQube Analysis') {
        //     steps {
        //         withSonarQubeEnv('Sonarqube') {
        //             bat 'mvn sonar:sonar'
        //         }
        //     }
        // }
        
        stage('Clean Docker Environment') {
            steps {
                script {
                    echo "Stopping any running container and removing old image..."
                    bat 'wsl bash -c "docker rmi mvc_calc_app || true"'
                    bat 'wsl bash -c "docker rmi %ECR_REPO%:latest || true"'
            
                    echo "Deleting existing image from AWS ECR..."
                    bat 'wsl bash -c "aws ecr batch-delete-image --repository-name surajmishra/mvc_calc_app --image-ids imageTag=latest || true"'
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
        
        stage('Push to AWS ECR') {
            steps {
                script {
                    
                    echo "Pushing the Docker image to ECR..."
                    bat 'wsl bash -c "aws ecr get-login-password --region %AWS_REGION% | docker login --username AWS --password-stdin %ECR_REPO%"'
                    bat 'wsl docker tag %DOCKER_IMAGE% %ECR_REPO%:latest'                   
                    bat 'wsl docker push %ECR_REPO%:latest'
                }
            }
        }

    //    stage('Deploy to EC2') {
    //         steps {
    //             sshagent(['ec2-mvc-ssh-key']) {
    //                 bat '''
    //                     ssh ubuntu@ec2-52-66-255-145.ap-south-1.compute.amazonaws.com "bash ~/deploy.sh"
    //                 '''
    //             }
    //         }
    //     }
        
    }
    
    post {
        success {
            echo "Pipeline finished successfully. Docker image pushed to AWS ECR: %ECR_REPO%:latest"
        }
        failure {
            echo "Pipeline failed. Please check the Jenkins build logs for details."
        }
    }
}
