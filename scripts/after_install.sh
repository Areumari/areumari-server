#!/bin/bash

# 애플리케이션 디렉토리로 이동
cd /home/ubuntu/jwt

# 스크립트 파일에 실행 권한 부여
sudo chmod +x /home/ubuntu/jwt/scripts/*.sh

# 환경 변수 파일 생성
cat > /home/ubuntu/jwt/.env << EOF
DB_HOST=database-2.c186ei0q4wh2.ap-northeast-2.rds.amazonaws.com
DB_PORT=3306
DB_USER=admin
DB_PASSWORD=amgfaa289
DB_NAME=database-2
EOF

# 환경 변수 파일 권한 설정
sudo chown ubuntu:ubuntu /home/ubuntu/jwt/.env
sudo chmod 600 /home/ubuntu/jwt/.env

# Docker 이미지 빌드를 위한 권한 설정
sudo chown -R ubuntu:ubuntu /home/ubuntu/jwt