pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn compile install -DskipTests'
      }
    }

    stage('test') {
      steps {
        junit(allowEmptyResults: true, testResults: '**/target/*.xml')
      }
    }

    stage('check') {
      steps {
        sh 'mvn checkstyle:checkstyle'
      }
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}