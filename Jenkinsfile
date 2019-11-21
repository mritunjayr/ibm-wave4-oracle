pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn compile package install'
      }
    }

    stage('test') {
      steps {
        junit(allowEmptyResults: true, testResults: '**/target/*.xml')
      }
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}