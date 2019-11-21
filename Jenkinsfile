pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn --version'
      }
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}