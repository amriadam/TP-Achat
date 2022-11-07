pipeline{
    agent any
    
    stages{
        stage('GIT'){
            steps{
                sh "ls"
                echo "getting code from git";
                
                git "https://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@github.com/amriadam/TP-Achat.git";    
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
        /*stage('MVN SONARQUBE'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=1111"
            }
        }*/
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
        
        /*stage('NEXUS'){
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
        }*/
        stage('DOCKER'){
            steps{
                //sh 'docker-compose --version'
                //sh  'docker compose ps'
                sh 'docker-compose up'
                
                
            }
        }
        
    }
}
