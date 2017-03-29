![alt text](https://raw.githubusercontent.com/JakeLandowski/JavaWork/master/AsteroidsGame/AsteroidGif.gif "Asteroids")
[Pardon the choppy gif capture]

Created for one of my intro java class assignments. The original project involved just animating some random asteroids to move linearly across the screen. I found this uninteresting and was inspired to just create the actual "game," or atleast a working concept of it. Keep in mind, the code is pretty sloppy since I only have a day to make this, and spent most of it doing the math.

I initially ran into a wall of not knowing how to create multi-directional fluid movement, but knew how to create basic velocity. My next step was to figure out how to get the angle/slope from points at different angles as well as the distance, so I just googled til I found the Pythagoream Theorem and distance formulas and eventually made it work to get the ship and asteroids to move at both controlled and random directions and velocities. I ended up learning in the end that this is what vectors are lol. 

Obviously I am not a math major. Still, it was a fun challenge. The only real flaw is a bug I could never figure out which is the code that randomizes spawn points for asteroids outside of the game window only causes them to spawn from the right side of the screen, although it is the same code that works in my star screensaver program. It seems to work depending on how close they spawn to the window border, my theory is that the other borders don't have valid support beyond the screen but who knows. 
