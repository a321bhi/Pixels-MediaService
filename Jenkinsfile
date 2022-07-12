pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        echo 'Initializing'
        bat 'mvn clean'
      }
    }

    stage('Build') {
      steps {
        bat 'mvn package'
      }
    }

    stage('Static Analysis') {
      steps {
        bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
      }
    }

    stage('Deploy') {
      steps {
        bat 'docker build -t pixels-mediaservice .'
        bat 'docker tag pixels-mediaservice abhi2104/pixels-mediaservice'
        bat 'docker push abhi2104/pixels-mediaservice'
      }
    }

    stage('') {
      agent {
        node {
          label 'jenkinsagent'
        }

      }
      steps {
        sh 'docker pull abhi2104/pixels-mediaservice'
      }
    }

  }
}