pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn --batch-mode -V -U -e clean verify package -Dsurefire.useFile=false -Dmaven.test.failure.ignore'
      }
    }

    stage('Analysis') {
      steps {
        sh 'mvn --batch-mode -V -U -e checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs spotbugs:spotbugs'
      }
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}