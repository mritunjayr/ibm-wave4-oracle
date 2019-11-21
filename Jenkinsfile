pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -DskipTests -V -U -e clean verify package -Dsurefire.useFile=false -Dmaven.test.failure.ignore'
      }
    }

    stage('Analysis') {
      parallel {
        stage('Analysis') {
          steps {
            sh 'mvn -V -U -e checkstyle:checkstyle pmd:pmd pmd:cpd spotbugs:spotbugs'
          }
        }

        stage('Test-resport') {
          steps {
            sh 'junit \'**/target/surefire-reports/TEST-*.xml\''
          }
        }

      }
    }

  }
  post {
    always {
      recordIssues(enabledForFailure: true, tools: [mavenConsole(), java(), javaDoc()])
      recordIssues(enabledForFailure: true, tool: checkStyle())
      recordIssues(enabledForFailure: true, tool: spotBugs())
      recordIssues(enabledForFailure: true, tool: cpd(pattern: '**/target/cpd.xml'))
      recordIssues(enabledForFailure: true, tool: pmdParser(pattern: '**/target/pmd.xml'))
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}