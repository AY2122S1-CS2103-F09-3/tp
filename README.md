[![Java CI](https://github.com/AY2122S1-CS2103-F09-3/tp/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/AY2122S1-CS2103-F09-3/tp/actions)
[![codecov](https://codecov.io/gh/AY2122S1-CS2103-F09-3/tp/branch/master/graph/badge.svg?token=KLKGJOEN9F)](https://codecov.io/gh/AY2122S1-CS2103-F09-3/tp)

![Ui](docs/images/Ui.png)

* ProgrammerError (P|E in short) is an application developed based on the `AddressBook Level 3` application.
* This is an application made for CS2100 Teaching Assistants (TAs) to track their students' task progress and performance

## List of Features 

1. View Sample Data (shown when starting the application)
2. Purge Sample Data (Delete all)
    - Purge Existing Sample Data
    - `purge`
3. In-build help feature
    - `help`
4. Unix Command Syntax
    - allow user to use flags to execute operations
    - e.g. `add -n <NAME> -sid <STUDENT_ID>`
5. Create records of individual students (ADD)
    - Name, Student ID, Class ID, NULL grade as a placeholder for default
    - `add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID>`
6. View the records of individual students (READ)
    - Find a student using Student ID
    - `view -sid <STUDENT_ID>`
7. Update a student's record (UPDATE)
    - Update a student's grade
    - `update -sid <STUDENT_ID>`
8. Delete a student's record  (DELETE)
    - Delete the entry of the entire student
    - `delete -sid <STUDENT_ID>`
9. Automatically Sorted Class Records
    - Sort in the backend when inputting data
    - Sorting key: Student ID
10. Save data to Hard Disk
    - Save whenever there is a change (create/edit/delete)

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
