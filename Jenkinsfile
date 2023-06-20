pipeline {
    environment {
        registry = "eienx/notification-service"
        registryCredentials = 'dockerhub_id'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Cloning Git') {
            steps {
                git(
                    url: 'https://github.com/JoanneChangInnova/notification-service.git',
                    credentialsId: 'github_id',
                    branch: 'master'
                )
            }
        }
        stage('Building image') {
            steps {
                script {
                    dockerImage = docker.build(registry + ":$BUILD_NUMBER")
                }
            }
        }
        stage('Deploy image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Cleaning up') {
            steps {
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
    }
}
