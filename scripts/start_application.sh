#!/bin/bash

# 애플리케이션 디렉토리로 이동
cd /home/ubuntu/jwt

# Docker 컨테이너 빌드 및 실행
docker-compose build
docker-compose up -d

# 실행 상태 확인
docker-compose ps