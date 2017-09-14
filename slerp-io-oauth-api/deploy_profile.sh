curl -v -X PUT --data-binary @$1 \
  -H "Content-type: text/x-yaml" \
  http://192.168.43.42:8500/v1/kv/$2

