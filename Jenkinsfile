pipeline {
    agent any 
    
    environment {
        AZURE_SUBSCRIPTION_ID='93b29c60-2065-47c7-80a5-3ccfc04f94dd'
        AZURE_TENANT_ID='7d065a49-9136-4db6-90fb-d9763238ada2'
        CONTAINER_REGISTRY='dockerImagesReg'
        RESOURCE_GROUP='myResourceGroup'
        REPO="dockerimagesreg.azurecr.io"
        IMAGE_NAME="apiservice"
        TAG="dev"
    }

    stages {
        stage('Build') { 
            steps {
                sh 'mvn clean install -DskipTests=true '
            }
        }
        stage('Build and Push Docker Image') { 
            
            steps {
                withCredentials([usernamePassword(credentialsId: 'myAzureCredential', passwordVariable: 'AZURE_CLIENT_SECRET', usernameVariable: 'AZURE_CLIENT_ID')]) {
                            sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
                            sh 'az account set -s $AZURE_SUBSCRIPTION_ID'
                            sh 'az acr login --name $CONTAINER_REGISTRY --resource-group $RESOURCE_GROUP'
                            sh 'az acr build --image $REPO/$IMAGE_NAME:$TAG --registry $CONTAINER_REGISTRY --file Dockerfile . '
                        }
            }            
            
        }
        stage('Test') { 
            steps {
                print 'Test'
            }
        }
    }
}
