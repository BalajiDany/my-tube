# My tube
Show case rest API to implement following Web intends

 - Authentication
 - Basic Auth
 - Authorization
 - AWS Integration
 - Restful Service
 - File Upload and Download

## Concept

User can able to login and upload the files. which will be uploded to  AWS S3 Buckets, configured in the [application.yml](https://github.com/BalajiDany/my-tube/blob/master/src/main/resources/application.yml) file.

Admin role has access to view all  users and their uploaded files.

## Setup
1. Clone the Project
2. Set Environment Variables.
3. Build and Run by Maven

## End Points
For Detail Please refer controller

 - [userDetailsController.java](https://github.com/BalajiDany/my-tube/blob/master/src/main/java/com/showcase/mytube/controller/UserDetailsController.java)
 - [userAssertController.java](https://github.com/BalajiDany/my-tube/blob/master/src/main/java/com/showcase/mytube/controller/UserAssertController.java)

