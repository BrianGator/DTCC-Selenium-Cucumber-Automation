pipeline { agent any; stages { stage('Test') { steps { sh 'mvn test -Ptestcases' } } } post { always { junit 'target/surefire-reports/*.xml' } } }
