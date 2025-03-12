#!/bin/bash

ROOT_PATH="/home/ubuntu/areumari-server"
INSTALL_LOG="$ROOT_PATH/install.log"

NOW=$(date +%c)

echo "[$NOW] 배포 파일 권한 설정" >> $INSTALL_LOG
chmod +x $ROOT_PATH/*.jar
chmod +x $ROOT_PATH/scripts/*.sh
