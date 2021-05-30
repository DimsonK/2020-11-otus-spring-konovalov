#!/usr/bin/env bash
docker image prune -f
docker container prune -f
docker volume prune -f
docker network prune -f
docker system prune -f
