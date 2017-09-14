vms=( "manager1" "worker1" )
 
for vm in ${vms[@]}
do
  docker-machine create \
    --driver virtualbox \
    --virtualbox-memory "512" \
    --virtualbox-cpu-count "1" \
    --virtualbox-disk-size "10000" \
    ${vm}
done
