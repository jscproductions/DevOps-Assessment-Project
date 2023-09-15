# DevOps Training Final Project

## Setting up the Java Application
- A project about Employee details registration form using SpringBoot was choosen as the base project.
- The database it used was implemented locally. So we decided that had to create a remote DB server that had database and tables required for the project.
- We configured the sections of the application (application.properties) where connection to the database was implemented so that it was properly configured to use the remote DB server that we were setting up.

## Setting up the Remote DB Server
- An AWS EC2 instance was created to host the DB Server.
- Necessary ports were enabled to facilitate the communication with the DB Server.
- PostgreSQL was set up and the necessary database and tables were made that were required by the application.
- Made the appropriate configurations so that other servers (application server) could connect with it using the appropriate username and password.

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/dbserver.jpeg" width="800" height="500">

## Setting up Automated Backup for the DB Server
- To backup the database, an additional EBS volume was created in AWS and attached to the DB server instance. It was then mounted to the instance.
- A backup script file was created using pg_dump that would backup the db to the attached EBS volume.
- A cronjob was set up in the server so that the backup script would run automatically every day.

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/dbbackup.jpeg" width="800" height="500">

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/backup.png" width="800" height="500">

## Building and Containerising
- The application was build, compiled and packaged to a JAR file using Maven as the build tool.
- Inorder to deploy the application to Kubernetes, we made a Dockerfile to build a docker image for this application so that it could be used in to deploy to the pods in K8s cluster.
- We tested the application by running the application within a docker container first so as to make sure that everything was working fine.
- The builded docker image was then pushed to DockerHub, so that the deployment file in K8s could access the image from there.

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/dockerhub.jpeg" width="800" height="500">

## Kubernetes Cluster Creation using kubeadm
- Three EC2 instances were created; one for the KMaster and the other two for KWorker.
- We followed the official documentation of Kubernetes to install Kubeadm, Kubectl, Kubelet and other necessary dependencies such as runc, Calico Network plugin etc.
- containerd was used as the container runtime.
- Created seperate security groups for the Master and the Worker which allowed the necessary ports that had to be enabled for Nodeport Services, Kubelet API, Kubernetes API server etc.
- Worker nodes were joined by using the join command received when running the kubeadm init command in the KMaster.

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/cluster.jpeg" width="800" height="500">

## Deploying application to Cluster
- Before deploying the application, we made sure that all the components such as api server, controller-manager, scheduler etc.
- The Deployment yaml file was created to deploy the application and a Service yaml file was created to expose the application as a Service.
- The deployment was successful and created 2 replicas of the application pods as mentioned in the deployments.yml file.

## Creating the Target Group and ALB
- A target group was created comprising of the worker nodes so that it could be used as a target to route the traffic when using a load balancer.
- An Application Load Balancer (ALB) was created and configured with the necessary ports and security groups. It was then linked with the target group created for the worker nodes.
- The DNS name provided by the ALB was used to check whether the traffic was routed to the worker nodes mentioned in the target group.

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/loadBalancer.jpeg" width="800" height="500">

## Kubernetes Cluster Creation using EKS
- We also implemented the cluster using EKS.
- Node group with two worker nodes were attached to it.
- After deploying the application as a NodePort service, the node port corresponding to the application pods was retrieved.
- It was then used to create an Application Load Balancer to direct the traffic comming to the load balancer to the application pods in the worker nodes by setting the target group.


## Pipeline Creation
- A pipeline was created in Jenkins that would automate the CI/CD cycle.
- Firstly, Git was used as the SCM tool to clone the application repo.
- Maven was used to compile and package the application to jar file.
- The pipeline was integrated with SonarQube to do code analysis.
- Docker commands were incorporated to the pipeline script to create the docker image and push it to DockerHub.
- Nexus was integrated with the pipeline to upload artifact to Nexus Repo.
- After Nexus stage, the application was deployed to the Kubernetes cluster.
- To integrate K8s cluster with Jenkins, a service agent was created in K8s and necessary roles to deploy the deployment and service was assigned to it. Then the token for the service account was created and used as credential in Jenkins.

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/pipeline.jpeg" width="800" height="500">

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/sonarqube.jpeg" width="800" height="500">

<img src="https://github.com/jscproductions/FinalProject/blob/main/Screenshots/nexus.png" width="800" height="500">

