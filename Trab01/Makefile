CC := g++
CFLAGS := -Wall -std=c++11
SRCFILES := $(wildcard src/*.cpp)
MAIN := $(wildcard bin/main.cpp)

all: $(SRCFILES:src/%.cpp=obj/%.o)
	$(CC) $(CFLAGS) obj/*.o -o bin/main

obj/%.o: src/%.cpp
	$(CC) $(CFLAGS) -c $< -o $@ -I./inc

.PHONY: clean
clean:
	rm -rf obj/*.o
	rm -rf bin/*

run:
	bin/main
