package com.han.game.model;

import java.awt.Graphics;

/**
 * boss二阶段弹幕对象类
 * @author 张涵霖
 *
 */
public class Boss extends GameObject {
	int degree;
	public Boss() {
		degree = 0;
	}
	
	public void setData(double d, double d1, double d2, double d3, 
			 int i, int j, int k, int l) {
	        super.setData(d, d1, d2, d3, i, j, k, l);
	    }
	
	public void move() {
		super.move();
		vx = 6*Math.sin(Math.PI*degree/36);
		vy = 6*Math.cos(Math.PI*degree/36);
		degree++;
//		System.out.println(degree);
		if (p.boss.getObject(0).type == 3) {
			vx = 0;
			vy = 0;
		}
		
		if (p.boss.getObject(0).type == 4) {
			vx = 0;
			vy = 0;
		}
		
	}
	public void draw(Graphics g) {
		super.draw(g);
	}
}
