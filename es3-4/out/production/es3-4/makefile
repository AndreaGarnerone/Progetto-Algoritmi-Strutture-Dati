JUNIT=../resources/junit-4.13.2.jar
HAMCREST=../resources/hamcrest-core-1.3.jar
CLASSPATH=.:$(JUNIT):$(HAMCREST)

# Default target
all: all_pq all_g all_prim

# Targets for PriorityQueue project
all_pq: compile_priorityqueue run_priorityqueue

compile_priorityqueue: priorityqueue/PriorityQueue.class priorityqueue/PriorityQueueTestMain.class

priorityqueue/PriorityQueue.class: priorityqueue/PriorityQueue.java
	javac priorityqueue/PriorityQueue.java

priorityqueue/PriorityQueueTestMain.class: priorityqueue/PriorityQueueTestMain.java
	javac -cp $(CLASSPATH) priorityqueue/PriorityQueueTestMain.java

run_priorityqueue:
	java -cp $(CLASSPATH) priorityqueue/PriorityQueueTestMain

clean_priorityqueue:
	rm -f priorityqueue/*.class

# Targets for Graph project
all_g: compile_graph run_graph

compile_graph: graph/Graph.class graph/GraphTestRunner.class

graph/Graph.class: graph/Graph.java
	javac graph/Graph.java

graph/GraphTestRunner.class: graph/GraphTestRunner.java
	javac -cp $(CLASSPATH) graph/GraphTestRunner.java

run_graph:
	java -cp $(CLASSPATH) graph/GraphTestRunner

clean_graph:
	rm -f graph/*.class

# Target for Prim program
all_prim: compile_prim run_prim

compile_prim: graph/Prim.class

graph/Prim.class: graph/Prim.java
	javac graph/Prim.java

run_prim:
	java graph.Prim "../italian_dist_graph.csv"

clean_prim:
	rm -f graph/Prim.class

# Clean up all
clean: clean_priorityqueue clean_graph clean_prim

.PHONY: all all_pq all_g all_prim compile_priorityqueue compile_graph compile_prim run_priorityqueue run_graph run_prim clean clean_priorityqueue clean_graph clean_prim

