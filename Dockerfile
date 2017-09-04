FROM postgres:9.4
MAINTAINER ...

# Custom initialization scripts

COPY create_db.sh /docker-entrypoint-initdb.d/
