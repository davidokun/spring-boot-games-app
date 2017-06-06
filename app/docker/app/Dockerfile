FROM develar/java

MAINTAINER David Cuellar, davidokun99@gmail.com

COPY spring-boot-games-app-0.0.1-SNAPSHOT.jar /usr/local/spring-boot-games-app-0.0.1-SNAPSHOT.jar
WORKDIR /usr/local/

ENTRYPOINT ["java", "-jar", "spring-boot-games-app-0.0.1-SNAPSHOT.jar", "--acceptLicense", "/usr/local/"]


