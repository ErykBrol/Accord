# accord

## Iteration 02

 * Start date: October 17th (Online)
 * End date: November 17th (In-Person)

## Process

 * 1 Week Sprints, starting and ending on Tuesdays
	* Allows up to split up the work nicely, and helps keep us on track
 * Trello to track sprint details. 
 * Slack to communicate, integration with Github to track changes/pull requests.
 * Discord for voice chat during online meetings (Slack voice chat is paid)
	* Easy to set up and get everyone in the channel
* Better than typing, allows us to work more efficiently

#### Roles & responsibilities

 * Mobile (Front-end)
	* Takes cares of design decisions, mockups, and front-end development.
 
 * Server (Back-end)
	* Takes care of web services that the mobile app interfaces with.

#### Events

Describe meetings (and other events) you are planning to have:

 * Meetings online every Tuesday at 8pm (over Discord voice chat).
 	* Used to review the previous sprint, merge the code into the organization repo and assign tasks for the next sprint.

 * In-person meetings every Friday at 5pm (BA3195)
 	* Meet up with team members (front-end/back-end) to discuss implementation details, blockers impeding progress in the current sprint and do some development for the current sprint.

#### Artifacts

List/describe the artifacts you will produce in order to organize your team.  	 
 * Trello board screenshots listing tasks to be completed for the current sprint/completed tasks.
	* We have labels for tasks with different priorities. (High - Medium - Low)
	* Assigning tasks is generally discussed at the online meetings with the corresponding team (front/back-end) at the beginning of the sprint.

 * Slack conversation logs and Slack github integration logs.

#### Git / GitHub workflow
* We created a fork of the organization repo so we can use Github/Travis integration with Slack.

* The fork has 3 Main Branches
	* Master branch (where we merge the mobile and server branches together each sprint)
	* Mobile branch (where all the mobile development is done)
	* Server branch (where all the server development is done)
* Whenever we want to work on a new feature, we create a new branch from mobile or server.
	* This is done to prevent conflicts as there are many developers involved with the project
* When the new feature or fix is ready, we open a pull request to merge the code with the mobile/server branch
* Whenever a pull request is opened
	* Pull request creator requests a review from at least one team member
	* Reviewer must perform a code review and approve the pull request before the pull request can be merged to the main dev branch for mobile/server
	* Reviewer typically merges the pull request after approving.
* At the end of a sprint, we merge the mobile and server branch with master, and create a pull request from the fork to the organization repo (this is what we like to call a “release”).
	* During the online tuesday meeting, all team members test the code from the master branch and approve the pull request. Once it’s approved by everyone (verbally or through Github), we release it to the organization repo.
	* As a result of this whole process, code quality is ensured as we have multiple steps where code is reviewed and guaranteed to be working. The organization repo always contains the latest working code, which is what we will use for demos and the video.

## Product

#### Goals and tasks

 * A working implementation of our mockup screens, fully connected to the backend with the exception of the suggestions feature.
	* Mobile team must create the home screen, room creation screen, loading screens, idea submission screen (along with chatting capabilities), and results screen.
	* Server team must setup endpoints for room creation, room joining, chatting capabilities, and voting submission for mobile team to connect to.
 	* Server team must also implement sending data back to connected clients. (Vote results from other users, and messages).

#### Artifacts

List/describe the artifacts you will produce in order to present your project idea.

 * Video to demonstrate the features of our product, such as creating a room, joining a room using a code, chat submission, and the voting screen, as well as the final results screen.
 * Mockup diagrams from in-person meetings. These are to reason about different screen design decisions.
 * Invision prototype.


