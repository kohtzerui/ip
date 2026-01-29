# Nimbus User Guide

Nimbus is a personal assistant chat bot that helps you keep track of tasks.

## Features

### Echo
Nimbus repeats what you say (Level-1).

### Task List
Nimbus stores your tasks and allows you to list them (Level-2).

#### Adding a Task
Simply type the task description to add it to your list.
Example:
```
read book
```
Expected Output:
```
 added: read book
```

#### Listing Tasks
Use the `list` command to see all your stored tasks.
Example:
```
list
```
Expected Output:
```
 1. read book
 2. return book
```

## Usage

1. Compile: `javac -d bin src/main/java/Nimbus.java`
2. Run: `java -cp bin Nimbus`
3. Enter commands as described in Features.
4. Type `bye` to exit.