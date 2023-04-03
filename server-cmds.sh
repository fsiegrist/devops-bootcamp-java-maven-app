#!/usr/bin/env/ bash

export IMAGE_TAG=$1
docker-compose -f docker-compose.yaml up -d
echo "successfully started the container using docker-compose"