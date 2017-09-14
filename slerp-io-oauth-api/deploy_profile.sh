curl -v -X PUT --data-binary @/home/kiditz/slerp-git/slerpio/config/slerp-oauth/slerp-oauth.yml  \
  -H "Content-type: text/x-yaml" \
  http://192.168.43.42:8500/v1/kv/config/slerp-oauth/data

