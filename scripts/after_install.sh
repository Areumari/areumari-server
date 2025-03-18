#!/bin/bash
cd /home/ubuntu/areumari-server || exit

# 환경 변수 파일 생성 (RDS 접속 정보 등)
cat > .env << EOL
DB_HOST=database-2.c186ei0q4wh2.ap-northeast-2.rds.amazonaws.com
DB_USER=admin
DB_PASSWORD=amgfaa289
DB_NAME=database-2
SPRING_PROFILES_ACTIVE=prod
EOL

# 스크립트 실행 권한 부여
chmod +x ./scripts/*.sh

# Docker 이미지 빌드
sudo docker build -t areumari-server .

exit 0