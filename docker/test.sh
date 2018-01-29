vms=( "manager1" "manager2" "manager3" )
 
for vm in ${vms[@]:1:2}
do
  echo ${vm}
done
