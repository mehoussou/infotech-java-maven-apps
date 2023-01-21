
pipeline{
  
  agent any
  environment {
    NEW-VERSION = '1.3.0'
    BRANCH_NAME = "lab"
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
          BRANCH_NAME == "labs"
        }
      }
      steps {
        echo 'testing the application...'
      }
    }
    
    stage("deploy"){
      steps {
        echo 'deploying the application...'
      }
    }
    
  }

  post {
    always{
      echo "we are testing the pipeline"
    }
    sucess {
      echo "Our pipeline is successful run"
    }
    failure {
      echo "Our pipeline is failed"
    }
  }
}


