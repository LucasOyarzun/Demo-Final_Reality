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

---

This project has two principal packages: Character and Weapons, with their respective tests.
First, the Character package has the model of Enemies and Player characters. Starting by an interface called
ICharacter, and continuing with an AbstracCharacter class. Here are defined the principal variables of characters,
their constructor and basic methods to get their names, character class and to wait their turn on the battles.

The Enemy Class' principal difference with the Player Characters, is that the enemy has a weight, meanwhile 
player character's weight is decided by their weapons equipped.
PlayerCharacter class has 5 sub-classes that are the 5 types of character allowed to create: BlackMage, WhiteMage,
Knight, Engineer and Thief. Each has its respective constructor and equip(weapon) method.
BlackMages and WhiteMages has a new variable called mana, and both of them implements an interface IMage, where
will be the methods related to cast spells.

Weapon Package has an interface IWeapon with an AbstractWeapon class. That define the basic methods to get
variables of the weapon and Override of equals and hashCode to has a way to decide if a weapon equals to another.
This class has 5 sub-classes, that are the 5 types of weapons in the game: Axe, Bow, Knife, Staff, Sword.
Each has its respective constructor, and Staff has a new wariable called magicDamage, that is included when a
staff is inicialized.
