# Humans vs Monsters

# How to run the game

We have created a .jar file to run our game.

## Game Structure:

Humans vs Monsters is a battle to the death turn based board game. The aim of the game is for the two players to meet up and try to kill all opponent players. Each turn, each player can choose one certain action in order to try to defeat the opponent.
The game ends upon these four conditions:

1-	Human players kill all the Monster players.

2-	Monster players kill all the Human players.

3-	After a certain number of turns, the team with the highest Health Points win.

4-	After a certain number of turns, if the both teams have same amount of Health points then the game ends in a draw.

## Map Structure:

The map is dungeon based made from hexagon tiles. A player exists on a tile. And each tile cannot contain more than one player.
The map will be made from different rooms connected by passages where the players search for each other and battle. The players are spawned randomly in the map at the beginning of each game.
The size of the map can be modified in between the size of 10x10 to 15x15.
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

Each player will have these main attributes:

Hearts: Hearts resemble the Hit Points. Each player has 10 Hearts at the beginning of each game.

Attack: Decrease the opponent health by attacking the opponent piece.

Defense: Defense is to reduce the incoming damage.

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

Each turn, the players will choose from one of three actions:

1-  Move

2-	Attack

3-  Special Ability

4-	Defend

5-	Swap 
