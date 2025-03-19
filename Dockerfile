FROM openjdk:17-jdk

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션 파일 복사
COPY . .

# 환경 변수 설정
ENV DB_HOST=database-2.c186ei0q4wh2.ap-northeast-2.rds.amazonaws.com
ENV DB_PORT=3306
ENV DB_NAME=database-2
ENV DB_USER=admin
ENV DB_PASSWORD=amgfaa289

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
CMD ["java", "-jar", "target/jwt-0.0.1-SNAPSHOT.jar"]