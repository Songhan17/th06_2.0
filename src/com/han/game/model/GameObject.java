package com.han.game.model;

import java.awt.Graphics;

import com.han.game.main.GamePanel;

/**
 * 所有的父类
 * 
 * @author 十七
 *
 */
public class GameObject {

	
	protected static GamePanel p;
	protected double px;
	protected double py;
	protected double vx;
	protected double vy;
	protected double th;
	protected int size;
	protected int frame;
	protected int type;
	protected int life;
	protected char anim;
	protected boolean exist;
	protected GameObject tmp;

	public int count;
	
	

	public GameObject() {
		px = py = vx = vy = 0;
		frame = size = type = life = 0;
		th = 0;
		exist = false;
		anim = 'n';
		
	}

	public static void gameObjectInit(GamePanel gamepanel) {
		p = gamepanel;
	}

	/**
	 * 
	 * @param d  x坐标
	 * @param d1 y坐标
	 * @param d2 x增量
	 * @param d3 y增量
	 * @param i  尺寸判断绘制图
	 * @param j  帧数
	 * @param k  敌人分数类型
	 * @param l  生命数
	 * @param m  动画类型
	 */
	public void setData(double d, double d1, double d2, double d3,
			int i, int j, int k, int l, char m) {
		px = d;
		py = d1;
		vx = d2;
		vy = d3;
		size = i;
		frame = j;
		type = k;
		life = l;
		anim = m;
		exist = true;
	}

	int i = 32;
	int j = 48;

	public void draw(Graphics g) {
		
		if (!exist) {
			return;
		}

		// 自机
		if (size == 1) {
			if (anim == 'n') {
				if (p.timepaint == 1) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, 0, 4, i, j, null);
				}
				if (p.timepaint == 2) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i, 4, i * 2, j, null);
				}
				if (p.timepaint == 3) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i * 2, 4, i * 3, j, null);
				}
				if (p.timepaint == 4) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i * 3, 4, i * 4 - 2, j, null);
				}
			}
			if (anim == 'l') {
				if (p.timepaint == 1) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, 0, 4 + j, i, j * 2, null);
				}
				if (p.timepaint == 2) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i, 4 + j, i * 2, j * 2, null);
				}
				if (p.timepaint == 3) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i * 2, 4 + j, i * 3, j * 2, null);
				}
				if (p.timepaint == 4) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i * 3, 4 + j, i * 4 - 2, j * 2, null);
				}
			}
			if (anim == 'r') {
				if (p.timepaint == 1) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, 0+i, 4 + j, i-i, j * 2, null);
				}
				if (p.timepaint == 2) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i+i, 4 + j, i * 2-i, j * 2, null);
				}
				if (p.timepaint == 3) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i * 2+i, 4 + j, i * 3-i, j * 2, null);
				}
				if (p.timepaint == 4) {
					g.drawImage(p.pImg, (int) px - 16, (int) py - 16, (int) px + 24,
							(int) py + 44, i * 3+i, 4 + j, i * 4 - 2-i, j * 2, null);
				}
			}
		}
		// 小妖精
		if (size == 2) {
			if (p.timepaint == 1) {
				g.drawImage(p.eImg, (int) px - 16, (int) py - 16, (int) px + 16,
						(int) py + 16,i * 4, 0, i * 5, 33, null);
			}
			if (p.timepaint == 2) {
				g.drawImage(p.eImg, (int) px - 16, (int) py - 16, (int) px + 16,
						(int) py + 16,i * 5, 0, i * 6, 33, null);
			}
			if (p.timepaint == 3) {
				g.drawImage(p.eImg, (int) px - 16, (int) py - 16, (int) px + 16,
						(int) py + 16,i * 6, 0, i * 7, 33, null);
			}
			if (p.timepaint == 4) {
				g.drawImage(p.eImg, (int) px - 16, (int) py - 16, (int) px + 16,
						(int) py + 16,i * 7, 0, i * 8, 33, null);
			}
			
		}
		// 大妖精
		if (size == 3) {
			if (p.timepaint == 1) {
				g.drawImage(p.eImg, (int) px - 20, (int) py - 20, (int) px + 20,
						(int) py + 20, i * 4, 34, i * 5, 64, null);
			}
			if (p.timepaint == 2) {
				g.drawImage(p.eImg, (int) px - 20, (int) py - 20, (int) px + 20,
						(int) py + 20, i * 5, 34, i * 6, 64, null);
			}
			if (p.timepaint == 3) {
				g.drawImage(p.eImg, (int) px - 20, (int) py - 20, (int) px + 20,
						(int) py + 20, i * 6, 34, i * 7, 64, null);
			}
			if (p.timepaint == 4) {
				g.drawImage(p.eImg, (int) px - 20, (int) py - 20, (int) px + 20,
						(int) py + 20, i * 7, 34, i * 8, 64, null);
			}
			
		}

		// boss
		if (size == 4) {
			if (anim == 'n') {
				g.drawImage(p.bImg, (int) px - 16, (int) py - 16, (int) px + 24,
						(int) py + 44, 6, 2, 55, 76, null);
			}
			if (anim == 'l') {
				g.drawImage(p.bImg, (int) px - 16, (int) py - 16, (int) px + 24,
						(int) py + 44, 124, 2, 62, 76, null);
			}
			if (anim == 'r') {
				g.drawImage(p.bImg, (int) px - 16, (int) py - 16, (int) px + 24,
						(int) py + 44, 62, 2, 124, 76, null);
			}
			
		}

		// boss-分身-蕾咪蝙蝠
//		if (size == 7) {
//			g.drawImage(p.bImg, (int) px - 16, (int) py - 14, (int) px + 32,
//					(int) py + 15, 1, 82, 65, 111, null);
//		}

		// 奖励-火力
		if (size == 5) {
			g.drawImage(p.aImg, (int) px - 16, (int) py - 16, (int) px + 12,
					(int) py + 14, 1, 1, 16, 16, null);
		}
		// 奖励-点数
		if (size == 6) {
			g.drawImage(p.aImg, (int) px - 16, (int) py - 16, (int) px + 12,
					(int) py + 14, 16, 1, 31, 16, null);
		}
		// 奖励-Boom
		if (size == 8) {
			g.drawImage(p.aImg, (int) px - 16, (int) py - 16, (int) px + 12,
					(int) py + 14, 48, 0, 64, 16, null);
		}
		// 奖励-生命
		if (size == 9) {
			g.drawImage(p.aImg, (int) px - 16, (int) py - 16, (int) px + 12,
					(int) py + 14, 80, 0, 96, 16, null);
		}

		// 自机子弹1
		if (size == 16) {
			g.drawImage(p.pImg, (int) px, (int) py - 20, (int) px + 15,
					(int) py - 5, 129, 1, 144, 16, null);
		}
		
		// 自机子弹2
		if (size == 20) {
			g.drawImage(p.pImg, (int) px, (int) py - 20, (int) px + 15,
					(int) py - 5, 145, 1, 160, 16, null);
		}
		
		// 自机B弹
		if (size == 101) {
			g.drawImage(p.boImg, (int) px - 260, (int) py - 300, (int) px + 260,
					(int) py + 300, 0, 0, 256, 256, null);
		}
		
		// 妖精子弹1
		if (size == 15) {
			g.drawImage(p.aImg, (int) px, (int) py - 20, (int) px + 15,
					(int) py + 5, 18, 64, 30, 80, null);
		}
		
		// 妖精子弹2
		if (size == 23) {
			g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
					(int) py - 5, 128, 32, 144, 48, null);
		}
		
		// boss子弹1
		if (size == 24) {
			g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
					(int) py - 5, 160, 48, 176, 64, null);
		}
		
		// boss子弹2
		if (size == 25) {
			g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
					(int) py - 5, 96, 48, 112, 64, null);
		}
		
		// boss子弹3
		if (size == 32) {
			g.drawImage(p.eImg2, (int) px - 42, (int) py - 42, (int) px + 42,
					(int) py + 42, 0, 0, 64, 64, null);
		}
		
		// boss子弹3
		if (size == 17) {
			g.drawImage(p.aImg, (int) px - 8, (int) py - 16, (int) px + 8,
					(int) py + 16, 72, 160, 88, 192, null);
		}
		
		// boss子弹4
		if (size == 26) {
			int q = 16;
			if (type == 1) {
				g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
						(int) py - 5, 144, 48, 160, 64, null);
			}
			if (type == 2) {
				g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
						(int) py - 5, 128 - 3 * q, 48,
						144 - 3 * q, 64, null);
			}
			if (type == 3) {
				g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
						(int) py - 5, 112 - 3 * q, 48,
						128 - 3 * q, 64, null);
			}
		}
		
		// boss子弹3
		if (size == 18) {
			g.drawImage(p.aImg, (int) px - 8, (int) py - 28, (int) px + 15,
					(int) py - 5, 48, 48, 64, 64, null);
		}
		
	}
	

	public void move() {
		px += vx;
		py += vy;
		frame++;
		if (px < -10 || py < 0 || px > 560 || py > 1000)
			exist = false;
	}
	

	public void erase() {
		exist = false;
	}

	public double getPx() {
		return px;
	}

	public double getPy() {
		return py;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getTh() {
		return th;
	}

	public int getSize() {
		return size;
	}

	public int getFrame() {
		return frame;
	}

	public int getType() {
		return type;
	}

	public int getLife() {
		return life;
	}

	public boolean getExist() {
		return exist;
	}
}
