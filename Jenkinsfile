pipeline {
    agent any

    options {
        timeout(time: 45, unit: 'MINUTES')
        disableConcurrentBuilds()
        timestamps()
    }

    parameters {
        choice(name: 'TEST_ENVIRONMENT', choices: ['qa', 'stage'], description: 'Environment profile used by config.properties.')
        choice(name: 'SUITE_TARGET', choices: ['All', 'UI-E2E', 'API-Backend', 'Karate-API', 'Unit'], description: 'Select the automation suite target.')
        choice(name: 'BROWSER', choices: ['chrome', 'edge'], description: 'Browser target for Selenium UI execution.')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser tests in headless mode.')
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
        APP_ENV = "${params.TEST_ENVIRONMENT}"
    }

    stages {
        stage('Checkout From Git / Bitbucket') {
            steps {
                checkout scm
            }
        }

        stage('Toolchain Verification') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'git --version'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn -q clean compile test-compile -DskipTests'
            }
        }

        stage('Execute Selected Test Matrix') {
            steps {
                script {
                    def cmd = ''
                    if (params.SUITE_TARGET == 'UI-E2E') {
                        cmd = "mvn test -Pui -Denv=${APP_ENV} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS}"
                    } else if (params.SUITE_TARGET == 'API-Backend') {
                        cmd = "mvn test -Papi -Denv=${APP_ENV} -Dtest=BackendTransactionTest,DataDrivenTransactionTest"
                    } else if (params.SUITE_TARGET == 'Karate-API') {
                        cmd = "mvn test -Dtest=KarateApiRunner -Dkarate.env=${APP_ENV}"
                    } else if (params.SUITE_TARGET == 'Unit') {
                        cmd = "mvn test -Dtest=OrderCalculatorTest"
                    } else {
                        cmd = "mvn test -Pall -Denv=${APP_ENV} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS}"
                    }
                    catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                        sh cmd
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/cucumber-reports/**/*,target/failed-test-screenshots/**/*,target/karate-reports/**/*'
            cleanWs deleteDirs: true, notFailBuild: true
        }
        failure {
            echo 'Automation regression failure detected. Review reports, screenshots, and Jira defect template in docs/defects.'
        }
    }
}


// Additional portfolio commands:
// Expanded SDET suite: mvn test -Pexpanded
// Intentional failure demo: mvn test -Pfailure-demo -DincludeIntentionalFailures=true
// Publish reports with Jenkins JUnit, Cucumber, and HTML Publisher plugins.
// Recommended CloudBees/Jenkins parameter value: Expanded-SDET-Showcase
