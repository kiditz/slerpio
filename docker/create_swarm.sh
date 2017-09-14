vms=( "manager1")
SWARM_MANAGER_IP=$(docker-machine ip manager1)
echo ${SWARM_MANAGER_IP}
 
docker-machine ssh manager1 \
  "docker swarm init \
  --advertise-addr ${SWARM_MANAGER_IP}"

WORKER_SWARM_JOIN=$(docker-machine ssh manager1 "docker swarm join-token worker")
WORKER_SWARM_JOIN=$(echo ${WORKER_SWARM_JOIN} | grep -E "(docker).*(2377)" -o)
WORKER_SWARM_JOIN=$(echo ${WORKER_SWARM_JOIN//\\/''})
echo ${WORKER_SWARM_JOIN}

# three worker nodes
for vm in ${vms[@]:1:1}
do
  echo ${vm}
  docker-machine ssh ${vm} ${WORKER_SWARM_JOIN}
done

docker node ls

echo "Script completed..."
