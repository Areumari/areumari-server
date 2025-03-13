#!/bin/bash

ROOT_PATH="/home/ubuntu/areumari-server2"
JAR="$ROOT_PATH/application.jar"

APP_LOG="$ROOT_PATH/application.log"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"

NOW=$(date +%c)

echo "[$NOW] 실행 가능한 JAR 찾기" >> $START_LOG
JAR_FILE=$(find $ROOT_PATH/build/libs -name "*.jar" | sort | tail -n 1)

if [ -z "$JAR_FILE" ]; then
  echo "[$NOW] 실행할 JAR 파일이 없습니다." >> $START_LOG
  exit 1
fi

echo "[$NOW] $JAR_FILE 복사 -> $JAR" >> $START_LOG
cp "$JAR_FILE" "$JAR"

echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG
