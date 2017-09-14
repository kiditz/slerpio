docker service create --name postgres -p 2070:5432 -e POSTGRES_USER=kiditz -e POSTGRES_PASSWORD=rioters7 -e POSTGRES_MULTIPLE_DATABASES=oauth,slerpio localhost:5000/postgres
