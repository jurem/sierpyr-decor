
all: sierpyr


sierpyr:
	mkdir -p bin
	javac -d bin -classpath src src/sierpyr/Decor.java


@PHONY: clean


clean:
	rm -rf bin
