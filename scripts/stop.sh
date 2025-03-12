#!/bin/bash

ROOT_PATH="/home/ubuntu/areumari-server"
JAR="$ROOT_PATH/application.jar"
STOP_LOG="$ROOT_PATH/stop.log"

SERVICE_PID=$(pgrep -f $JAR) # 실행 중인 Spring 서버의 PID 찾기

if [ -z "$SERVICE_PID" ]; then
  echo "$(date +%c) : 서비스 Not Found" >> $STOP_LOG
else
  echo "$(date +%c) : 서비스 종료 (PID: $SERVICE_PID)" >> $STOP_LOG
  kill "$SERVICE_PID"
  sleep 5

  # 프로세스가 종료되지 않으면 강제 종료
  SERVICE_PID=$(pgrep -f $JAR)
  if [ -n "$SERVICE_PID" ]; then
    echo "$(date +%c) : 강제 종료 (PID: $SERVICE_PID)" >> $STOP_LOG
    kill -9 "$SERVICE_PID"
  fi
fi
