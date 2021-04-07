# Accord

## Iteration 03

 * Start date: November 24th 2017
 * End date: December 1st 2017

## Process

We’re doing a quick 1 week sprint to get our next steps from our iteration 2 review complete for the demo. We have our MVP done at this point, so the remaining work is mostly polishing the application and adding our extra user features (suggestions, etc).

#### Changes from previous iteration

 * We plan to update Trello more frequently, to ensure that all group members stay updated on our sprint progress.
	* Every time someone starts on a card, they move it to ‘In Progress’ and comment on it on Trello so we know who is working on it.
	* Every time someone makes a pull request, they move the corresponding card to ‘In PR’ on Trello.

 * We’ve adapted our sprint cycles to match up with our deliverable due date. Iteration 3 will only be a 1 week sprint. (Starting today)
	* We will have a more cohesive sprint, as the whole sprint will focus on what we want to get done before the deliverable.
* If we stuck with sprints that start on Tuesdays, we would have 1 full sprint, and 1 short sprint before the deliverable is due, which would mess up our sprint organization.

#### Roles & responsibilities

 * Mobile (Front-end) - (Lucas, Vincent, Eryk, Rebecca)
	* Takes cares of front-end development (adding our additional features) and app polishing.
 * Server (Back-end) - (Bilal, Jesse, Yoson)
	* Takes care of the web services and communication with APIs (Google APIs, etc.) that the mobile app will be requesting.
 * Superstar Editor (Eryk)
	* Our in-house professional video editor, that makes godly demo videos.

#### Events

* Tuesday online meeting
  * Used to discuss progress made, finalize major tasks that should've been completed by then
  * Plan any small changes to be made
  * Organize video
* Friday Meetings
  * We will use the first Friday meeting to assign tasks to be worked on
  * In-person meetings used for discussing progress, doing iteration plan and iteration review
  * Talk about previous sprint (what got done, what didn’t get done, blockers, ...), release to github organization repo, assign tasks for next sprint
* End of semester team bonding
  * We will use our final product to choose where to go for dinner (Popeyes seems quite likely)
  * Discuss monetization and release to App Store/Google Play
  * Selling our company to Facebook/Google/Amazon
  * Browsing available office spaces
  * Finalizing merchandise deal/launching our merch store
  * Plan what we are going to do when we become (tri/bi/mi)llionaires

#### Artifacts

* Trello to keep track of tasks
	* As we planned in our last iteration, we will be updating Trello more frequently
	* We will have cards for each thing we plan to implement
	* Team members will mark which tasks they’re working on
* Task Assignment
	* We will assign the first few tasks to team members at the first “Friday” meeting
	* Beyond that, whatever tasks are still incomplete on the Trello board will be up-for-grabs for team members that finished their tasks
* Task Priority
	* Major features are top priority (suggestions, info cards)
  * Small bug fixes/navigation fixes are second priority
  * Least important are UI/UX changes and updates

#### Git / GitHub workflow

We feel like our GitHub process has worked pretty well for the last iteration, and we got most if not all of our planned work done. The process was also very organized, and so our GitHub process will largely stay unchanged. The one change we are adding is that our releases will only occur at the end of the iteration (not that it matters for this iteration), as it allows for a more relaxed release schedule, and we were essentially doing that in our prior iterations.

Here’s our modified GitHub process:

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
* At the end of the iteration, we merge the mobile and server branch with master, and create a pull request from the fork to the organization repo (this is what we like to call a “release”).
	* During the online tuesday meeting, all team members test the code from the master branch and approve the pull request. Once it’s approved by everyone (verbally or through Github), we release it to the organization repo.
	* As a result of this whole process, code quality is ensured as we have multiple steps where code is reviewed and guaranteed to be working. The organization repo always contains the latest working code, which is what we will use for demos and the video.




## Product

#### Goals and tasks

* Results info card
	* Top result for popular topics (i.e. food) will be displayed in informative card with link to Google Maps, and other 
* Suggestions
	* Idea submission screen will display suggestions relating to the room topic (only works for popular topics i.e. food)
* Front-end error checking
	* Preventing users from navigating screens incorrectly
	* Authenticate room codes (only allow joining existing rooms)
* UI/UX
	* Re-design the voting screen and the results screen

#### Artifacts

 * Video demo containing a run-through typical usage of our app. 
 * Screenshots of previous versions of the app to show our progress
