
pipeline{
  
  agent any
  environment {
    NEW-VERSION = '1.3.0'
    // BRANCH_NAME = "lab"
  }
  
  parameters {
    choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description:'')
    booleanParam(name: 'executeTests', defaultValue: true, description: '')
  }
  stages {
    
    stage("build"){
      steps {
        echo 'building the application...'
        echo "{building version ${NEW_VERSION}"
      }
    }
    stage("test"){
      when {
        expression{
          params.executeTests
        }
      }
      steps {
        echo 'testing the application...'
      }
    }
    
    stage("deploy"){
      steps {
        echo 'deploying the application...'
        echo "deploying version ${params.VERSION}"
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


