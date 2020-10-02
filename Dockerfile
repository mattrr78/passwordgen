FROM adoptopenjdk/openjdk13
COPY build/libs/passwordgen-1.0.1.jar passwordgen-1.0.1.jar
ENTRYPOINT ["java","-jar","/passwordgen-1.0.1.jar"]