# JongBot User Guide

JongBot is a simple command-line task manager for organizing your todos, deadlines, and events. All tasks are automatically saved!

---

## Quick Start

1. Ensure Java 11+ is installed
2. Download `jongbot.jar`
3. Run: `java -jar jongbot.jar`
4. Start typing commands!

---

## Commands

### `help` - Show available commands
```
help
```

### `todo DESCRIPTION` - Add a simple task
```
todo buy groceries
todo read chapter 5
```

### `deadline DESCRIPTION /by DATE` - Add task with deadline
```
deadline homework /by Friday 5pm
deadline return book /by next Monday
```
⚠️ Must include `/by` keyword

### `event DESCRIPTION /from START /to END` - Add event with time period
```
event meeting /from 2pm /to 4pm
event conference /from Monday /to Wednesday
```
⚠️ Must include both `/from` and `/to` keywords

### `list` - View all tasks
```
list
```
Shows tasks with status: `[ ]` = not done, `[X]` = done

### `mark INDEX` - Mark task as done
```
mark 1
mark 3
```

### `unmark INDEX` - Mark task as not done
```
unmark 2
```

### `delete INDEX` - Delete a task permanently
```
delete 4
```

### `find KEYWORD` - Search tasks by keyword
```
find book
find meeting
```
Searches whole words only (case-insensitive)

### `bye` - Exit the application
```
bye
```

---

## Quick Reference

| Command | Format | Example |
|---------|--------|---------|
| Help | `help` | `help` |
| Todo | `todo DESCRIPTION` | `todo buy milk` |
| Deadline | `deadline DESC /by DATE` | `deadline essay /by Friday` |
| Event | `event DESC /from START /to END` | `event party /from 8pm /to 11pm` |
| List | `list` | `list` |
| Mark | `mark INDEX` | `mark 1` |
| Unmark | `unmark INDEX` | `unmark 2` |
| Delete | `delete INDEX` | `delete 3` |
| Find | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |

---

## Task Types

- **`[T]`** - Todo: Simple task
- **`[D]`** - Deadline: Task with due date
- **`[E]`** - Event: Task with time period

---

## Notes

✅ Tasks are auto-saved to `data/tasks.txt`  
✅ Use any date format (e.g., "tomorrow", "2026-03-15", "next Friday")  
✅ Multi-word descriptions are supported  
✅ Task indices start from 1  
⚠️ Don't manually edit the data file

---

## Common Errors

- **Empty description?** Include a description for all tasks
- **Missing `/by`, `/from`, or `/to`?** Check your deadline/event format
- **Invalid index?** Use `list` to see valid task numbers
- **Unknown command?** Type `help` for available commands