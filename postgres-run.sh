docker run --name slerp_postgres -p 2070:5432/tcp -e POSTGRES_MULTIPLE_DATABASES=oauth,slerp -e POSTGRES_USER=kiditz -e POSTGRES_PASSWORD=rioters7 -d slerpio/postgres
