# Accord

#### Q1: What are you planning to build?

 * Our product is a mobile app to help people make decisions.
   The problem that we are solving is the inability to make decisions as a group in situations (i.e; deciding where to eat...) and other situations, by guiding them to one. We will also give suggestions for certain decisions, such as the nearest restaurant applicable to their decision.

The way the app will work is users join a private room with their friends and have a limited time to submit suggestions towards a chosen topic. After the timer has finished, users are no longer able to submit ideas and are required to vote on the ideas submitted by the room. After voting has finished, the app will display the most popular choices selected by the room. This app is different from a traditional poll because it’s fun and interactive which encourages people to get involved with the decision making process. It also provides a safe space as all suggestions are anonymized; that way all suggestions can be considered equally.

Below is a preliminary mock up of our app:
   
 ![Alt text](https://i.imgur.com/KXRySR7.jpg)
 
 And here is the prototype we built using InVision:
 
 [https://invis.io/5NDYDGBRC](https://invis.io/5NDYDGBRC)

#### Q2: Who are your target users?

Social groups that have a hard time coming to an agreeable decision.

Personas:

Bob and the Boys:
It’s late at night and Bob, Pete, and Jimbo are looking for a midnight snack. Every one of them has a different idea of where to eat. Bob wants to go to McDonald’s, Pete wants to go Popeyes, and Jimbo wants to go to Burger King. Although Bob wants McDonald’s, he prefers Popeyes over Burger King, and Jimbo prefers Popeyes over McDonald's. There could even be choices they didn’t think of. Our app provides a quick way to resolve this dilemma, and the boys go and get Popeyes.

Timmy and the Team:
Timmy and his team just finished baseball practice, and they want to go watch a movie. They head to the theatres but when they get there, they can’t decide what to watch. Timmy wants to watch Teletubbies but he’s afraid his friends will make fun of him for his suggestion. Everybody else also wants to watch Teletubbies, but they’re all afraid of the others judging them. Rather than watching a boring movie that no one wants to watch, using our app, they can come to a group decision to watch Teletubbies.

Team Project:
Steve and his team have trouble deciding on a specific implementation detail for their amazing new startup idea. His team is overflowing with ideas, and it’s hard to entertain  each suggestion. With our app they can alleviate this problem, and quickly come to a group decision. One idea comes up that the group really likes, and it is voted to the top. They discuss that suggestion, and if it doesn’t work out, they can look to the next most popular suggestion. In this way they can quickly work out popular ideas.

After work Dinner:
Everybody finds out they want Mexican food after the voting phase of our app, but nobody knows a good Mexican restaurant nearby. Our app makes suggestions on Mexican restaurants nearby, which is convenient, as they don’t have to switch to another app or go to a web browser to find one.

#### Q3: Why would your users choose your product? What are they using today to solve their problem/need?

Users would choose to use our product because it encourages collaboration among their peers and it enables them to come to a decision that is favoured by the majority of the group. Decision making can be a difficult task when dealing with large groups, you are often faced with the inability to have everyone contribute to the process and there are some that may feel discouraged from sharing their suggestions with the group in fear of having their idea shamed. The app provides an intuitive interface for submitting and voting on ideas, results are displayed with a simplified breakdown of popularity. The app replaces traditional/outdated polls as it provides a streamlined platform to keep track of ideas and suggestions. Through a suggestions feature on the platform, users are able to discover new information/ideas related to the topic of their room, through our suggestion system (nearby food, movies). The app guides the user to a decision in one clean package, saving users time.

----

### Highlights

#### Alternative products we were considering: 

- Livestreaming service for professors
   - The live streaming aspect of the app would be hard to implement
   - It would be difficult to demo our product
- Tinder for jobs
   - Difficult to accommodate both sides of the userbase (the applicants and the recruiters)
   - Hard to differentiate our product from the existing products on the market  
- A school/commuter related organizer app
   - We had difficulties identifying the problem we are solving with the app
   - The idea was too vague and we couldn’t come up with concrete features for the app

Key Insight: Decisions are hard to make sometimes (ex; trying to come up with our product), so we wanted to make an app that helped people make decisions.

Key Insight: When making decisions, original ideas or even regular ideas are hard to come up with so prompts/suggestions are helpful. This spurred us to add a suggestion system to our app, for topics such as food and movies, which could help guide users to a concrete decision after they vote.

Key Decision: Splitting into Front End/Design and Backend. This will allow us to separate processes like designing the GUI and implementing APIs for suggestions. This is beneficial because the multiple layers of our project would make it difficult for everyone to be well-versed in everything

Compromises/Fleshing out of our product:
   - How to guide users to a decision:
      - A tallying system, where the most frequent suggestions/ideas were the ones at the top.
         - Not very interactive, as with this system, the only part where user input is needed is the chatbox/suggestion phase.
      - A Tinder-esque system, where each user idea pops up on the screen, and you swipe left or right to rank them.
         - Doesn’t force users to make a decision (you could like/dislike everything)
      - Show two options, winner stays on system.
         - ideas that came later in the voting process a better chance of being picked, since it had less chances to get eliminated
         - Easier to make a decision with 2 choices on screen
      - Bracket system, which is the one we picked.
         - Easier to make a decision with 2 choices on screen
         - Doesn’t have the disadvantage of the winner stays on system
         - Timer at the voting phase
         - Forces users to make a decision
         - Users come to a collective decision quicker
 
