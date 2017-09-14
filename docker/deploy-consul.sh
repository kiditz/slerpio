SWARM_MANAGER_IP=$1
echo ${SWARM_MANAGER_IP}
docker run -d \
  --net=host  \
  --hostname $2 \
  --name $2 \
  --env "SERVICE_IGNORE=true" \
  --volume consul_data:/consul/data \
  --publish 8500:8500 \
  consul:latest \
  consul agent -server -ui \
    -bootstrap \
    -client=0.0.0.0 \
    -advertise=${SWARM_MANAGER_IP} \
    -data-dir="/consul" \
