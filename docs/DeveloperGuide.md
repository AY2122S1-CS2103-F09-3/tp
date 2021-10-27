---
layout: page title: Developer Guide
---

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
  original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes
called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**
API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `ProgrammerErrorParser` class to parse the user
   command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
   executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `ProgrammerErrorParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ProgrammerErrorParser` returns back
  as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**
API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list
  which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be
  bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**
API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in json format, and read them back into corresponding
  objects.
* inherits from both `ProgrammerErrorStorage` and `UserPrefStorage`, which means it can be treated as either one (if
  only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.programmer.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`
* ores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `purge`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

### Student List Filtering

#### Proposed Implementation

The proposed filter mechanism is facilitated by the `QueryStudentDescriptor` and the
`StudentDetailContainsQueryPredicate`. The `StudentDetailContainsQueryPredicate` extends `Predicate<Student>` and
contains a private field of type
`QueryStudentDescriptor` that will be used to test if a given student to the predicate matches all the query fields in
the `QueryStudentDescriptor`.

* `StudentDetailContainsQueryPredicate#test(Student)` — Evaluates the predicate on the given `Student` argument.
* `QueryStudentDescriptor#doesStudentMatchDescriptor(Student)` — Evaluates if the `QueryStudentDescriptor` fields
  matches with the corresponding fields of the `Student` argument.

These operations are exposed in the `Model` interface as `Model#updateFilteredStudentList(Predicate<Student>)`.

Given below is an example usage scenario and how the list filtering mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user executes `filter -cid B01` to display all the students whose Class ID matches `B01`.

The following UML sequence diagram shows how the filter command works.

![FilterSequenceDiagram](images/FilterSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `StudentDetailContainsPredicate`
, `QueryStudentDescriptor` and `FilterCommand` should end at the destroy marker (X) but due to a limitation of PlantUML,
the lifeline reaches the end of diagram.>

</div>

The following UML activity diagram summarizes what happens when a user executes a new filter command.

#### Design Consideration

**Aspect: How filter command executes**

* **Alternative 1 (current choice):** Filter commands can take in any combination of query parameters (`-n`, `-cid`
  , `-sid`)
    * Pros: Allow for flexibility in the way users want to filter the list.
    * Cons: More difficult to implement and proper handling of the combinations of query parameters is needed.

* **Alternative 2:** A different type of filter command to filter by each of the student's attribute.
    * Pros: Implementation does not need to consider the combination of query parameters.
    * Cons: Multiple commands may be required to filter by more than one query dimension.

**Aspect: How to handle combination of query parameters**

* **Alternative 1 (current choice):** Design a `QueryStudentDescriptor` class that abstracts the handling of the input
  query parameters.
    * Pros: Need not explicitly handle the different argument combinations at the higher-level abstractions
      (e.g. `FilterCommandParser` class). Code is more maintainable.
    * Cons: Was more difficult to implement.

* **Alternative 2:** Handle the different argument combinations in the `FilterCommandParser` class.
    * Pros: Easier to handle empty argument cases with explicit conditional checking.
    * Cons: Bad use of abstraction, SLAP violated and long nested conditional statements. Code made harder for future
      extension.

**Aspect: Naming the function**

* **Alternative 1 (current choice):** Calling it `filter`.
    * Pros: `filter` is an intuitive command word for the expected functionality.
    * Cons: From the user experience perspective, it is slightly longer than type than other alternative.

* **Alternative 2:** Calling it `view`.
    * Pros: It is shorter to type and less refactoring required from the code that this project morphed from.
    * Cons: `view` is not as intuitive as other alternatives.

* **Alternative 3 (future consideration):** Providing a shortcut command for `filter`.
    * Pros: Allows for users to type less for the same expected behaviour.
    * Cons: New users may be confused with such shortcut commands.

### Show Lab Results Feature

#### Implementation

The show lab results feature allows the user to view the lab result list of a particular student. Its implementation
introduces the following classes:

* `ShowCommand`that extends `Command`
* `ShowCommandParser` that implements `Parser<ShowCommand>`
* `ShowCommandResult` that extends `CommandResult`

The syntax of this command is `show <INDEX_ON_LIST>`. For instance,`show 1` asks ProgrammerError to display the lab
results of student at index 1 of the current list.

Given below is a possible usage scenario:

[Pre-Condition] There are 2 students in ProgrammerError, and the user has created some lab results for each of them.

Step 1. The user key in the command `show 1`: The information of the student at index 1 as well as his/her lab results
are displayed on the side panel.

The mechanism is as described below:

* Upon detecting 'show' as the command word. `ProgrammerErrorParser` will create a `ShowCommandParser` with the input
  index.


* `ShowCommandParser` parses the index and creates a `ShowCommand`, which finds the student to be shown according to the
  index and creates a `ShowCommandResult` with the student identified.


* `MainWindow` receives the `ShowCommandResult` and displays the information and lab results of the identifed student.

Step 2. The user key in `show 2`: The side panel is updated with the information and lab results of the student at index
2

Step 3. The user key in `show 3`: ProgrammerError will show an error message in the `resultDisplay`, warning the user
that the index is invalid. This is triggered by `CommandException`, which is thrown by `ShowCommand`.

The following sequence diagram shows how the show command works:

![ShowSequenceDiagram](images/ShowSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![ShowActivityDiagram](images/ShowActivityDiagram.png)

#### Design considerations:

**Aspect: How Show Lab Results executes:**

* **Alternative 1 (current choice):** Each student object keeps track of its own lab results by an ObservableList.
    * Pros: Easy to implement; Lower chance of having mismatched student and lab records.
    * Cons: Have to pass a `Student` instance across different classes; May have performance issue if more attributes
      are added for `Student`

* **Alternative 2:** An ObservableList of lab results of every student in ProgrammerError itself.
    * Pros: Potential improvement in performance by passing an index, instead of a `Student` instance, across different
      classes.
    * Cons: Hard to implement, as we have to ensure the ObservableList of lab results and students have matching index
      (ie `Student` instance at index 1 of student list has its lab results at index 1 of lab results list), given that
      other operations such as add and delete can change the indexes easily.

### Download Data Feature

The download data feature allows the user to download student data as a CSV file in a directory location of their
choice.

#### Implementation

The implementation details of this feature can be found mainly in `MainWindow` as most of the necessary operations are
related to the UI. In addition, the following classes are utilised:

- `DownloadCommand`: for generating the `DownloadCommandResult`
- `DownloadCommandResult`: for displaying the feedback to the user
- `MainWindow.fxml`: for the addition of a 'Download' button on the MainWindow
- `Styles.css`: for the customisation of styles for pop-up messages

The following sequence diagram shows how the `download` command works:

![DownloadSequenceDiagram](images/DownloadSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes the download command:

![DownloadActivityDiagram](images/DownloadActivityDiagram.png)

#### Design Considerations

One of the main considerations was to deal with reading and writing files only when necessary. This meant checking if
there is any data to begin with. Only if there exists any data will the user be prompted to select a folder destination.

Additionally, a pop-up message was chosen to be displayed for two reasons. First, it provides the user a clear visual
indicator of the result of their command, as compared to the typical textual output they would see. Second, we would
only know if the data was successfully downloaded after the textual response is shown to the user. Using a pop-up
message right at the end of this operation means we can change the message depending on whether the download was a
success.

### Alternatives

1. One alternative could be to not use a third-party package (`org.json`), and instead manually parse the json file and
   write the corresponding values to a CSV file which ProgrammerError would create.

We chose not to go down this route as it is much more tedious with little reward in terms of code management and code
quality.

2. Another alternative with respect to the user experience could be to disallow the user from selecting a folder to save
   their data to. Instead, a default location could be chosen so as to save the user some time in getting their data
   downloaded quickly.

However, since we wanted to make ProgrammerError more flexible and adaptable to different users, we opted to include the
functionality of allowing the user to select a folder destination.

_{more aspects and alternatives to be added}_

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

CS2100 TAs who

* have to manage a number of students across different classes
* keep track of the students' attendance
* keep track of the students' emails
* prefer and comfortable with CLI tools
* can type fast
* are proficient with Unix commands
* prefer typing to mouse interactions

**Value proposition**:

CS2100 TAs who use ProgrammerError enjoys greater productivity and efficiency when managing his/her classes of students.
<<<<<<< HEAD

=======
> > > > > > > master

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                                               | So that I can…​
| -------- | ------------------------------------------ | ------------------------------------------------------------------------------------------------ | ----------------------------------------------------------------------
| `* * *`  | potential user exploring the app           | see the app populated with sample data| easily see how the app will look like when it is in use.
| `* * *`  | user ready to start using the app          | purge all current data         |  get rid of data in the app.
| `* * *`  | CS2100 TA                                  | be able to create records of individual students: (Name, Student ID, Class ID, email)| so that I can identify and track their progress separately.
| `* * *`  | CS2100 TA                                  | be able to sort the class records| have an organized class record.
| `* * *`  | CS2100 TA                                  | delete the details of a student| clear the information of students who have dropped out of the class.
| `* * *`  | new user                                   | use the in-build help feature  | learn how to use the app quickly.
| `* * *`  | CS2100 TA                                  | be able to view (read) the records of individual students| know more about the student's current performance and email.
| `* * *`  | CS2100 TA                                  | be able to update the details of a student| correct any mistakes that I have made.
| `* * *`  | CS2100 TA                                  | be able to save the data in a CSV file| upload to LumiNUS and share with the CS2100 Instructors.
| `* * *`  | proficient programmer / TA                 | navigate ProgrammerError seamlessly with the use of Unix command| efficiently manage my class.
| `* * `  | CS2100 TA with multiple devices             | export the data in my ProgrammerError                                                            | import it on another device.
| `* * `  | busy CS2100 TA                              | view students' statstics formatted in a standard form                                            | avoid sorting the information on my own.
| `* * `  |  CS2100 TA                                  | use the app to track students' performance                                                       | identify those who need more help.
| `* * `  | user                                        | know that the software and data will be available 99.999 percent of the time I try to access it  | don't get frustrated and find another software to use.
| `* *`   | CS2100 TA who is an undergradudate myself   | spend little time updating ProgrammerError                                                       | have have sufficient time for my other commitments.
| `* *`   | CS2100 TA with overwhelming work            | be greeted with a nice interface                                                                 | enjoy the process of doing admin tasks.
| `* *`   | CS2100 TA                                   | update attendance to keep track of participation email%                                          | I can fulfil my obligations as a TA.
| `* *`   | CS2100 TA                                   | edit a student's participation score                                                             | the records are up to date.
| `* *`   | CS2100 TA                                   | upload the students' performance to LumiNUS conveniently                                         |
| `* *`   | impatient CS2100 TA with overwhelming work  | be greeted with a nice interface                                                                 | quickly retrieve a student's particular/email from the database.
| `* *`   | CS2100 TA who loves using the keyboard      | type commands                                                                                    | practice my typing skills.
| `* *`   | CS2100 TA                                   | generate weekly feedbacks via email for my students with ease                                    |
| `* *`   | CS2100 admin                                | have a dashboard to have a bird eye view of my class statistics                                  | be updated quickly on my class progress.
| `* *`   | CS2100 TA                                   | use tags to identify the topics which many students have difficulty in                           | prepare more notes on the topic.
| `*`     | CS2100 TA with multiple classes             | filter the contact list by classes  | easily identify those in the current class.                |                                                                        
| `*`     | archive previous batch statistics           | compare current batch performance with them                                                      |
| `*`     | expert user                                 | archive/hide unused data                                                                         | avoid being distracted by irrelevant data.
| `*`     | CS2100 TA                                   | use tags to mark the weak areas of a student                                                     | provide more targetted help to my students.
| `*`     | user                                        | add a time to a task                                                                             | record when a task need to be done.
| `*`     | user                                        | see the pending task that has the next earliest deadline                                         | know what I need to do next.
| `*`     | a student                                   | easily view my participation statistics                                                          | verify that it was recorded correctly.
| `*`     | a TA with many students and classes         | store vital information of my students                                                           | query it when the need arises.
| `*`     | CS2100 Teaching Staff                       | easily search and update student's contact details                                               | I can reach them throughout the module.

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ProgrammerError` and the **Actor** is the
`CS2100 Teaching Assistant (CS2100 TA in short)`, unless specified otherwise)

**Use case: UC1 Purge/Delete all sample student records**

Precondition: CS2100 TA opens ProgrammerError for the first time

**MSS**

1. CS2100 TA requests to list student records
2. ProgrammerError shows a list of sample student records
3. CS2100 TA requests to purge sample student records
4. ProgrammerError deletes all sample student records

   Use case ends.

**Use case: UC2 Create a student record**

**MSS**

1. CS2100 TA requests to list student records
2. ProgrammerError shows a list of student records
3. CS2100 TA requests to create a student record
4. ProgrammerError requests for the student's details
5. CS2100 TA specifies the student's details
6. ProgrammerError creates a student record

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 5a. The student details given are incomplete.

    * 5a1. ProgrammerError shows an error message.

      Use case resumes at step 2.

**Use case: UC3 View a student record**

**MSS**

1. CS2100 TA requests to list student records
2. ProgrammerError shows a list of student records
3. CS2100 TA requests to view a specific student record
4. ProgrammerError shows the student record's details

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ProgrammerError shows an error message.

      Use case resumes at step 2.

**Use case: UC4 Delete a student record**

**MSS**

1. CS2100 TA requests to list student records
2. ProgrammerError shows a list of student records
3. CS2100 TA requests to delete a specific student record in the list
4. ProgrammerError deletes the student record

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ProgrammerError shows an error message.

      Use case resumes at step 2.

**Use case: UC5 Update a student record**

**MSS**

1. CS2100 TA requests to list student records
2. ProgrammerError shows a list of student records
3. CS2100 TA requests to update a specific student record
4. ProgrammerError updates the student record

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ProgrammerError shows an error message.

      Use case resumes at step 2.

**Use case: UC6 Automatic sort student records**

**MSS**

1. CS2100 TA creates (UC2) / views (UC3) / delete (UC4) / update (UC5) a student record
2. CS2100 TA requests to list student records
3. ProgrammerError automatically sort the changed student records

   Use case ends.

**Use case: UC7 Automatic save and load student records**

**MSS**

1. CS2100 TA creates (UC2) / views (UC3) / delete (UC4) / update (UC5) a student record
2. ProgrammerError automatically save the changed student records to hard disk
3. CS2100 TA restarts the application
4. ProgrammerError shows the saved data

   Use case ends.

{More to be added}

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. A TA with above average typing speed for code should be able to accomplish most of the tasks faster using commands
   than using the mouse.
3. The data should be stored locally and should be in a human editable csv file.
4. The product should be for a single user at a time (i.e. not a multi-user product).
5. No DBMS should be used to store data.
6. The software should follow the Object-oriented paradigm primarily. (with the possibility of allowing a mix of other
   styles when justifiable).
7. The final product should be a result of evolving the given code base. (i.e. allowed to replace all existing code with
   new code, as long as it is done incrementally)
8. The GUI should work well (i.e., should not cause any resolution-related inconveniences to the user) for standard
   screen resolutions 1920x1080 and higher, and screen scales 100% and 125%.
9. the GUI should be usable (i.e., all functions can be used even if the user experience is not optimal) for resolutions
   1280x720 and higher, and screen scales 150%.

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Student ID**: An NUS student's matriculation number
- **TA**: A CS2100 teaching assistant

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

    1. Prerequisites: List all students using the `list` command. Multiple students in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Download data

1. Select folder from directory chooser window to save data to:

    1. To cancel, click 'cancel' to return to the main window.
    2. In the chosen folder, ProgrammerError will save a CSV file of the students' data named `programmerError.csv`.

### [Proposed] Dashboard

1. A dashboard to view the TA's classes lab results.  
