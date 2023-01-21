pipeline{
  
  agent any
  
  stages {
    
    stage("build"){
      steps {
        echo 'building the application...'
      }
    }
    stage("test"){
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
}

post {
  always{
    echo "we are testing the pipeline"

  }
  sucess {
    echo "Our pipeline is successful run"
  }
  failed{
    echo "Our pipeline is failed"

  }

}