
def gv
pipeline{
  
  agent any
  environment {
    NEW_VERSION = '1.3.0'
    // BRANCH_NAME = "lab"
  }
  
  parameters {
    choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description:'')
    booleanParam(name: 'executeTests', defaultValue: true, description: '')
  }
  stages {

    stage("init"){
      steps {
        script{
          gv = load "script.groovy"

        }
      }

    }
    
    stage("build"){
      steps {
        script {
          gv.buildApp()
        } 
      }
    }
    stage("test"){ 
      when {
        expression{
          params.executeTests
        }
      }
      steps {
        script{
          gv.testApp()
        }
      }
    }
    
    stage("deploy"){
      input {
        message "Select the environment to deploy to"
        ok "Done"
        parameters{
          choice(name: 'ONE', choices: ['dev', 'staging', 'prod'], description: '')
          choice(name: 'TWO', choices: ['dev', 'staging', 'prod'], description: '')
        }
      }
      steps {
        script {
          gv.deployApp()
          echo "Deploying to ${ONE}"
          echo "Deploying to ${TWO}"
        }
      }
    }
  }
}

//   post {
//     always{
//       echo "we are testing the pipeline"
//     }
//     sucess {
//       echo "Our pipeline is successful run"
//     }
//     failure {
//       echo "Our pipeline is failed"
//     }
//   }
// }


