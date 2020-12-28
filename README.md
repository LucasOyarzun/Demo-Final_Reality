Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.


Assumptions
-----------
How we don't have implemented a Main() method yet, I haven't used Quiet Testing, because otherwise,
wouldn't be occasion to watch the announcements of the controller.

-----------

This project has two principal packages: Character and Weapons, with their respective tests.
First, the Character package has the model of Enemies and Player characters. Starting by an interface called
ICharacter, and continuing with an AbstracCharacter class. Here are defined the principal variables of characters,
their constructor and basic methods to get their names, character class and to wait their turn on the battles.

The Enemy Class' principal difference with the Player Characters, is that the enemy has a weight, meanwhile 
player character's weight is decided by their weapons equipped.
PlayerCharacter class has 5 sub-classes that are the 5 types of character allowed to create: BlackMage, WhiteMage,
Knight, Engineer and Thief. Each has its respective constructor and equip(weapon) method.
BlackMages and WhiteMages has a new variable called mana.

Weapon Package has an interface IWeapon with an AbstractWeapon class. That define the basic methods to get
variables of the weapon and Override of equals and hashCode to has a way to decide if a weapon equals to another.
This class has 5 sub-classes, that are the 5 types of weapons in the game: Axe, Bow, Knife, Staff, Sword.
Each has its respective constructor, and Staff has a new wariable called magicDamage, that is included when a
staff is inicialized.

The Player Characters can equip weapons and can attack opponents according to their classes, when a character's lifePoints
are 0, the character is dead and is removed from the battle.

Player and Computer class are classes that keeps the behavior of the Player and the COM during the battles, keep the
character list or enemy list and control the character's information and attacks.

GameController class is the controller of the game it can create and assign Objects of the model(PlayerCharacters, Enemies
and Weapons), it knows who are the Player's characters and Computer's characters, and their information.
Controller can handle Player's inventory adding and removing weapons from it.
Controller can equip a weapon to a PlayerCharacter and order a character to attack an opponent.

Besides, the controller knows when stars and finish a character's turn, and knows immediately if a character die and
if the Player won or lost the battle.

Finally were implementes Phases to the game battles, Main Phase (To check Allies, Enemies and change weapons), Attack Phase
(To select who we will attack) and End Phase (To wait until the next character is ready to battle).

To show the game, was created a "view" (for MVC pattern) with JavaFX, where the "view" will call controller's methods to show
to the player every information he/she needs to play. Even it includes a scene of Game Over, with a message that depends on the
game result. 

To initialize the game, it's necessary to Run FinalReality.java, and a window will appear, the user can play using buttons on the 
screen, with them, it's possible to change weapons, start Attack Phase and select an enemy to attack. If it's the turn of an enemy
it will attack to a player character randomly. Every event on the game is announced by a Label on the left of the window.

