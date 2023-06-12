#!/bin/bash

# install and start docker
sudo yum update -y && sudo yum install -y docker
sudo systemctl start docker

# add ec2-user to docker group to allow it to call docker commands
sudo usermod -aG docker ec2-user

# install docker-compose 
sudo curl -L "https://github.com/docker/compose/releases/download/v2.18.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose