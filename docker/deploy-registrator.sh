vms=( "manager1" )
 
for vm in ${vms[@]}
do
  echo ${vm}
  docker-machine env ${vm}
  eval $(docker-machine env ${vm})
 
  HOST_IP=$(docker-machine ip ${vm})
  echo ${HOST_IP}
 
  docker run -d \
    --name=registrator \
    --net=host \
    --volume=/var/run/docker.sock:/tmp/docker.sock \
    gliderlabs/registrator:latest \
      -internal consul://${HOST_IP:localhost}:8500
done
