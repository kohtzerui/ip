# Nimbus User Guide

Nimbus is a **personal task management chatbot** that helps you keep track of your todos, deadlines, and events via a simple command-line interface.

---

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a todo: `todo`](#adding-a-todo-todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an event: `event`](#adding-an-event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have **Java 17** or above installed.
2. Download the latest `Nimbus.jar` from the [releases page](https://github.com/kohtzerui/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Nimbus.
4. Open a terminal, `cd` into the folder, and run:
   ```
   java -jar Nimbus.jar
   ```
5. Type commands and press Enter to interact with Nimbus. Refer to the [Features](#features) section below for details on each command.

---

## Features

> **Notes about the command format:**
> - Words in `UPPER_CASE` are parameters to be supplied by you.
> - Parameters must be provided in the order shown.
> - Extraneous parameters for commands that do not take in parameters (e.g. `list`, `bye`) will result in an error.

### Adding a todo: `todo`

Adds a todo task (a task with no date attached).

Format: `todo DESCRIPTION`

Example:
```
todo read book
```
Expected output:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding a deadline: `deadline`

Adds a task with a due date.

Format: `deadline DESCRIPTION /by DATE`

- `DATE` can be in ISO-8601 format (e.g. `2025-12-31`) for formatted display, or free-form text (e.g. `Sunday`).

Example:
```
deadline return book /by 2025-06-15
```
Expected output:
```
Got it. I've added this task:
  [D][ ] return book (by: Jun 15 2025)
Now you have 2 tasks in the list.
```

### Adding an event: `event`

Adds a task with a start and end date/time.

Format: `event DESCRIPTION /from START /to END`

- `START` and `END` can be in ISO-8601 format or free-form text.

Example:
```
event project meeting /from 2025-08-06 /to 2025-08-07
```
Expected output:
```
Got it. I've added this task:
  [E][ ] project meeting (from: Aug 6 2025 to: Aug 7 2025)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Displays all tasks in your task list.

Format: `list`

Example output:
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Jun 15 2025)
3.[E][ ] project meeting (from: Aug 6 2025 to: Aug 7 2025)
```

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark INDEX`

- `INDEX` is the task number shown in the `list` output.

Example:
```
mark 1
```
Expected output:
```
Nice! I've marked this task as done:
  [T][X] read book
```

### Unmarking a task: `unmark`

Marks the specified task as not done.

Format: `unmark INDEX`

Example:
```
unmark 1
```
Expected output:
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### Deleting a task: `delete`

Removes the specified task from the list.

Format: `delete INDEX`

Example:
```
delete 3
```
Expected output:
```
Noted. I've removed this task:
  [E][ ] project meeting (from: Aug 6 2025 to: Aug 7 2025)
Now you have 2 tasks in the list.
```

### Finding tasks by keyword: `find`

Finds all tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

- The search is **case-sensitive**.

Example:
```
find book
```
Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Jun 15 2025)
```

### Exiting the program: `bye`

Exits the application.

Format: `bye`

### Saving the data

Task data is saved automatically to `./data/nimbus.txt` after every command that modifies the task list. There is no need to save manually.

### Editing the data file

Task data is saved as a text file at `./data/nimbus.txt`. Advanced users are welcome to update data directly by editing that file.

> **⚠️ Caution:** If your changes to the data file are not in the correct format, Nimbus may not be able to load the file and will start with an empty task list.

---

## FAQ

**Q: How do I transfer my data to another computer?**

A: Copy the `data/nimbus.txt` file from your current Nimbus home folder to the same location on the other computer.

**Q: Can I use dates in formats other than ISO-8601?**

A: Yes. If a date string is not in `yyyy-MM-dd` format, Nimbus stores and displays it as-is (e.g. `Sunday`, `next week`).

---

## Command Summary

| Action       | Format                                       |
|--------------|----------------------------------------------|
| **Todo**     | `todo DESCRIPTION`                           |
| **Deadline** | `deadline DESCRIPTION /by DATE`              |
| **Event**    | `event DESCRIPTION /from START /to END`      |
| **List**     | `list`                                       |
| **Mark**     | `mark INDEX`                                 |
| **Unmark**   | `unmark INDEX`                               |
| **Delete**   | `delete INDEX`                               |
| **Find**     | `find KEYWORD`                               |
| **Exit**     | `bye`                                        |