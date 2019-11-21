pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn compile install -DskipTests'
          }
        }

        stage('Check') {
          steps {
            sh 'mvn checkstyle:checkstyle'
          }
        }

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