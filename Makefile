ifeq ($(OS),Windows_NT)
	RM := if exist "TRABALHO/target/classes/TRABALHO" (rmdir /s /q "TRABALHO/target/classes/TRABALHO")
else
	RM := rm -rf 'TRABALHO/target/classes/TRABALHO'
endif

default: test

run:
	mvn -f TRABALHO/pom.xml clean compile exec:java

test:
	mvn -f TRABALHO/pom.xml clean test

jar:
	mvn -f TRABALHO/pom.xml clean compile assembly:single

run-jar:
	java -jar $(wildcard ./TRABALHO/target/TRABALHO*.jar)

jrj: jar run-jar

install-maven-dependencies:
	mvn -f TRABALHO/pom.xml clean install

install-java-and-maven-windows:
	choco install -y openjdk --version=11.0.1 --allow-downgrade
	choco install -y maven --version=3.9.5 --allow-downgrade

patch:
	mvn -f TRABALHO/pom.xml \
	build-helper:parse-version \
	versions:set -DnewVersion=\
	\$${parsedVersion.majorVersion}.\
	\$${parsedVersion.minorVersion}.\
	\$${parsedVersion.nextIncrementalVersion} \
	-DgenerateBackupPoms=false

minor:
	mvn -f TRABALHO/pom.xml \
	build-helper:parse-version \
	versions:set -DnewVersion=\
	\$${parsedVersion.majorVersion}.\
	\$${parsedVersion.nextMinorVersion}.\
	0 \
	-DgenerateBackupPoms=false
