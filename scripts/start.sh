#!/bin/bash
cd /home/ubuntu/areumari-server || exit

# Docker 컨테이너 실행
sudo docker run -d --name areumari-container \
  -p 80:8080 \
  --env-file .env \
  --restart always \
  areumari-server

# 상태 확인
sudo docker ps | grep areumari-container

# 로그 출력 시작
echo "Application started at $(date)" >> /home/ubuntu/logs/deploy.log

exit 0