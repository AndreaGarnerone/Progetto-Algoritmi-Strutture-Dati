CC = gcc
INCLUDES = src/*.h
COMMON_DEPS = $(INCLUDES) Makefile

all: bin/edit_distance.o bin/tester.o fusion								#request of modules needed(.o)

bin/%.o: src/%.c $(COMMON_DEPS) | bin/ build/								#creation of the module placed in build
	$(CC) -c $< -o $@

fusion: bin/main.o bin/edit_distance.o 										#merge of the modules to get the exe file
	$(CC) bin/main.o bin/edit_distance.o -o ./build/main_ex2.out		

start: all ./build/main_ex2.out 											#target that run all the exercizes
	./build/main_ex2.out

unity: bin/edit_distance.o bin/unity.o bin/
	$(CC) src/tester.c src/edit_distance.c src/unity.c -o build/test

unity_run: unity
	build/test

clean:																		#target to clear all the bin and the build files in the folder
	rm -f build/* bin/*

bin/:
	mkdir -p bin

build/:
	mkdir -p build
