vms=("manager1")
SWARM_MANAGER_IP=$(docker-machine ip manager1)
echo ${SWARM_MANAGER_IP}
 
docker-machine ssh manager1 \
  "docker swarm init \
  --advertise-addr ${SWARM_MANAGER_IP}"
  
echo "Script completed..."
