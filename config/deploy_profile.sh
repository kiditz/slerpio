curl -v -X PUT --data-binary @$1  \
  -H "Content-type: text/x-yaml" \
  http://localhost:8500/v1/kv/config/$2/data

