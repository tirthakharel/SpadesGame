=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: gnarayan
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections - I used collections to appropriately model state. My Deck class was a map of Card objects to files in
  order to properly get the image file with the correct card. My Hand class was an ArrayList in order to properly add and
  remove cards.

  2. File I/O - I used File I/O in order to keep track of names and scores. I read and wrote to two different files in
  order to track the names and scores.

  3. Inheritance - I used inheritance in order to make proper JFrame classes and JPanel classes.

  4. Testing - In order to test my game, I wrote some JUnit tests to test necessary functions.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Game:
  The Game class is my main class where the whole program runs. This is also where I set my panels
  The GameArea class is where my actual game happens.
  The Card class is where I keep my Card object.
  The Deck class is where I store my Deck object - this is important as it keeps my files and card objects together.
  The Hand class is where I create Hands for the 2 players. It also has checks for the amount of cards left.  
  
- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  There were many blocks, one of the main ones was how to implement the functionality of my cards. I finally accomplished
  this by creating the Card class which was an extension of JButton.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I don't think my game is properly encapsulated as there are a lot of "crosscontamination" of types and objects between
  classes.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  N/A