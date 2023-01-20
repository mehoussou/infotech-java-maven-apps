#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library-latest@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://github.com/mehousso/jenkins-shared-library-latest',
     credentialsId: 'gitlab-credentials'
    ]
)

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }

    environment {
        IMAGE_NAME = 'mehousso/demo-app:java-maven-6.0'
    }
    stages {
        stage("build app") {
            steps {
                script {
                    echo "building application jar..."
                    buildJar()                    
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building docker image..."
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }

        stage ("Provision server to create terraform"){
            environment{
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')
            //   assign a value to the env variable  
                TF_VAR_en_prefix = 'test'
            }
            steps{
                script{
                    dir('terraform'){
                        sh "terraform init"
                        sh "terraform apply --auto-approve"
                        EC2_PUBLIC_IP = sh (
                            script: "terraform output ec2_public_ip",
                            returnStdout: true
                        ).trim()
                    }
                }
            }

        }

        stage("deploy") {
            steps{
                script {
                    echo "waiting for EC@ server to initialize"
                    sleep(time: 120, unit: "SECONDS")

                    echo "deploying docker image to EC2..."
                    echo "${EC2_PUBLIC_IP}"

                    def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
                    def ec2Instance = "ec2-user@${EC2_PUBLIC_IP}"
                    sshagent(['server-ssh-key']) {
                        sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
                        sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
                    }
                }
            }
        }
    }
}