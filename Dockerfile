# build environment
FROM openjdk:11 as builder
COPY . /usr/src/
WORKDIR /usr/src/
CMD mvnw clean install

# production environment
FROM openjdk:11
COPY --from=builder /usr/src/target/up42cc.jar /usr/local/app/up42cc.jar
EXPOSE 8080
CMD java -jar /usr/local/app/up42cc.jar