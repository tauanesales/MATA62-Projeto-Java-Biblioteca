ifeq ($(OS),Windows_NT)
	RM := if exist ".\out" (rmdir /s /q ".\out")
else
	RM := rm -rf '.\out'
endif

default: clean compile run

run:
	java -cp ./out/ Console/Programa

compile:
	javac -cp ./src/ ./src/Console/*.java -d "./out"

clean:
	$(RM)

install-java-windows:
	choco install -y openjdk
