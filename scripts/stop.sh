#!/bin/bash

# 애플리케이션 디렉토리로 이동
cd /home/ubuntu/jwt

# 이미 실행 중인 Docker 컨테이너 중지
if [ -f docker-compose.yml ]; then
    docker-compose down
else
    # docker-compose.yml이 없는 경우 관련 컨테이너 찾아서 중지
    CONTAINERS=$(docker ps -q --filter "name=jwt")
    if [ ! -z "$CONTAINERS" ]; then
        docker stop $CONTAINERS
        docker rm $CONTAINERS
    fi
fi

# 사용하지 않는 Docker 이미지 정리 (선택 사항)
docker image prune -f