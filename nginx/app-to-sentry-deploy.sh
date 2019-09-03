#!/bin/bash
docker run --runtime=runsc-kvm -d -p 8082:80 --name nginx-web --rm -v ~/dev/nginx/www:/usr/share/nginx/html -v ~/dev/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v ~/dev/nginx/logs:/var/log/nginx nginx
sleep 5
for((i=1; i<=20; i++));
do
sleep 0.1
curl 127.0.0.1:8082
done
docker stop nginx-web
echo done!
