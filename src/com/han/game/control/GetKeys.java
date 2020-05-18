package com.han.game.control;


/**
 * °´¼üÀà
 * @author Ê®Æß
 *
 */
public class GetKeys {

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean z;
	public boolean x;
	public boolean shift;
	public boolean esc;
	public boolean space;
	

	public GetKeys() {
		up = down = left = right = false;
		z = x = shift = space = false;
		
		
	}

	public void keyPressed(int i) {
		if (i == 37)
			left = true;
		if (i == 39)
			right = true;
		if (i == 38)
			up = true;
		if (i == 40)
			down = true;
		if (i == 90)
			z = true;
		if (i == 88)
			x = true;
		if (i == 16)
			shift = true;
		if (i == 27)
			esc = true;
		if (i == 32)
			space = true;
	}

	public void keyReleased(int i) {
		if (i == 37)
			left = false;
		if (i == 39)
			right = false;
		if (i == 38)
			up = false;
		if (i == 40)
			down = false;
		if (i == 90)
			z = false;
		if (i == 88)
			x = false;
		if (i == 16)
			shift = false;
		if (i == 27)
			esc = false;
		if (i == 32)
			space = false;
	}
}
