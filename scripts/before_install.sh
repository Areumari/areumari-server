#!/bin/bash
# 필요한 패키지 설치
sudo apt-get update -y
sudo apt-get install -y docker.io docker-compose
sudo systemctl start docker
sudo usermod -a -G docker ubuntu

# 이전 배포 정리
if [ -d /home/ubuntu/areumari-server ]; then
    sudo rm -rf /home/ubuntu/areumari-server
fi
mkdir -p /home/ubuntu/areumari-server

# 로그 디렉토리 생성
mkdir -p /home/ubuntu/logs