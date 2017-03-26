//	Jacob Landowski
//	Date: 2/15/17
//	IT 219 STORYBOOK

import acm.graphics.*;	//	ACM SHAPES
import acm.program.*;	//	ACM CONSOLE
import acm.util.*;		// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER

public class StorybookEnvironment extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	public static final int TIMER_RATE = 10;
	public static GImage cat, alien;
	public static GObject[] bubble = new GObject[5];
	
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
		//	SETUP AND START SCENE
	public void run()
	{
		setupScene();
		scene();
	}
	
		//	SET OBJECTS IN SCENE
	public void setupScene()
	{
		setBackground(new Color(210, 200, 164));
		
		GRect floor = new GRect(APPLICATION_WIDTH, 100);
		floor.setColor(new Color(222, 164, 135));
		floor.setFilled(true);
		add(floor, 0, APPLICATION_HEIGHT-100);
		
		cat = new GImage("../../cat.png");
		GImage bed = new GImage("../../bed.png");
		GImage lamp = new GImage("../../lamp.png");
		alien = new GImage("../../alien.png");
		
		cat.scale(0.5);
		bed.scale(0.18);
		lamp.scale(0.13);
		alien.scale(0.4);
		
		bed.sendToBack();

		cat.sendToFront();
		alien.sendToFront();
		
		add(cat, APPLICATION_WIDTH/2 - 60, (APPLICATION_HEIGHT-100) - cat.getHeight());
		add(bed, APPLICATION_WIDTH - bed.getWidth(), (APPLICATION_HEIGHT-100) - bed.getHeight()+22);
		add(lamp, APPLICATION_WIDTH/3.8, (APPLICATION_HEIGHT-100) - lamp.getHeight());
		add(alien, -alien.getWidth(), (APPLICATION_HEIGHT-100) - alien.getHeight());
		
	}
	
		//	RUN ALL 3 PARTS OF SCENE WITH 2 SEC PAUSE
	public void scene()
	{
		sceneBegins();
		pause(2000);
		sceneCrescendo();
		pause(2000);
		sceneEnds();
	}
	
		//	SET ZZZ TEXT BUBBLE OVER CAT
	public void sceneBegins()
	{

		double x = cat.getX();
		double y = cat.getY();
		
		setBubble("ZzzZzz");
		for(int i = 0; i < 3; i++)
		{
			add(bubble[i], x + (cat.getWidth()-5) + (i*15), y-30 - (i*30));
		}
		add(bubble[3], x + cat.getWidth()/2 + 65, y - 175);
		add(bubble[4], x+cat.getWidth()/2 + 100, y-135);
	}
	
		//	MOVE ALIEN INTO SCENE
		//	CHANGE TEXT BUBBLE TO ! ! !
	public void sceneCrescendo()
	{
		while(alien.getX() < alien.getWidth()/2)
		{
			alien.move(1,0);
			pause(10);
		}
		((GLabel) bubble[4]).setLabel("  ! ! !");
	}
	
	
		//	REMOVE TEXT BUBBLE
		//	MOVE CAT AND ALIEN RIGHT OUT OF SCENE
	public void sceneEnds()
	{
		for(int i = 0; i < 5; i++)
		{
			remove(bubble[i]);
		}
		
		while(cat.getX() < APPLICATION_WIDTH ||
			  alien.getX() < APPLICATION_WIDTH)
		{
			alien.move(7, 0);
			cat.move(5, 0);
			cat.sendToFront();
			pause(10);
		}
		
	}
	
		//	SPAWN TEXT BUBBLE TO PLACE OVER CAT
	public void setBubble(String text)
	{
		for(int i = 0; i < 3; i++)
		{
			bubble[i]= new GOval(15, 15);
			((GOval) bubble[i]).setFillColor(Color.WHITE);
			((GOval) bubble[i]).setFilled(true);
		}
		
		GRoundRect textBox = new GRoundRect(150, 65);
		textBox.setFillColor(Color.WHITE);
		textBox.setFilled(true);
		
		
		GLabel zzz = new GLabel(text);
		zzz.setFont("SansSerif-bold-22");
		bubble[3] = textBox;
		bubble[4] = zzz;
	}
	
}
