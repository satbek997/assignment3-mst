#  Assignment 3 – Minimum Spanning Tree (MST)

**Course:** Design and Analysis of Algorithms  
**Student:** Satbek   
**Group:** SE-2435  

---

##  Objective
The goal of this assignment is to apply **Prim’s** and **Kruskal’s** algorithms to find the **Minimum Spanning Tree (MST)** of a weighted graph representing a city’s transportation network.  
Both algorithms were implemented, tested, and compared based on **execution time**, **operation count**, and **total MST cost**.

---

##  Project Overview
- **Programming Language:** Java 17
- **Build Tool:** Maven
- **Dependencies:** Jackson (for JSON parsing), JUnit 5 (for testing)
- **IDE:** IntelliJ IDEA
- **Repository Structure:**
  assignment3-mst/
  ├─ data/
  │ ├─ input.json # Input datasets
  │ ├─ output.json # Generated MST results
  │ └─ mst_results.csv # Summary table
  │
  ├─ src/
  │ ├─ main/java/mst/ # Algorithm implementation
  │ │ ├─ Edge.java
  │ │ ├─ Graph.java
  │ │ ├─ PrimMST.java
  │ │ ├─ KruskalMST.java
  │ │ ├─ DisjointSet.java
  │ │ └─ Main.java
  │ │
  │ └─ test/java/mst/ # Unit testing (JUnit)
  │ └─ MSTTest.java
  │
  └─ pom.xml # Maven configuration

markdown
Копировать код

---

##  Algorithm Design

###  Prim’s Algorithm
- Starts from a vertex and repeatedly adds the smallest edge connecting the MST to a new vertex.
- Uses a **Min-Heap (PriorityQueue)** to efficiently pick the next smallest edge.
- Best suited for **dense graphs**.
- **Time complexity:** O(E log V)

###  Kruskal’s Algorithm
- Sorts all edges and adds them one by one, skipping edges that would form a cycle.
- Uses a **Disjoint Set (Union–Find)** data structure for cycle detection.
- Works best for **sparse graphs**.
- **Time complexity:** O(E log E)

---

##  Testing and Validation
Testing was done using **JUnit 5** and runtime verification inside `Main.java`.

**Test Cases:**
1. Both algorithms return the same MST total cost 
2. Each MST contains exactly `V − 1` edges 
3. No cycles are formed 
4. Execution time ≥ 0 

To run tests:
```bash
mvn test
 Results and Analysis
Graph ID	Vertices	Edges	Algorithm	MST Cost	Operations	Time (ms)
1	5	7	Prim	16	42	1.52
1	5	7	Kruskal	16	37	1.28
2	4	5	Prim	6	29	0.87
2	4	5	Kruskal	6	31	0.92

 MST costs are identical for both algorithms — confirming correctness.
️ Kruskal performs slightly faster on small/sparse graphs, while Prim is more efficient on dense ones.

 Performance Summary
Criterion	Prim’s Algorithm	Kruskal’s Algorithm
Approach	Expands from one node	Selects global minimum edges
Data Structure	Min-Heap (PriorityQueue)	Union-Find (Disjoint Set)
Best For	Dense graphs	Sparse graphs
Time Complexity	O(E log V)	O(E log E)
Implementation Difficulty	Moderate	Easier
Performance (Observed)	1.52 ms	1.28 ms

 Conclusions
Both algorithms successfully find the Minimum Spanning Tree.

Kruskal’s algorithm was slightly faster for the given datasets.

Prim’s algorithm would perform better for larger, dense networks.

The results confirm theoretical efficiency differences.

 Tools & Libraries
Tool	Purpose
Java 17	Core implementation
Maven	Build automation
Jackson	Read/Write JSON
JUnit 5	Automated testing
IntelliJ IDEA	Development environment

 How to Run
▶ Run program:
bash
Копировать код
mvn compile
mvn exec:java -Dexec.mainClass="mst.Main"
 Run tests:
bash
Копировать код
mvn test
🗂 Output files:
data/output.json — JSON summary of MST results

data/mst_results.csv — Comparison table (for Excel/graphs)

 Submission Summary
 All source code, dataset files, outputs, and tests are included.
 Full analysis and explanation are provided in this README.
 Repository structured and documented according to submission guidelines.

Submission Link:
 https://github.com/satbek997/assignment3-mst

References
Cormen, T. H. Introduction to Algorithms, MIT Press.

GeeksforGeeks, “Prim’s vs Kruskal’s Algorithm”.

Course materials and lab manual (Assignment 3 – MST Optimization).


##  Bonus Section – Graph Design in Java (10%)

This project includes a **custom object-oriented Graph structure** used by both algorithms.

###  Implemented Classes
- **Graph.java** — stores vertices, edges, and adjacency list  
- **Edge.java** — defines a weighted undirected edge  

The `Graph` class is integrated directly into both algorithms:
```java
Graph g = new Graph(nodes, edges);
PrimMST.PrimResult primRes = prim.runPrim(g);
KruskalMST.KruskalResult krusRes = krus.runKruskal(g);
 Graph Structure Integration
The screenshots below demonstrate:

The Graph class design (object-oriented implementation).

Successful MST computation using the custom Graph structure.

Graph.java Structure	MST Program Output
