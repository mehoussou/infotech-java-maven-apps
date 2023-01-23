def buildJar(){
    echo "Building the application..."
        sh 'mvn package'
}

def buildImage(){
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId:'docker-hub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh 'docker build -t mehousso/demo-app:v2 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push mehousso/demo-app:v2'
    }
}

def deployApp(){
    echo "deploying the application..."
}

return this