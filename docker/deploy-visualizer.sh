docker service create \
  --name swarm-visualizer \
  --publish 5001:8080/tcp \
  --constraint node.role==manager \
  --mode global \
  --mount type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock \
  manomarks/visualizer:latest
