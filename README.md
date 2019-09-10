# rehabilitationClinic

```
commmands to start project:

docker network create myntw 
docker run -d -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=rehabilitationclinic -d -p 5432:5432 --net=myntw --name postgres postgres
docker run -d -p 61616:61616 -p 8161:8161 --net=myntw rmohr/activemq 


docker build --rm -t mywebapp .
docker run -d --rm -p 8080:8080 --net=myntw --name=tomcat mywebapp 

Credentials
main doctor: zahir.durham:zahir.durham
doctor: skyler.rope:skyler.rope
nurse: kelsey.fulton:kelsey.fulton
admin: login:password
```