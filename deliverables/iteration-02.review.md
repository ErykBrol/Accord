# Accord

## Iteration 02 - Review & Retrospect

 * When: November 17th @ 5pm
 * Where: BA3008 -> BA3195

## Process - Reflection

Below is a recap of the process decisions made during the planning phase of this iteration:

 * 1 Week Sprints, starting and ending on Tuesdays
 * Trello to track sprint details. 
 * Slack to communicate, integration with Github to track changes/pull requests.
 * Discord for voice chat during online meetings

 * Meetings online every Tuesday and in-person meetings every Friday

 * GitHub process
    * 3 Main branches (prod, mobile, server)
    * Branch when working on a feature, pull requests and code reviews to merge

#### Decisions that turned out well

List process-related (i.e. team organization) decisions that, in retrospect, turned out to be successful.

 * #### GitHub process
    * Introducing 3 separate branches allowed the mobile team and server team to work coherently with the least chance of introducing merge conflicts between the 2 teams.
    * Branching from the main dev branches prevented code conflicts within a team
    
    ![x](https://i.imgur.com/eGSiXHQ.png)
    
    * Our pull request system forced code to be tested/reviewed before being merged into the main dev branch, which lowered the chance of spaghetti code making it to production.
    
    ![x](https://i.imgur.com/0iqBBPl.png)
    
    ![x](https://i.imgur.com/3Ic4OAd.png)
    
    * It was easy to organize and revert commits as features were contained within their own branches. 
    * Travis CI: Allowed us to test our implementations on pull requests, through our unit tests (Back-end), making sure that the build passes.

 * #### Slack
    * A lot more organized than Facebook chat, since we can have a text channel for each topic/team.
    
    ![x](https://i.imgur.com/yP3oOWm.png)
    
    * Integration with GitHub so that it’s easy and convenient to stay updated with the project
    * Notifications when new commits and pull requests to the group repo are made
    
    ![x](https://i.imgur.com/EJTwsZA.png)
    
    * Useful features such as code blocks and pinned messages

 * #### Trello
    * Gave us an easy place to plan and check what needed to be done for the current sprint.
    
    ![x](https://i.imgur.com/0hJd2Pm.png)
    ![x](https://i.imgur.com/IdWTHxi.png)

 * #### Discord
    * Voice chat allowed for more productive online meetings compared to text only meetings, with faster communication.
    

#### Decisions that did not turn out as well as we hoped

 * #### 1 Week Sprints/Sprint Scheduling
    * We followed the schedule for the first few weeks, but with the pressure of midterms/assignments, our schedule fell apart. The week before reading week we got minimal work done. Most of the work was left to last sprint as a result of this.
* We planned to have our final sprint of the iteration finished at the end of reading week, so that we would have the following week to work on the video exclusively, but we didn’t finish all of our planned cards until Nov. 16th.
    
 * #### Trello 
    * We didn’t update the board as often as we had hoped, so it became confusing sometimes who was working on what, what wasn’t implemented yet, and what was already completed.

#### Planned changes

 * We need to update Trello more frequently, so that group members can stay updated. (In progress, In PR, has largely been ignored for this iteration).
    * Every time someone starts on a card, they move it to ‘In Progress’ and comment on it on Trello, so we know who is working on it.
    * Every time someone makes a pull request, they move the corresponding card to ‘In PR’ on Trello.
    * Every time a pull request is approved and merged, the person merging moves the card to ‘Done’.

## Product - Review

#### Goals and/or tasks that were met/completed:

 * A working implementation of our mockup screens, fully connected to the backend with the exception of the suggestions feature.
 * Mobile team created the home screen, room creation screen, loading screens, idea submission screen (along with chatting capabilities), and results screen.
 * Server team setup endpoints for room creation, room joining, chatting capabilities, and voting submission for mobile team to connect to.
 * Server team implemented sending data back to connected clients. (Vote results from other users, room info, etc.).

 * Extra tasks completed:
    * Server team implemented the tournament system that is used to determine results of the voting phase, and also sends back the results to clients at the end of the voting phase.

#### Goals and/or tasks that were planned but not met/completed:

 * We completed all our planned goals for this iteration, however the client interface could use some polishing. (There are minor rendering bugs).

## Meeting Highlights

Going into the next iteration, our main insights are:

 * Our main focus in this iteration was to ship our MVP with little emphasis on design. We have come to realize that designing an intuitive and friendly user-interface requires a lot of effort (i.e. research, creativity, execution). Going forward, we are going to dedicate a greater amount of engineering effort towards introducing more intuitive user-interfaces.

 * Since we have successfully shipped our MVP (base features that solve our proposed problem), we are going to focus on introducing new features that aim to improve user-experience and further guide users on their decision-making journey. Planned features include but not limited to: suggestions based on room topics, helpful tips for winning results (i.e. nearest restaurant location), subtle improvements to our mobile application to improve user-experience.

* Since we were pushing to release our MVP by the deadline, we sacrificed code quality in some parts of our application. Going forward, we are going to refactor areas of our code that can be improved. In addition, the platform team will write additional tests to increase our code coverage to catch bugs that may be introduced in future code changes.

## Project Video

Here's the link to our project video: https://youtu.be/9jL5oMEzUbU
