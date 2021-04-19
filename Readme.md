# 8-Puzzle

8-Puzzle is a JAVA program for solving a give state of the 8 puzzle to reach the goal state (2D array) as mentioned below which can be represented as a 3 x 3 matrix. 

```Board.java```   implements an immutable data type ```Board```.
```Solver.java``` implements an A* search, BFS search, and DFS search to solve 3-by-3 slider puzzles by creating an immutable data type ```Solver```. 

The output is the cheapest cost which is calculated by the number of each block moved to get to the goal state. For example if block with ```2``` is moved then the ```cost += 2``` and so on.

Goal : ```[1, 2, 3, 8, 0, 4, 7, 6, 5]```

Example:
* Given State:  
``` 
    [1, 3, 4]
    [8, 0, 2]
    [7, 6, 5]
```
* Goal State: 
``` 
    [1, 2, 3]
    [8, 0, 4]
    [7, 6, 5]
```
* Cheapest Cost: 30

* The program is implemented in 3 different ways : A* algorithm, BFS, DFS.
    * A* algorithm uses a heuristic function i.e. manhattan distance to find the best possible path.
    ![a_star](https://www.cs.princeton.edu/courses/archive/fall16/cos226/assignments/8puzzle-game-tree.png)

## Installation

* Extract the 8PUZ solution folder from the zip file @8PUZ
* Save the folder at the desired location on your computer.
* Open Visual Studio Code or IntelliJ IDEA (recommended).
    * IntelliJ IDEA has a free license available for students: https://www.jetbrains.com/community/education/#classrooms

### Visual Studio Code:

* Open new window and click on - Open Folder.
* Select the extracted folder 8PUZ and it should open in viscose.
* Now, you can manually install the extension pack from the Extensions view (⇧⌘X) by typing vscode-java-pack in the search box.
* After java-pack is installed, you can run it in many ways:
    * Left click on Solver.java in 'src' folder and select 'Run' command.
    * Also, in Solver.java you can see a shortcut link to 'Run' the program just above the main function.
    * Another way is to go to debug option on left pane of vscode and select 'Run and Debug'.

### IntelliJ IDEA:

* Open IntelliJ IDEA and select 'Open Project'.
* You can find the 'Run' option on the 'File Menu Bar'.
* Select 'Run Solver' option from the 'Run' menu.

## Usage

* The program reads file stored in the project folder 8PUZ.
* To run test-case-files, please include your SampleFile#.txt in the 8PUZ folder.
* By default 3 test cases are already provided namely SampleFile1.txt, SampleFile2.txt, SampleFile3.txt
* Can add more test files but only one test case per file is permitted as per the guidelines found in the project description.
* Please present your test case in the form of 3 x 3 matrix in the .txt file where blocks on each row are separated by space.
* Thus, to finally run a new test file, go to the first line inside main function in Solver.java and replace file name in the line File file = new File("yourFileName.txt").

