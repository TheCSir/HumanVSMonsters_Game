# Humans vs Monsters

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
Since the tiles are hexagons, there are six movements allowed:

1-	North

2-	North-West

3-	North-East

4-	South

5-	South-West

6-	South-East

Movement will depend on rolling a dice. Minimum move is 1 and maximum moves are 6. For Example if a player decides to move and rolls a 4, then the player will be able to move 4 tiles e.g. (North, North, North-West, South-West).
Players cannot move outside of walls and blocked paths.

## Player Structure:

Each player will have these main attributes:

Hearts: Hearts resemble the Hit Points. Each player has 8 Hearts at the beginning of each game.

Attack: Defines the amount of damage caused to opponent.

Defense: Defense is the chance to either reduce or completely avoid damage taken by opponent.

Speed: Each player has a different attack speed and affects which player causes damage first in each turn.

Strength: The opponent type that you cause the most damage to.

Weakness: The opponent type that you receive the most damage from.

### Humans

**1-	Archer**

Attack: Decreases 1 Heart.

Defense: 10% chance in blocking all damage. 90% chance in blocking half damage.

Speed: Max speed is 8. Min speed is up to 6.

Effective against: Sky Monsters (Griffin).

Weak against: Water Monsters (Medusa).

Special Skill: Long Reach, Shoot three tiles away.

**2-	Priest**

Attack: Decreases 1 Heart.

Defense: 10% chance in blocking all damage. 90% chance in blocking half damage.

Speed: Max speed is 6. Min speed is 4.

Effective against Water Monsters (Medusa).

Weak against: Ground Monsters (Minotaur).

Special Skill: Healing Lights, Heal up to three hearts.

**3-	Warrior**

Attack: Decreases 1 heart.

Defense: 10% chance in blocking all damage. 90% chance in blocking half damage.

Speed: Max speed is 6. Min speed is 4.

Effective against: Ground Monsters (Minotaur).

Weak against: Sky Monsters (Griffin).

Special skill: Heavy Smash, causes double HP damage.

### Monsters:

**1-	Minotaur**

Attack: Decreases 1 heart.

Defense: 10% chance in blocking all damage. 90% chance in blocking half damage.

Speed: Max speed is 6. Min speed is 4.

Effective against: Priests.

Weak against: Warriors.

Special skill: Summoning Bulls, each Bull has one heart of health.

**2-	Medusa**

Attack: Decreases 1 heart.

Defense: 10% chance in blocking all damage. 90% chance in blocking half damage.

Speed: Max speed is 7. Min speed is 5.

Effective against: Archers.

Weak against: Priests.

Special skill: Summoning Sneaks, each Sneak has two hearts of health. Sneaks cannot attack but they poison Humans which lasts for three turns., affecting the HP by 1 point each turn 

**3-	Griffin**

Attack: Decreases 1 heart.

Defense: 10% chance in blocking all damage. 90% chance in blocking half damage.

Speed: Max speed is 8. Min speed is 6.

Effective against: Warriors.

Weak against: Archers.

Special skill: Summoning Hawks, each Hawk has two hearts, and cannot attack but they decrease the Human speed by 1 speed point each turn.

Each turn, the players will choose from one of three actions:

1-	Attack

2-	Defend

3-	Move

4-	Swap Player

