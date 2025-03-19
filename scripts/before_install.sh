#!/bin/bash

# 필요한 패키지 설치
sudo apt-get update
sudo apt-get install -y docker.io docker-compose

# Docker 서비스 시작 및 부팅 시 자동 시작 설정
sudo systemctl start docker
sudo systemctl enable docker

# ubuntu 사용자에게 Docker 권한 부여
sudo usermod -aG docker ubuntu

# 이전 애플리케이션 디렉토리 정리
if [ -d /home/ubuntu/jwt ]; then
    sudo rm -rf /home/ubuntu/jwt
fi

# 애플리케이션 디렉토리 생성
sudo mkdir -p /home/ubuntu/jwt
sudo chown ubuntu:ubuntu /home/ubuntu/jwt