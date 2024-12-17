# Tomcat 9 + Java 21 기반 이미지 사용
FROM tomcat:9.0.83-jdk21

# 작성자 정보
LABEL authors="newneeew"

# Spring Boot JAR 파일 복사
ARG JAR_FILE=build/libs/springboot-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /usr/local/tomcat/webapps/app.jar

# 포트 설정
EXPOSE 80

# Spring Boot 애플리케이션 실행
CMD ["java", "-jar", "/usr/local/tomcat/webapps/app.jar"]
