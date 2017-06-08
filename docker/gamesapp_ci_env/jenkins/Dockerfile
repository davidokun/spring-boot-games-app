FROM jenkins

MAINTAINER David Cuellar, davidokun99@gmail.com

ENV MAVEN_VERSION=3.5.0

USER root

WORKDIR /usr/local/

ADD data/jobs /var/jenkins_home
ADD data/users /var/jenkins_home
ADD data/plugins /var/jenkins_home


RUN wget http://www.eu.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz
RUN tar xzf apache-maven-${MAVEN_VERSION}-bin.tar.gz
RUN rm apache-maven-${MAVEN_VERSION}-bin.tar.gz
RUN mv apache-maven-${MAVEN_VERSION} /opt/
ENV MAVEN_HOME=/opt/apache-maven-${MAVEN_VERSION}
ENV PATH "$PATH":${MAVEN_HOME}/bin:.: