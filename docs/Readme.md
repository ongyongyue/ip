🏝️ Holiday User Guide

“Plan today, relax tomorrow.”

Holiday is a desktop task manager designed to help you organise tasks quickly and efficiently. Built with Java, it uses a clean chat-style interface so you can manage tasks without navigating complicated menus.

🚀 Quick Start
1️⃣ Prerequisites

Install Java 17 or later.

2️⃣ Download

Download the latest holiday.jar from the Releases page.

3️⃣ Run the App
``` 
java -jar holiday.jar
```

4️⃣ Start Managing Tasks

Type a command in the chat box.

Press Send (or Enter).

Your tasks are saved automatically.

📁 Tasks are stored in:

data/holiday.txt

📋 Features

✅ Add todo, deadline, and event tasks               
✅ Mark tasks as done or undone                                  
✅ Delete tasks  
✅ Search tasks by keyword   
✅ Sort tasks by date    
✅ Duplicate task detection  
✅ Auto-save to local storage    
✅ Persistent across sessions

💬 Commands
```
Note: Commands are case-insensitive. Extra spaces are ignored.
Possible commands are
todo: Add a todo task
deadline: Add a task with a deadline date and time
event: Add a task with a start and end date time
list: List out current tasks in our list
mark: Mark the indexed task as done
unmark: Unmark the indexed task as not done
delete: Deletes the indexed task from the list
find: Find a task by keyword
bye: Closes the application
```
➕ Add a Todo

A task without a date.

Format
```
todo <description>
```
Example
```
todo pack luggage
```
Response
```
Got it. I've added this task:
[T][ ] pack luggage
Now you have 1 task(s) in the list.
```
⏰ Add a Deadline

A task due by a specific date and time.

Format
```
deadline <description> /by <yyyy-MM-dd HHmm>
```
Example
```
deadline book flights /by 2025-06-01 2359
```
⚠️ Date must be valid.

📅 Add an Event

A task occurring over a time period.

Format
```
event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
```
Example
```
event beach day /from 2025-06-10 0900 /to 2025-06-10 1800
```
⚠️ Start time must be before end time.

📄 List All Tasks
list
✔ Mark a Task as Done
mark <task number>

Example
```
mark 1
```
↩ Unmark a Task
```
unmark <task number>
```
❌ Delete a Task
```
delete <task number>
```
🔍 Find Tasks by Keyword

Searches descriptions.
```
find <keyword>
```
Example
```
find beach
```
👋 Exit
bye

Holiday will save your tasks and close.

⚠️ Error Handling

Holiday provides clear messages when errors occur.

📁 Data Storage

Tasks are saved automatically to:
```
data/holiday.txt
```
Manual saving is not required.

If the file is missing, Holiday creates a new one.

⚠️ Avoid editing the file manually to prevent corruption.

Known Limitations

Recurring tasks are not supported yet.

GUI appearance may vary slightly across operating systems.