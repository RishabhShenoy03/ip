# JongBot User Guide

JongBot is a command-line task management application that helps you organize your todos, deadlines, and events. It features persistent storage, allowing your tasks to be saved and loaded automatically.

---

## Quick Start

1. Ensure you have Java 11 or above installed on your computer
2. Download the latest `jongbot.jar` from the releases page
3. Open a command terminal and navigate to the folder containing the jar file
4. Run the application with: `java -jar jongbot.jar`
5. Type commands and press Enter to execute them
6. Refer to the [Features](#features) section below for available commands

---

## Features

### Viewing Help: `help`

Displays a list of all available commands and their usage.

**Format:** `help`

**Example:**
```
help
```

**Expected Output:**
```
Use "todo", "deadline", "event" to add a task to the list
Use "list" to see all tasks
Use "mark" / "unmark" / "delete" followed by the task index to do... exactly that
Use "find <word> to find tasks containing that word
```

---

### Adding a Todo: `todo`

Adds a simple todo task to your task list.

**Format:** `todo DESCRIPTION`

**Parameters:**
- `DESCRIPTION`: The description of the todo task (cannot be empty)

**Example:**
```
todo buy groceries
todo read textbook chapter 5
```

**Expected Output:**
```
Adding this task to the list:
[T][ ] buy groceries
Number of tasks in list: 1
```

**Notes:**
- Todo tasks are displayed with the `[T]` tag
- The `[ ]` indicates the task is not done yet
- Trying to add a todo without a description will result in an error

---

### Adding a Deadline: `deadline`

Adds a task with a deadline to your task list.

**Format:** `deadline DESCRIPTION /by DEADLINE`

**Parameters:**
- `DESCRIPTION`: The description of the deadline task (cannot be empty)
- `DEADLINE`: The deadline by which the task should be completed (free-form text)

**Example:**
```
deadline CS2113 assignment /by Friday 5pm
deadline library book return /by next Monday
deadline submit report /by 2026-03-15
```

**Expected Output:**
```
Adding this task to the list:
[D][ ] CS2113 assignment (by: Friday 5pm)
Number of tasks in list: 1
```

**Notes:**
- Deadline tasks are displayed with the `[D]` tag
- The `/by` keyword is **required** and separates the description from the deadline
- The deadline can be in any format (natural language or dates)

---

### Adding an Event: `event`

Adds an event with a time period to your task list.

**Format:** `event DESCRIPTION /from START /to END`

**Parameters:**
- `DESCRIPTION`: The description of the event (cannot be empty)
- `START`: The start time/date of the event (free-form text)
- `END`: The end time/date of the event (free-form text)

**Example:**
```
event team meeting /from 2pm /to 4pm
event project presentation /from Monday 10am /to 11am
event conference /from 2026-03-20 /to 2026-03-22
```

**Expected Output:**
```
Adding this task to the list:
[E][ ] team meeting (from: 2pm to: 4pm)
Number of tasks in list: 1
```

**Notes:**
- Event tasks are displayed with the `[E]` tag
- Both `/from` and `/to` keywords are **required**
- The time/date can be in any format

---

### Listing All Tasks: `list`

Displays all tasks currently in your task list.

**Format:** `list`

**Example:**
```
list
```

**Expected Output:**
```
Here's your list:
1:[T][ ] buy groceries
2:[T][X] read textbook chapter 5
3:[D][ ] CS2113 assignment (by: Friday 5pm)
4:[E][ ] team meeting (from: 2pm to: 4pm)
```

**Notes:**
- Tasks are numbered starting from 1
- `[ ]` indicates an incomplete task
- `[X]` indicates a completed task
- If the list is empty, you'll see an error message: "List is empty. Insert some stuff bro :P"

---

### Marking a Task as Done: `mark`

Marks a task as completed.

**Format:** `mark INDEX`

**Parameters:**
- `INDEX`: The index number of the task to mark (as shown in the list)

**Example:**
```
mark 1
mark 3
```

**Expected Output:**
```
Task 1 has been marked
```

**Notes:**
- The task's status will change from `[ ]` to `[X]`
- Index must be a valid number within the range of your task list
- Invalid indices will show an error: "Invalid task index. use "list" to see all tasks"

---

### Unmarking a Task: `unmark`

Marks a previously completed task as incomplete.

**Format:** `unmark INDEX`

**Parameters:**
- `INDEX`: The index number of the task to unmark (as shown in the list)

**Example:**
```
unmark 2
```

**Expected Output:**
```
Task 2 has been unmarked
```

**Notes:**
- The task's status will change from `[X]` to `[ ]`
- Works the same as `mark` but in reverse

---

### Deleting a Task: `delete`

Permanently removes a task from your task list.

**Format:** `delete INDEX`

**Parameters:**
- `INDEX`: The index number of the task to delete (as shown in the list)

**Example:**
```
delete 3
```

**Expected Output:**
```
Task 3 has been deleted
```

**Notes:**
- Once deleted, the task cannot be recovered
- All subsequent tasks will shift down in the index
- Invalid indices will show an error message

---

### Finding Tasks: `find`

Searches for tasks that contain a specific keyword in their description.

**Format:** `find KEYWORD`

**Parameters:**
- `KEYWORD`: The word to search for (case-insensitive, whole word matching)

**Example:**
```
find book
find meeting
find homework
```

**Expected Output:**
```
Here are the matching tasks:
1:[T][ ] read textbook chapter 5
2:[D][ ] library book return (by: next Monday)
```

**Notes:**
- Search is **case-insensitive** ("book" will match "Book" or "BOOK")
- Only matches **whole words** (e.g., "team" won't match "teamwork")
- If no matches are found, an empty list is displayed
- The numbering shows the position in the filtered results, not the original list

---

### Exiting the Program: `bye`

Exits the JongBot application.

**Format:** `bye`

**Example:**
```
bye
```

**Expected Output:**
```
Bye bye! See you soon!
```

**Notes:**
- All tasks are automatically saved before exiting
- Your tasks will be loaded again when you restart the application

---

## Data Storage

- Tasks are automatically saved to `data/tasks.txt` after every operation
- The data file is created automatically if it doesn't exist
- Tasks are loaded automatically when you start the application
- **Do not manually edit the data file** as it may cause data corruption

---

## Error Handling

JongBot provides helpful error messages for common mistakes:

| Error Situation | Error Message |
|----------------|---------------|
| Empty todo description | `Todo description cannot be empty. Try "todo homework"` |
| Empty deadline description | `Deadline description cannot be empty. Try "deadline homework /by 8pm"` |
| Missing `/by` in deadline | `Please include /by for any deadlines!! For example: "deadline homework /by tomorrow"` |
| Empty event description | `Event description cannot be empty. Try "event homework /from 10am /to 1pm"` |
| Missing `/from` or `/to` in event | `Please include /from and /to for any events!! For example: "event homework /from 10am /to 1pm"` |
| Invalid task index | `Invalid task index. use "list" to see all tasks` |
| Empty list | `List is empty. Insert some stuff bro :P` |
| Unknown command | `I don't understand what you mean, please try again. Insert with "todo" / "deadline" / "event"` |

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Help | `help` | `help` |
| Add Todo | `todo DESCRIPTION` | `todo buy groceries` |
| Add Deadline | `deadline DESCRIPTION /by DEADLINE` | `deadline homework /by Friday` |
| Add Event | `event DESCRIPTION /from START /to END` | `event meeting /from 2pm /to 4pm` |
| List | `list` | `list` |
| Mark | `mark INDEX` | `mark 1` |
| Unmark | `unmark INDEX` | `unmark 2` |
| Delete | `delete INDEX` | `delete 3` |
| Find | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |

---

## FAQ

**Q: Can I use multi-word descriptions for tasks?**  
A: Yes! Everything after the command keyword (and before special keywords like `/by`, `/from`, `/to`) is treated as the description.

**Q: What date formats are supported?**  
A: JongBot accepts any free-form text for dates and times. You can use natural language like "tomorrow", "next Monday", or specific dates like "2026-03-15".

**Q: Can I edit an existing task?**  
A: Currently, you need to delete the old task and add a new one with the updated information.

**Q: Where is my data stored?**  
A: Tasks are saved in `data/tasks.txt` relative to where you run the application.

**Q: Will my tasks persist after closing the application?**  
A: Yes! All tasks are automatically saved and will be loaded when you restart JongBot.

---

## Technical Details

- **Language:** Java 11+
- **Storage:** Plain text file format
- **Task Types:** Todo, Deadline, Event
- **Persistence:** Automatic save/load functionality
- **Error Handling:** Comprehensive exception handling with user-friendly messages

---

## Credits

Developed as part of the CS2113 module project.