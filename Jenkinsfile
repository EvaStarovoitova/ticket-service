pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java21'
    }

    stages {
        stage('Build') {
            steps {
                bat 'call mvn compile'
            }
        }

        stage('Test') {
            steps {
                bat 'call mvn test -DforkCount=0'
            }
        }

        stage('Quality Check & Package') {
            steps {
                bat 'call mvn validate && call mvn package -DskipTests'
            }
        }
    }
}
