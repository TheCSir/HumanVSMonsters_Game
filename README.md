# Humans vs Monsters

## How to run the game:

We have created a .jar file to run our game.

## Additional functional requirement made:

1- We have the undo function done in our game where the player can undo their move up to 3 move and also a redo function to undo the undo function.

2- For the Additional combat capability, every piece will have their own special ability and can create a shield to defend for the incoming damage.

3- We have the option to change the size of our game board and also let the player to choose how many piece to have on the board.

4- We also have the option to replay all the move that have happen throughout the game.

## Game Structure:

Humans vs Monsters is a meet and fight turn based board game. The aim of the game is for the two players to control their piece on the board to meet up with the opponent's piece and try to decrease the opponent players health to 0. Each turn, each player will need to choose an action to perform in order to defeat the opponent.
The game will end upon these four conditions:

1-	Monster health bar is 0, Human win.

2-	Human health bar is 0, Monster win.

3-	After a certain number of turns, who has highest Health Points win.

4-	After a certain number of turns, same amount of Health Points draw.

## Map Structure:

The map is dungeon based made from hexagon tiles. A player exists on a tile. And each tile cannot contain more than one player.
The map will be made from different rooms connected by passages where the players search for each other and battle. The players are spawned randomly in the map at the beginning of each game.
The size of the map can be modified in between the size of 10x10 to 15x15 and the player would able to choose the how many pieces are there on the board in the setup.
Since the tiles are hexagons, there are six movements allowed:

1-	North

2-	North-West

3-	North-East

4-	South

5-	South-West

6-	South-East

Each pieces will have their own move range.
Players cannot move outside of walls and blocked paths.

## Player Structure:

#### Each player will have these main attributes:

Hearts: Hearts resemble the Hit Points. Each player has 10 Hearts at the beginning of each game.

Undo: The player would able to undo up to 3 moves once throughout the whole game.

Redo: The player would able to undo the undo function.

#### Each piece will have these main attributes:

Move: Change the piece location on the board.

Attack: Decrease the opponent health by attacking the opponent piece.

Special ability: Each piece will have their own special ability, i.e. summon a minion, long range or really heacy attack, and heal

Defense: Defense is to reduce the incoming damage.

Swap: The piece have the ability to swap to another different piece.

Minion will have the same attributes with the piece but also with its own health bar.

### Humans

**1-	Archer**

Attack: Decreases 2 Heart.

Defense: Create a shield blocking half of the incoming damage.

Special Skill: Long Range Shot, Shoot 3 tiles away.

**2-	Priest**

Attack: Decreases 2 Heart.

Defense: Create a shield blocking half of the incoming damage.

Special Skill: Health Regeneration, Heal 3 hearts.

**3-	Warrior**

Attack: Decreases 2 heart.

Defense: Create a shield blocking half of the incoming damage.

Special skill: Heavy Smash, causes double HP damage.

### Monsters:

**1-	Minotaur**

Attack: Decreases 2 heart.

Defense: Create a shield blocking half of the incoming damage.

Special skill: Summoning Bull, the minion Bull will have 2 heart of health.

**2-	Medusa**

Attack: Decreases 2 heart.

Defense: Create a shield blocking half of the incoming damage.

Special skill: Summoning Sneaks, the minion Snake will have 2 heart of health. 

**3-	Griffin**

Attack: Decreases 2 heart.

Defense: Create a shield blocking half of the incoming damage.

Special skill: Summoning Hawks, the minion Hawk will have 2 heart of health

Each turn, the players will choose from one of the actions below:

1-  Move

2-	Attack

3-  Special Ability

4-	Defend

5-	Swap 
