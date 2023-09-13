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

## Setting up Automated Backup for the DB Server
- To backup the database, an additional EBS volume was created in AWS and attached to the DB server instance. It was then mounted to the instance.
- A backup script file was created using pg_dump that would backup the db to the attached EBS volume.
- A cronjob was set up in the server so that the backup script would run automatically every day.

## Building and Containerising
- The application was build, compiled and packaged to a JAR file using Maven as the build tool.
- 
