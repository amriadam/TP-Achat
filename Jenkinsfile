pipeline{
    agent any
    
    stages{
        stage('GIT'){
            steps{
                sh "ls"
                echo "getting code from git";
                git branch : 'adam',
                url :  "https://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@github.com/amriadam/TP-Achat.git";    
            }
        }
        stage('MVN CLEAN'){
            steps{
                sh "mvn clean"
            }
        }
        stage('MVN COMPILE'){
            steps{
                sh "mvn compile"
            }
        }
        stage('MVN SONARQUBE'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=1111"
            }
        }
        stage('DOCKER COMPOSE1'){
            steps{
                //sh 'docker-compose --version'
                //sh  'docker compose ps'
                sh 'docker-compose up'
                
                
            }
        }
        stage('MVN TEST'){
            steps{
                sh "mvn test"
            }
        }
        stage('MVN BUILD'){
            steps{
                sh "mvn clean install package -Dmaven.test.skip=true"
                
            }
        }
        
        stage('NEXUS'){
            steps{
                nexusArtifactUploader artifacts: [
                    [
                        artifactId: 'tpAchatProject', 
                        classifier: '', 
                        file: 'target/tpAchatProject-1.0.jar', 
                        type: 'jar'
                    ]
                ], 
                credentialsId: 'nexus3', 
                groupId: 'com.esprit.examen', 
                nexusUrl: '192.168.44.128:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'achat-adam-release', 
                version: '1.0' 
            }
        }
        stage('Build Docker Image') {
                 steps {
                 sh 'docker build -t adamelamri/adamback:1.0.0 .'
                 }
              }

              stage('Push Docker Image') {
                   steps {
                     sh 'docker login -u "adamelamri" -p "5;X#,;+5Z_PfvfM" docker.io'
                     sh 'docker push adamelamri/adamback:1.0.0 '
                   }
              }
        stage('DOCKER COMPOSE'){
            steps{
                //sh 'docker-compose --version'
                //sh  'docker compose ps'
                sh 'docker-compose up'
                
                
            }
        }
        
    }
    post {
                      success {
                        
                            emailext body: 'Pipeline build successfully', subject: 'Pipeline build', to: 'adam.elamri@esprit.tn'
                      }
                      failure {
                        
                            emailext body: 'Pipeline failure', subject: 'Pipeline failure', to: 'adam.elamri@esprit.tn'
                      }
              }
}
