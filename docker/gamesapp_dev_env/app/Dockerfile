FROM develar/java

MAINTAINER David Cuellar, davidokun99@gmail.com

COPY app-games.jar /usr/local/app-games.jar
WORKDIR /usr/local/

ENTRYPOINT ["java", "-jar", "app-games.jar", "--acceptLicense", "/usr/local/"]


