// Source implementation: src/test/java/com/dtcc/automation/cicd/CiCdConfigurationTest.java
// Purpose: Confirms Jenkinsfile/GitHub Actions exist and publish test reports.
pipeline {
  agent any
  stages { stage('Test') { steps { sh 'mvn test -Ptestcases' } } }
  post { always { junit 'target/surefire-reports/*.xml' } }
}
