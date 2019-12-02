pipeline {
    agent any 
    stages {
        stage('Clone Repo') {
            steps {
                dir ('C:/Program Files (x86)/Jenkins/workspace/ShoppingCart') {
                   deleteDir()
                }
                echo '============================= Clone Repo for ShoppingCart ============================='
                bat 'git clone https://github.com/markwaynedeclaro/ShoppingCart.git'
                bat 'mvn clean -f ShoppingCart'
            }
        }
        stage('Test') {
            steps {
                echo '============================= Test the ShoppingCart App ============================='
                bat 'mvn test -f ShoppingCart'
            }
        }
        stage('Deploy') {
            steps {
                echo '============================= Deploy the ShoppingCart App ============================='
                bat 'mvn package -f ShoppingCart'
            }
        }
    }
}