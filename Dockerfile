FROM openjdk:17

MAINTAINER cothis jhstl1203@gmail.com

RUN apt update && apt install -y vim

# areumari 폴더 생성
RUN mkdir -p /areumari

WORKDIR /areumari

# JAR 파일을 컨테이너에 복사 - 경로를 수정하세요
COPY build/libs/*.jar app.jar

# 포트 노출 (SpringBoot 기본 포트)
EXPOSE 8080

# SpringBoot 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]