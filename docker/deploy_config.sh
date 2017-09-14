curl -X PUT --data-binary @$1 \
  -H "Content-type: text/x-yaml" \
  http://172.20.0.1:8500/v1/kv/$2
