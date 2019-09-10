FROM tomcat:9.0-alpine
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
ADD ./target/RehabilitationClinic.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]