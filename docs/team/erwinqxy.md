---
layout: page
title: Erwin Quek's Project Portfolio Page
---
### Project: ProgrammerError

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) is a desktop application which helps CS2100 tutors manage
their students’ lab results in a simple and efficient manner, allowing them to spend less time on administrative
processes and more time teaching students.

The following is a summary of my contributions to the
project. ([View Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17))


### Features  

1. **New Feature**: Automatic sorting of the student list.
   * What it does: it helps to ensure that the student list is always sorted whenever an add or command is executed. It first sorts the students based on their class ID than by name.
   * Justification: This feature improves the product significantly as it helps to simulate a real-life nominal roll where the students will always be sorted by class and name, allowing the user to have an organized set of data to work with.
   * Highlights: This enhancement provides the user with a more convenient and accessible experience in finding his / her students.
2. **Improved Feature**: Added Student ID, Class ID, and Email
   * What it does: it provides important information to identify the student by.
   * Justification: This feature improves the product by providing vital important to identify the student.
   * Highlights: This feature will be used in other commands such as add, edit, and filter. This feature was tedious as it required an overhaul of the existing AB3 features and test cases. This feature also overlaps with the sample data.
3. **Improved Feature**: Added a new set of Sample Data.
   * What it does: it helps to populate ProgrammerError with some students.
   * Justification: This feature improves the testability of the product where it allows first-time users to explore and learn the various features provided by the product.
4. **New Feature**: Implemented a feature to help generate random lab scores.
   * What it does: it helps to populate the students in the sample data with randomized lab scores.
   * Justification This feature improves the testability of the product, providing, randomized lab scores to the user.
   * Highlight: This feature requires getting the total lab score of a particular lab as an upper bound for the randomization to work so that it does not generate an invalid score.
5. **Improved Feature**: Implement unique email.
   * What it does: it ensures that only unique email inputs are valid when executing add or edit student commands.
   * Justification: simulating a real-world constraint, there will never be two students with the same email address. It also helps to ensure the filter command results are consistent and accurate.
   * Highlight: Initially I thought that this will be a straightforward implementation. However, after implementing this feature, I discovered a few bugs which I will explain below.
6. **Improved Feature**: Implement error messages for duplicate email and student id.
   * What it does: it helps inform the user what is the error with his input command, duplicate student id, or a duplicate email. This will help provide the user with a better understanding of the error.
   * Justification: previously, we only implemented a duplicate student error which is vague and does not provide the user with enough feedback to correct his command input.
   * Highlight: After enforcing this feature and together with feature 5, there was a bug where the user is forced to input a unique email and student id. But users were now unable to edit the existing student id or email. Hence, it required some troubleshooting.
7. **Minor Fix**: Helped to refactor and update the entire project file to match our product name.


### Other Contributions
1. **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=erwinqxy&tabRepo=AY2122S1-CS2103-F09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

2. **Project management**:
   * Managed releases `v1.2a, v1.4` (2 releases) on GitHub

3. **Enhancements to existing features**:
      * Refactor the entire project structure to fit our product [#61](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/61), [#91](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/91), [#99](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/99), [#382](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/382)
      * Fix Parser Test Cases [#68](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/68)
      * Implement automatic sorting [#161](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/161), [#192](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/192)
      * Populate sample data [#169](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/169)
      * Fix cosmetic error message [#324](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/324) [#328](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/328)
      * Fix duplicate email [#427](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/427)

4. **Reviews** 
      * PRs reviewed:
        * [#98](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/98), [#100](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/100), [#108](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/108),
        [#109](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/109), [#110](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/110), [#152](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/152),
        [#153](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/153), [#252](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/252), [#413](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/413), 
        [#417](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/417), [#460](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/460/files)

5. **Documentation**:
   * Update documentation user guide, developer's guide [#165](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/165), [#240](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/240), [#246](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/246), 
      [#474](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/474), [#482](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/482)

6. **Community Contributions**: 
   * Provide some feedback and bug reporting to another team during PED: [#1](https://github.com/erwinqxy/ped/issues/1),
      [#2](https://github.com/erwinqxy/ped/issues/2), [#3](https://github.com/erwinqxy/ped/issues/3),
      [#4](https://github.com/erwinqxy/ped/issues/4), [#5](https://github.com/erwinqxy/ped/issues/5),
      [#6](https://github.com/erwinqxy/ped/issues/6), [#7](https://github.com/erwinqxy/ped/issues/7),
      [#8](https://github.com/erwinqxy/ped/issues/8), [#9](https://github.com/erwinqxy/ped/issues/9),
      [#10](https://github.com/erwinqxy/ped/issues/10), [#11](https://github.com/erwinqxy/ped/issues/11),
      [#12](https://github.com/erwinqxy/ped/issues/12), [#13](https://github.com/erwinqxy/ped/issues/13),
      [#14](https://github.com/erwinqxy/ped/issues/13)
