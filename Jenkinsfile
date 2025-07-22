
pipeline {
    agent any

    tools {
        // Make sure these match the names configured in Jenkins global tool settings
        jdk 'JDK 17'
        maven 'Maven 3.8.1'
    }

    environment {
        PATH = "${tool 'Maven 3.8.1'}/bin:${env.PATH}"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/san199r/Automated-Login-Form-Testing-Using-Selenium.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Publish JUnit-compatible reports from TestNG
            junit allowEmptyResults: true, testResults: 'test-output/testng-results.xml'
        }

        success {
            echo '✅ Build and tests passed successfully!'
        }

        failure {
            echo '❌ Build or tests failed.'
        }
    }
}
