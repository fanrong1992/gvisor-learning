#!/bin/bash
CID=$(docker run --runtime=runsc-kvm -d -p 8082:80 --name nginx-web --rm -v ~/dev/nginx/www:/usr/share/nginx/html -v ~/dev/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v ~/dev/nginx/logs:/var/log/nginx nginx)
PID=$(docker inspect -f='{{.State.Pid}}' $CID)
sleep 5
sudo strace -ff -o syslog/log -p $PID &
for((i=1; i<=100; i++));
do
sleep 0.001
curl -s 127.0.0.1:8082 > /dev/null
done
sleep 5
find syslog/ -name "log.*" > syslog/list
python3 pythonfile/collect_sys_stoh.py >> py_out
docker stop nginx-web
cat py_out
sudo rm syslog/*
