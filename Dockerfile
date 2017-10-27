##############################################################
##                      Base Image                          ##
##############################################################
FROM    centos:7.4.1708 AS base

# Install OpenJDK
RUN     yum update -y && \
        yum install -y java-1.8.0-openjdk-devel && \
        yum clean -y all
ENV     JAVA_HOME="/etc/alternatives/java_sdk"

# Install CFSSL (CloudFlare's TLS Toolkit)
ARG     CFSSL_VERSION="1.2"
ARG     CFSSL_BIN_URL="https://pkg.cfssl.org/R${CFSSL_VERSION}/cfssl_linux-amd64"
ARG     CFSSL_BIN_SHA256="eb34ab2179e0b67c29fd55f52422a94fe751527b06a403a79325fed7cf0145bd"
RUN     curl -L -o /usr/local/bin/cfssl "${CFSSL_BIN_URL}" && \
        echo "${CFSSL_BIN_SHA256} /usr/local/bin/cfssl" | sha256sum -c - && \
        chmod 755 /usr/local/bin/cfssl

##############################################################
##                      Build Stage                         ##
##############################################################
FROM    base AS build-env

# Install Apache Maven
ARG     MAVEN_VERSION="3.5.0"
ARG     MAVEN_ARCHIVE_URL="http://www.trieuvan.com/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz"
ARG     MAVEN_ARCHIVE_SHA256="beb91419245395bd69a4a6edad5ca3ec1a8b64e41457672dc687c173a495f034"
RUN     curl -L -o /tmp/mvn.tar.gz "${MAVEN_ARCHIVE_URL}" && \
        echo "${MAVEN_ARCHIVE_SHA256} /tmp/mvn.tar.gz" | sha256sum -c - && \
        tar xzvf /tmp/mvn.tar.gz -C /opt/ && \
        rm -f /tmp/mvn.tar.gz && \
        ln -s "/opt/apache-maven-${MAVEN_VERSION}" /opt/maven
ENV     PATH="${PATH}:/opt/maven/bin"

# Prepare the build directory
ARG     BUILD_DIR="/build"
WORKDIR "${BUILD_DIR}"

# Build the application
COPY    . "${BUILD_DIR}/"
RUN     mvn clean package

##############################################################
##                    Execution Stage                       ##
##############################################################
FROM    base

# Application info
ARG     APP_NAME="kube-pki"
ARG     APP_VERSION="1.0.0-SNAPSHOT"
ARG     APP_DIR="/opt/${APP_NAME}-${APP_VERSION}"

# Install the application
RUN     mkdir -p "${APP_DIR}/bin" && \
        ln -s "${APP_DIR}" "/opt/${APP_NAME}"
COPY    --from="build-env" "/build/target/${APP_NAME}-${APP_VERSION}.jar" "${APP_DIR}/bin/${APP_NAME}.jar"

# Start the application
CMD     ["java", "-jar", "/opt/kube-pki/bin/kube-pki.jar"]
