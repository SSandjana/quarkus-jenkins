pipeline {
    agent any

    tools {

            maven "M3"
        }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean package'
                archiveArtifacts artifacts: '**/target/*.jar'
            }
        }
    }
}