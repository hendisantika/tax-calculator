# tax-calculator

Simple Tax Calculator using Spring Boot MySQL Docker Compose

### Things to do :

1. Building the docker image from project

   first you need to build the application. This can be done with following command

   `./mvnw clean install`

2. Once the project is buit successfully, we can build the docker image with following command.

   `docker build -f Dockerfile -t tax-calculator .`

3. Once the above process is completed, you can verify whether the docker image is built successfully with following command. It will show you a list of docker images available.

   `docker images`

4. Running the built docker image

   Now we need to run the built docker image of our spring boot application. Since this application requires to connect with MySQL server, we need to make sure that MySQL server is up and running.

   You can check the currently up and running docker containers with following command.

   `docker ps`

   If the MySQL container is not up and running, you need to run it now.

5. Link with MySQL Container.

   Once the mysql container is up and running, you can run your spring boot application image on container with following command.  You need to link your spring boot application with mysql container.

   `docker run -t --name tax-calculator-container --link mysql-docker-container:mysql -p 8087:8080 tax-calculator`

6. Now we can check and verify whether both containers (mysql and spring boot application containers) are up and running.

   `docker ps`

7. Verify containers are linked properly

   To verify whether the containers are linked properly, you can get into the application container (tax-calculator-container) and see the content of the /etc/hosts file.

   **login to container with bash mode**

   `docker exec -it tax-calculator-container bash`
   tax-calculator-container is the name of the container that we need to access.  bash param says that we need the bash access.

   see the content of /etc/hosts ( cat /etc/hosts )