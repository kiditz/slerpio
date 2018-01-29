docker run -d \
  --net=host  \
  --hostname consul-server \
  --name consul-server \
  --env "SERVICE_IGNORE=true" \
  --volume consul_data:/consul/data \
  --publish 8500:8500 \
  consul:latest \
  consul agent -server -ui \
    -bootstrap-expect=1 \
    -client=0.0.0.0 \
    -advertise=172.17.0.1 \
    -data-dir="/consul" \

