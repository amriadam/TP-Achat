pipeline{
    agent any
    
    stages{
        stage('GIT'){
            steps{
                sh "ls"
                echo "getting code from git";
                git branch : 'jihene',
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
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=jihene"
            }
        }
        /*stage('MVN TEST'){
            steps{
                sh "mvn test"
            }
        }*/
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
                nexusUrl: '172.16.1.215:8080', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'simpleapp-release', 
                version: '1.0' 
            }
        }
     stage('Build Docker Image') {
                 steps {
                 sh 'docker build -t jihenesliti/jiheneback:1.0.0 .'
                 }
              }

             stage('Push Docker Image') {
                   steps {
                     sh 'docker login -u jihenesliti -p jihene123 docker.io'
                     sh 'docker push jihenesliti/jiheneback:1.0.0 '
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
}
