compile:
	mvn clean compile

test:
	mvn clean test

start-docker-containers:
			docker-compose up -d
# init redis counter to 1,000,000,000 to generate 6 letter base62 result
set-redis-key-global-counter:
			docker-compose exec redis redis-cli SET url_counter 1000000000
start-containers: start-docker-containers set-redis-key-global-counter
