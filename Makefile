dk-build:
	docker-compose build

dk-upb:
	docker-compose up --build

dk-upb-recreate:
	docker-compose up --build --force-recreate

dk-down:
	docker-compose down

.PHONY: build
build:
	./gradlew build -xtest

clean:
	./gradlew clean

postgres:
	docker exec -it pollingapp_db bash
