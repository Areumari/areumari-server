#!/bin/bash
cd /home/ubuntu/areumari-server || exit

# 기존 Docker 컨테이너 중지 및 삭제
sudo docker stop areumari-container || true
sudo docker rm areumari-container || true

# 사용하지 않는 이미지 정리 (선택사항)
sudo docker image prune -f

exit 0