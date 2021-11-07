---
layout: page
title: Sherman Ng's Project Portfolio Page
---

### Project: ProgrammerError

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) (PE) is a desktop application which helps CS2100 lab
tutors manage their students’ lab results in a simple and efficient manner, allowing them to spend less time on administrative
processes and more time teaching students. It is optimized for use via a Command Line Interface (CLI) while still
having the benefits of a Graphical User Interface (GUI) developed with Java and JavaFX.

Please find below my contributions to the project. My contributions to the project code base can also be found at the
following [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=shermannws&tabRepo=AY2122S1-CS2103-F09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

<hr/>

#### Contributions

1. **New Feature**: Added the ability to filter the list of students based on different query parameters.
   * What it does:
   Filters the list of students stored in PE by displaying all the students that matches the query parameters.
   Tutors can specify the filter criteria based on any combination of students' particulars.
   * Justification:
   So that tutors are able to quickly filter out the students' of interest out of their large number of students using known parameters (e.g. Class ID).
   * Highlights:
   This feature was challenging as it involves filtering students based on any combination of query parameters provided.
   This also involves recreating a whole new command from scratch which requires comprehensive understand of all the classes in PE.

2. **New Feature**: Added a command history feature that allows the tutor to navigate to previous commands using up/down keys.
   * What it does: Allows tutor to navigate previously executed commands, similar to the Unix CLI.
   * Justification: So that fast-typists tutors are able to be more efficient when using PE.
   * Highlights: Involves a sef-directed learning experience using online materials to properly implement an "onKeyPressed" event listener.
   Requires deep thoughts into the edge cases to ensure that this feature is free of bug.

3. **Project management**:
   * Created and set up the GitHub team organisation, repo, pages and actions
   * Managed and ensured the prompt delivery of releases [v1.3.trial](https://github.com/AY2122S1-CS2103-F09-3/tp/releases/tag/v1.3.trial) & [v1.3.1](https://github.com/AY2122S1-CS2103-F09-3/tp/releases/tag/v1.3.1) (2 releases) on GitHub

4. **Enhancements to existing features**:
   * Enhanced the `edit` feature to accommodate the changing requirements of PE (PRs [\#121](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/121), [\#387](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/387), [\#403](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/403))
   * Modified generic utility classes for the integration of the new PE requirements (PRs [\#274](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/274))
   * Increased user feedback by putting in place better exceptions throwing mechanism and more detailed description (PRs [\#272](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/272), [\#287](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/287), [\#373](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/373), [\#387](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/387), [\#403](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/403))
   * Increased code quality (PR [\#206](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/206))
   * Various bugfixes (PRs [\#150](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/150), [\#362](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/362), [\#375](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/375))

5. **Documentation**:
   * User Guide:
     * Added documentation for the command history navigation feature and for features `filter` and `edit` [\#35](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/35), [\#148](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/148), [\#160](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/160), [\#210](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/210), [\#385](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/385)
     * Created the command summary at the bottom of the User Guide [\#35](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/35)
     * Solved bug that prevented User Guide and Developer Guide from being linked as one of the header page in our `index.md` page [\#276](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/276)
   * Developer Guide:
     * Added implementation details of the `filter` feature. [\#162](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/162)

6. **Community**:
   * PRs with non-trivial comments: [\#113](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/113), [\#121](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/121), [\#160](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/160), [\#211](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/211), [\#389](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/389)
   * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/348), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/324))
   * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/shermannws/ped/issues/7), [2](https://github.com/shermannws/ped/issues/17), [3](https://github.com/shermannws/ped/issues/5))
