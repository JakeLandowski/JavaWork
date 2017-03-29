![alt text](https://raw.githubusercontent.com/JakeLandowski/JavaWork/master/219Final/TetrisCloneGif.gif "Tetris Clone")

This was made for my final project in one of my intro java classes. The requirements was to simply make some sort of game. It didn't have to be anything amazing, but I wanted a challenge so I decided to take on Tetris and really start practicing utilizing multiple classes to create a solid program. I ended up creating a very basic tetris clone, although it is nothing amazing in terms of design, it is a working concept. This was during a quarter where I was taking 5 classes and I only had 1 week to accomplish this, with that in mind I'm rather happy with what I made.

In this class we were using an old graphics library called ACM. Although I use it in this program, it is largely unnecessary as the only rendering I'm doing it turning cell blocks different colors to represent their state. 

Here is a quick breakdown of the set up of my program:

1. Main program runs the game loop and creates an instance of the Grid class.

2. The Grid object maintains most of the game state, and divides itself into cells which are instances of the Cell Class.

3. The generic Shape class contains all of the methods and fields which are inherited by each of the specific shape classes.

4. Each unique shape class contains a constant 4D int array of its shape coordinates, based on cells.

5. Game loop calls the fall method which primarily drives the game, causing the current shape to progress down. It also passes        whether or not down is being held, if so it just hastens the fall.

6. Shapes are drawn starting from their "origin" cell position, and uses the shape map to determine which following cells to also      fill in. 

7. One of the dimensions of the 4D shape map is different rotations. This is used to both check and draw the new shape when            rotating. This seemed both easier and faster than trying to write some complicated rotation algorithm. 

8. When a shape touches the bottom of the screen or a previously frozen block, it will freeze after sitting still for roughly 3        movement loops, resetting when moved or rotated in order to allow a grace period to shimmy it into place.

9. Each time a fresh shape is frozen into place, the grid checks each row for a full line of frozen cells, if so it deletes that      row and moves the previous rows of frozen cells down.


Since I'm still new to this, I don't know how much of what I've done is incorrect, but it functions, and it was definitely fun as hell to make.
