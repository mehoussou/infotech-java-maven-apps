pipeline{
  
  agent any
  tools{
    maven 'Maven-3.6'
  }

  stages {
    stage ("build jar") {
      steps {
        script {
          echo "building the application..."
          sh 'mvn package'
        }
      }
    }

    stage ("build image"){
      steps {
        script {
          echo "building the docker image..."
          withCredentials([usernamePassword(credentialsID:'docker-hub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]){
            sh 'docker build -t mehousso/demo-app:v2 .'
            sh "echo $PASS | docker login -u $USER --password-stdin"
            sh 'docker push mehousso/demo-app:v2'
          }
        }
      }
    }

    stage ("deploy"){
      steps{
        script{
          echo "deploying the application..."
        }
      }
    }
  }
}



