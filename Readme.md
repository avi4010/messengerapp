# Messengerapp

This is a messengerapp which is built on Java API's backend and HTML, CSS and Javascript as frontend

  - This webapp performs all the basic CRUD operations
  - Database used is MySQL 5.7 version
  - Phpmyadmin is used to view data of the MySQL container running.

### Installation

  - Install docker and docker-compose from official dockerhub site
  - Download or pull the code into a new directory.
  - Open the terminal in that folder where the code is present

Type in the below command to build the Image of messenger app:
```sh
$ docker-compose build
```

After the build is completed, start the messengerapp

```sh
$ docker-compose up -d
```

To verify, whether all services are up (All the services should be up)
```sh
$ docker-compose ps
```

By default, docker-compose file is configured to run the application in 8079 port of host. To view the application in browser, access the below URL
```sh
http://localhost:8079
```

By default, docker-compose file is configured to visualize the DB in 8078 port of host. To visualize the data in MYSQL DB. Type the below URL in the browser. (username and password for the below URL is root)
```sh
http://localhost:8078
```
### Installation (Alternate way)
Navigate into 'messenger_buildIn_Image' folder and then open terminal at that location
Start the messengerapp

```sh
$ docker-compose up -d
```