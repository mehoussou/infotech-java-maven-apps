// #!/usr/bin/env groovy


// libray identifier: 'jenkins-library@share-library-jenkins', retriever: modernSCM(
//     [$class: 'GitSCMSource',
//     remote:'https://github.com/mehoussou/Jenkins-share-library',
//     credentialsId:''
//     ]
// )
// // @library ('jenkins-library')


  
// def gv
// pipeline {
//     agent any
//     tools {
//         maven 'Maven-3.6'
//     }

//     environment {
//         IMAGE_NAME = 'mehousso/demo-app:jma-v3'
//     }
//     stages {
//         stage("build app") {
//             steps {
//                 script {
//                     echo "building application jar..."
//                     buildJar()                    
//                 }
//             }
//         }
//         stage("build image") {
//             steps {
//                 script {
//                     echo "building docker image..."
//                     buildImage(env.IMAGE_NAME)
//                     dockerLogin()
//                     dockerPush(env.IMAGE_NAME)
//                 }
//             }
//         }

//         stage ("Provision server to create terraform"){
//             environment{
//                 AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
//                 AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_key_id')
//             //   assign a value to the env variable  
//                 TF_VAR_en_prefix = 'test'
//             }
//             steps{
//                 script{
//                     dir('terraform'){
//                         sh "terraform init"
//                         sh "terraform apply --auto-approve"
//                         EC2_PUBLIC_IP = sh (
//                             script: "terraform output ec2_public_ip",
//                             returnStdout: true
//                         ).trim()
//                     }
//                 }
//             }

//         }

//         stage("deploy") {
//             environment{
//                 DOCKER_CREDS = credentials ('docker-hub-creds')

//             }
//             steps{
//                 script {
//                     echo "waiting for EC2 server to initialize"
//                     sleep(time: 120, unit: "SECONDS")

//                     echo "deploying docker image to EC2..."
//                     echo "${EC2_PUBLIC_IP}"

//                     def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME} ${DOCKER_CREDS_USR} ${DOCKER_CREDS_PSW}"
//                     def ec2Instance = "ec2-user@${EC2_PUBLIC_IP}"
//                     sshagent(['server-ssh-key']) {
//                         sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
//                         sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2Instance}:/home/ec2-user"
//                         sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
//                     }
//                 }
//             }
//         }
//     }
// }


// my work below without libray

// #!/usr/bin/env groovy


// libray identifier: 'jenkins-library@share-library-jenkins', retriever: modernSCM(
//     [$class: 'GitSCMSource',
//     remote:'https://github.com/mehoussou/Jenkins-share-library',
//     credentialsId:''
//     ]
// )
// @library ('jenkins-library')


  
// def gv
pipeline {
    agent any
    tools {
        maven 'Maven-3.6'
    }

    environment {
        IMAGE_NAME = 'mehousso/demo-app:jma-v3'
    }

    stages {
        stage("build app") {
            steps {
                script {
                echo "building application jar..."
                sh 'mvn package'  
                }   
            }
        }
    }

        stage("build image"){
            steps {
                script {
                    echo "building docker image..."
                    docker build -t "$IMAGE_NAME" .
                    echo "$DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin"
                    docker push "$IMAGE_NAME"
                }
            }
        }

        stage ("Provision server to create terraform"){
            environment{
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_key_id')
            //   assign a value to the env variable  
                TF_VAR_en_prefix = 'test'
            }
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

        stage("deploy") {
            environment{
                DOCKER_CREDS = credentials ('docker-hub-creds')
            }

            steps{
                script {
                    echo "waiting for EC2 server to initialize"
                    sleep(time: 120, unit: "SECONDS")

                    echo "deploying docker image to EC2..."
                    echo "${EC2_PUBLIC_IP}"

                    def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME} ${DOCKER_CREDS_USR} ${DOCKER_CREDS_PSW}"
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
