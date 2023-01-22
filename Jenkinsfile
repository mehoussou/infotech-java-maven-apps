// pipeline{
  
//   agent any
//   tools{
//     maven 'Maven-3.6'
//   }

//   stages {
//     stage ("build jar") {
//       steps {
//         script {
//           echo "building the application..."
//           sh 'mvn package'
//         }
//       }
//     }

//     stage ("build image"){
//       steps {
//         script {
//           echo "building the docker image..."
//           withCredentials([usernamePassword(credentialsID:'docker-hub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]){
//             sh 'docker build -t mehousso/demo-app:v2 .'
//             sh "echo $PASS | docker login -u $USER --password-stdin"
//             sh 'docker push mehousso/demo-app:v2'
//           }
//         }
//       }
//     }

//     stage ("deploy"){
//       steps{
//         script{
//           echo "deploying the application..."
//         }
//       }
//     }
//   }
// }


// ##########pipeline with groovy script###########

// def gv
// pipeline{
  
//   agent any
//   tools{
//     maven 'Maven-3.6'
//   }

//   stages {

//     stage ("init") {
//       steps {
//         script {
//           gv = load "script.groovy"
//         }
//       }
//     }
//     stage ("build jar") {
//       steps {
//         script {
//          gv.buildJar()
//         }
//       }
//     }

//     stage ("build image"){
//       steps {
//         script {
//           gv.buildImage()
          
          
//         }
//       }
//     }

//     stage ("deploy"){
//       steps{
//         script{
//           gv.deployApp()
          
//         }
//       }
//     }
//   }
// }

#!/usr/bin/env groovy

// #####define share library###

// this

// library identifier: 'jenkins-share-library-latest@jenkins-share-library', retriever: modernSCM(
//   [$class: 'GitSCMSource',]
//   remote: 'https://github.com/mehoussou/infotech-java-maven-apps/tree/jenkins-share-library-latest',
//   credentialsId:''
// )

// or below

// @Library('jenkins-share-library-latest')
def gv
pipeline{
  
  agent any
  tools{
    maven 'Maven-3.6'
  }

  stages {

    stage ("init") {
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    }
    stage ("build jar") {
      steps {
        script {
         gv.buildJar()
        }
      }
    }

    stage ("build image"){
      steps {
        script {
          buildImage 'mehousso/demo-app:v3'
          dockerLogin()
          buildPush 'mehousso/demo-app:v3'
        }
      }
    }

    stage ("deploy"){
      steps{
        script{
          gv.deployApp()
          
        }
      }
    }
  }
}

