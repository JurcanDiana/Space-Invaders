# Space-Invaders

&nbsp; The implementation of the nostalgic game of Space Invaders as a Desktop Application, 
done in Java Swing. 

&nbsp; It is a fixed shooter game in which the player moves 
rocket horizontally across the bottom of the screen and fires at aliens
overhead. The aliens begin as five rows of ten that move left and right as 
a group, shifting downward each time they reach a screen edge. 

&nbsp; The goal is to eliminate all of the aliens by shooting them. While the 
player has three lives, the game ends immediately if the invaders reach 
the bottom of the screen. The aliens attempt to destroy the player by 
firing projectiles. The rocket is partially protected by stationary 
defensive obstacles.

The initial look of the game:

![image](https://user-images.githubusercontent.com/86732510/223726064-db9fa3f9-5c68-4612-896e-22858d6d0420.png)

List of features:
- The player controls a rocket that can move left/right, which will be 
placed at all times at the bottom of the screen. Fire projectiles can be 
sent upward by pressing space.
- Aliens begin as five rows of ten that move uniformly left and right as 
a group, shifting downward each time they reach a screen edge.
- The aliens, also, fire projectiles at random intervals of time.
- Both the projectile and alien are destroyed, when projectiles hit 
aliens.
- The projectile is destroyed, when projectiles hit obstacles.
- The player loses a life, when projectiles hit the player.
- The game ends under three conditions. \
&ensp; 1. The user loses if the player runs out of lives. \
&ensp; 2. The user loses if the aliens reach the bottom of the screen 
and touch the player.\
&ensp; 3. The user wins if all aliens are destroyed.
