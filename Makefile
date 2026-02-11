PROJECT_DIR=app/lab1

test:
	cd $(PROJECT_DIR) && mvn -q test

verify:
	cd $(PROJECT_DIR) && mvn -q verify
