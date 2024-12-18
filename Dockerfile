# OpenJDK 21 기반 이미지 사용
FROM openjdk:21-jdk

# 작성자 정보
LABEL authors="newneeew"

# Spring Boot JAR 파일 복사
ARG JAR_FILE=build/libs/shop-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 포트 설정
EXPOSE 80

# Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
