FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD build/libs/springBootApp-0.2.0.jar devCom.jar
ENTRYPOINT ["java","-jar","/devCom.jar"]