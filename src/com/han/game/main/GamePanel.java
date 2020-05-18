package com.han.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.han.game.control.GetKeys;
import com.han.game.model.GameObject;
import com.han.game.model.Player;

/**
 * 主类
 * @author 十七
 *
 */
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener,
MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Graphics g;
	private Image dbImage;
	private Thread gameLoop;
	private GetKeys getKeys;
	private int back_y;
	private int time;
	private int time2;
	private int time3;
	private int time4;
	
	/**
	 * 0：菜单
	 * 1：说明
	 * 2：退出
	 * 3：加载游戏
	 * 4：死亡
	 * 5：继续
	 * 6：胜利
	 * 7：暂停
	 */
	private int menuMode;

	public Image bgImg;
	public Image pImg;
	public Image bImg;
	public Image eImg;
	public Image eImg2;
	public Image aImg;
	public Image boImg;
	public Image iconImg;
	public Player player;
	public ObjectsArray enemys;
	public ObjectsArray shoots;
	public ObjectsArray bullets;
	public ObjectsArray boss;
	public Point moveP = new Point(0, 0);
	public Point checkP;
	public AudioClip bgm[];
	/**
	 * 是否无敌
	 */
	public boolean isM;

	public GamePanel() {
		dbImage = null;
		player = new Player();
		getKeys = new GetKeys();
		enemys = new ObjectsArray("Enemy", 50);
		shoots = new ObjectsArray("Shoot", 200);
		bullets = new ObjectsArray("Bullet", 400);
		boss = new ObjectsArray("Boss", 10);
		menuMode = 0;
		isM = false;
		time = 0;
		time2 = 0;
		bgm = new AudioClip[8];
		time3 = 0;
		time4 = -1;

		// 背景
		ImageIcon imageicon1 = new ImageIcon(getClass().getResource("/images/bg.png"));
		bgImg = imageicon1.getImage();
		back_y = -bgImg.getHeight(null) + bgImg.getHeight(null);

		ImageIcon imageicon2 = new ImageIcon(getClass().getResource("/images/player00.png"));
		pImg = imageicon2.getImage();

		ImageIcon imageicon3 = new ImageIcon(getClass().getResource("/images/stg1enm.png"));
		eImg = imageicon3.getImage();

		ImageIcon imageicon5 = new ImageIcon(getClass().getResource("/images/stg6enm2.png"));
		bImg = imageicon5.getImage();

		ImageIcon imageicon6 = new ImageIcon(getClass().getResource("/images/etama3.png"));
		aImg = imageicon6.getImage();

		ImageIcon imageicon7 = new ImageIcon(getClass().getResource("/images/etama4.png"));
		eImg2 = imageicon7.getImage();

		ImageIcon imageicon8 = new ImageIcon(getClass().getResource("/images/boom.png"));
		boImg = imageicon8.getImage();
		
		ImageIcon imageicon9 = new ImageIcon(getClass().getResource("/images/timg.jpg"));
		iconImg = imageicon9.getImage();
		
		// 音乐
	    bgm[0]=Applet.newAudioClip(getClass().getResource("/sounds/th06_01.wav"));
	    bgm[1]=Applet.newAudioClip(getClass().getResource("/sounds/th06_02.wav"));
	    bgm[2]=Applet.newAudioClip(getClass().getResource("/sounds/th06_13.wav"));
	    bgm[3]=Applet.newAudioClip(getClass().getResource("/sounds/biubiu.mp3"));
	    bgm[4]=Applet.newAudioClip(getClass().getResource("/sounds/break.wav"));
	    bgm[5]=Applet.newAudioClip(getClass().getResource("/sounds/skill.wav"));
	    bgm[6]=Applet.newAudioClip(getClass().getResource("/sounds/th06_17.wav"));
	    bgm[7]=Applet.newAudioClip(getClass().getResource("/sounds/don00.wav"));

		// 开启焦点-按键
		setFocusable(true);
		// 添加键盘监听事件
		addKeyListener(this);
		// 添加鼠标监听事件
		addMouseListener(this);
		addMouseMotionListener(this);

		GameObject.gameObjectInit(this);
		gameLoop = new Thread(this);
		gameLoop.start();
		
		new TimeThread().start();
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime3() {
		return time3;
	}

	public void setTime3(int time3) {
		this.time3 = time3;
	}
	
	public int getTime4() {
		return time4;
	}

	public void setTime4(int time4) {
		this.time4 = time4;
	}

	/**
	 * 游戏初次载入
	 */
	public void gameSet() {
		menuMode = 0;
		// 载入角色信息
		player.setData(312, 539, 0, 0, 1, 0, 0, 10);
		shoots.allErase();
		bullets.allErase();
		enemys.allErase();
		time2 = 0;
	}

	/**
	 * 游戏资源更新
	 */
	public void gameUpdate() {
		if (menuMode == 3) {
			bGM(1, 0);
			bullets.allMove();
			shoots.allMove();
			enemys.allMove();
			boss.allMove();
			player.move(getKeys);
			if (getKeys.esc) {
				setMenuMode(7);
				getKeys.esc = false;
			}
			
		}
	}

	/**
	 * 游戏资源载入
	 */
	private void gameRender() {
		// 创建窗口，双缓冲
		if (dbImage == null) {
			dbImage = createImage(850, 1000);
			if (dbImage == null)
				return;
			g = dbImage.getGraphics();
		}

		// 背景叠加循环
		g.drawImage(bgImg, 0, back_y++, null);
		g.drawImage(bgImg, 0, back_y - bgImg.getHeight(null), null);
		if (back_y == bgImg.getHeight(null)) {
			back_y = 0;
		}

		player.draw(g);
		shoots.allDraw(g);
		enemys.allDraw(g);
		bullets.allDraw(g);
		boss.allDraw(g);
		
		if (isM) {
			ImageIcon imageicon = new ImageIcon(getClass().getResource("/images/stg7enm.png"));
			Image dun = imageicon.getImage();
			g.drawImage(dun, (int)player.getPx()-30, (int)player.getPy()-15,
					(int)player.getPx()+34, (int)player.getPy()+49, 0, 96, 64, 160, null);
			time++;
			if (time == 200) {
				isM = false;
				time = 0;
			}
		}

		// boss入场条件
		if (player.getFrame() >= 3300) {
			g.drawRect(30, 100, 500, 10);
			// boss血条阶段管理
			int life = enemys.getObject(0).getLife();

			if (life > 1000) {
				g.setColor(Color.yellow);
				g.fillRect(30, 100, life - 1000, 11);
			} else if (life >= 500) {
				g.setColor(Color.blue);
				g.fillRect(30, 100, life - 500, 11);
			} else if (life >= 0) {
				g.setColor(Color.red);
				g.fillRect(30, 100, life, 11);
			}
		}
		// 玩家血条
		g.setColor(Color.red);
		g.drawRect(600, 600, 200, 10);
		g.fillRect(600, 600, player.getLife() * 20, 11);
		

		// 分数和火力
		paintScore(g);
		
		if (menuMode != 3) {
			if (menuMode == 0) {
				
				bGM(0, 1);
				// 游戏开始界面
				ImageIcon imageicon = new ImageIcon(getClass().
						getResource("/images/title00.png"));
				Image menu = imageicon.getImage();
				// 标题字体
				imageicon = new ImageIcon(getClass().getResource("/images/front.png"));
				Image title = imageicon.getImage();
				// 开始
				imageicon = new ImageIcon(getClass().getResource("/images/start.png"));
				Image start = imageicon.getImage();
				// 帮助
				imageicon = new ImageIcon(getClass().getResource("/images/help.png"));
				Image help = imageicon.getImage();
				// 退出
				imageicon = new ImageIcon(getClass().getResource("/images/Exit.png"));
				Image Exit = imageicon.getImage();
				////////////////////////////////////////////
				int i = 60;
				g.drawImage(menu, 0, 0, 850, 1000, 0, 0, 640, 480, null);
				g.drawImage(title, 1, 1, 128, 128, 0, 0, 64, 64, null);
				g.drawImage(title, 71 + i, 10, 198 + i, 137, 64, 0, 128, 64, null);
				g.drawImage(title, 141 + 2 * i, 10, 268 + 4 * i, 128 + i,
						128, 0, 192, 64, null);
				g.drawImage(title, 211 + 5 * i, 10, 338 + 5 * i, 137, 192, 0, 256, 64, null);
				g.drawImage(title, 281 + 6 * i, 10, 408 + 6 * i, 137, 0, 64, 64, 128, null);
				// 按钮
				g.drawImage(start, 600, 400, 800, 500, 0, 0, 100, 32, null);
				g.drawImage(help, 600, 530, 800, 630, 0, 0, 52, 32, null);
				g.drawImage(Exit, 600, 650, 800, 750, 0, 0, 100, 32, null);
				// 鼠标移动到按钮是的效果
				// 开始游戏
				if ((moveP.getX() >= 600 && moveP.getX() <= 800) &&
						(moveP.getY() >= 400 && moveP.getY() <= 500)) {
					Color c = g.getColor();
					g.setColor(Color.white);
					g.drawRect(600, 400, 200, 100);
					g.setColor(c);
				}
				// 说明
				if ((moveP.getX() >= 600 && moveP.getX() <= 800) &&
						(moveP.getY() >= 530 && moveP.getY() <= 630)) {
					Color c = g.getColor();
					g.setColor(Color.yellow);
					g.drawRect(600, 530, 200, 100);
					g.setColor(c);
				}
				// 退出
				if ((moveP.getX() >= 600 && moveP.getX() <= 800) &&
						(moveP.getY() >= 650 && moveP.getY() <= 750)) {
					Color c = g.getColor();
					g.setColor(Color.pink);
					g.drawRect(600, 650, 200, 100);
					g.setColor(c);
				}
			// 说明页面
			} else if (menuMode == 1) {
				ImageIcon imageicon = new ImageIcon(getClass().
						getResource("/images/slpl00a.png"));
				Image help1 = imageicon.getImage();
				imageicon = new ImageIcon(getClass().getResource("/images/slpl00b.png"));
				Image help2 = imageicon.getImage();
				g.drawImage(help1, 0, 0, 850, 500, 0, 0, 256, 256, null);
				g.drawImage(help2, 0, 500, 850, 1000, 0, 0, 256, 240, null);
				if ((moveP.getX() >= 705 && moveP.getX() <= 835) &&
						(moveP.getY() >= 920 && moveP.getY() <= 1000)) {
					Color c = g.getColor();
					g.setColor(Color.red);
					g.drawRect(705, 920, 130, 990);
					g.setColor(c);
				}
			// 退出
			} else if (menuMode == 2) {
            	System.exit(0);
            // 死亡	
            } else if (menuMode == 4) {
				ImageIcon imageicon = new ImageIcon(getClass().
						getResource("/images/result.png"));
				Image dead = imageicon.getImage();
				g.drawImage(dead, 0, 0, 850, 1000, 0, 0, 640, 480, null);
				if ((moveP.getX() >= 20 && moveP.getX() <= 310) &&
						(moveP.getY() >= 670 && moveP.getY() <= 850)) {
					Color c = g.getColor();
					g.setColor(Color.red);
					g.drawRect(20, 670, 310, 180);
					g.setColor(c);
				}
				if ((moveP.getX() >= 350 && moveP.getX() <= 620) &&
						(moveP.getY() >= 670 && moveP.getY() <= 850)) {
					Color c = g.getColor();
					g.setColor(Color.green);
					g.drawRect(350, 670, 270, 180);
					g.setColor(c);
				}
			// 继续
			} else if (menuMode == 5) {
				bullets.allErase();
				player.setLife(10);
				player.setBoom(3);
				menuMode = 3;
				isM = true;
			// 胜利	
			} else if (menuMode == 6) {
				ImageIcon imageicon = new ImageIcon(getClass().
						getResource("/images/end03.jpg"));
				Image ve = imageicon.getImage();
				g.drawImage(ve, 0, 0, 850, 1000, 0, 0, 640, 480, null);
				if ((moveP.getX() >= 740 && moveP.getX() <= 815) &&
						(moveP.getY() >= 200 && moveP.getY() <= 720)) {
					Color c = g.getColor();
					g.setColor(Color.black);
					g.drawRect(740, 200, 75, 520);
					g.setColor(c);
				}
			// 暂停（方便截图用）
			} else if (menuMode == 7) {
				ImageIcon imageicon = new ImageIcon(getClass().
						getResource("/images/pause.png"));
				Image dead = imageicon.getImage();
				g.drawImage(dead, 0, 0, 800, 900, 0, 0, 850, 1000, null);
				if (getKeys.esc) {
					setMenuMode(3);
					getKeys.esc = false;
				}
				
			}
		}
	}
	
	

	
	public void setTime2(int time2) {
		this.time2 = time2;
	}

	/**
	 *  bgm[0]=背景音乐
	 *  bgm[1]=道中音乐
	 *  bgm[2]=boss音乐
	 *  bgm[3]=被击音乐
	 *  bgm[4]=击破音乐
	 *  bgm[5]=技能音乐
	 *  bgm[6]=胜利音乐
	 *  bgm[7]=计时音乐
	 * @param i
	 */
	public void bGM(int i, int j) {
		time2++;
		if (time2 == 1) {
			bgm[i].loop();
		}
		bgm[j].stop();
	}
	
	/**
	 * 加载选项模块
	 * @param i
	 */
	public void setMenuMode(int i) {
		menuMode = i;
	}
	
	/**
	 * 
	 * @param g 画分，画火力，B弹
	 */
	public void paintScore(Graphics g) {
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString("SCORE: " + player.getScore(), 600, 200);
		g.drawString("POWER: " + player.getPower(), 600, 300);
		g.drawString("BOOM: " + player.getBoom(), 600, 400);
		g.drawString("现在可以按键暂停：ESC", 600, 500);
		
		if (time4 == 20) {
			bgm[7].loop();
		}
		if (time4 >= 0) {
			g.setColor(new Color(0xFF0000));
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			g.drawString("倒计时: " + time4, 600, 500);
		}
	
		
		
	}

	/**
	 * 画窗口初始化
	 */
	private void paintScreen() {
		Graphics g = getGraphics();
		if (g != null && dbImage != null)
			g.drawImage(dbImage, 0, 0, null);
	}

	/**
	 * 时间线程控制，间隔1s
	 * @author 十七
	 *
	 */
	class TimeThread extends Thread {
		public void run() {
			while (true) {
//				time3 += time4;
//				if (time3 == 14) {
//					time4 = -1;
//				}
//				if (time3 == 0) {
//					time4 = 1;
//				}
				time3++;
				time4--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 线程20毫秒
	 */
	public void run() {
//		System.out.println("执行");
		System.out.println(menuMode);
		gameSet();
		do {
			gameUpdate();
			gameRender();
			paintScreen();
			
			
			// 屏幕睡眠
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 玩家死亡检测
			if (!player.getExist()) {
				setMenuMode(4);
			}
		} while (true);
	}

	// 按键监听事件
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		getKeys.keyPressed(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e) {
		getKeys.keyReleased(e.getKeyCode());
	}

	// 获取鼠标焦点
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
		moveP = e.getPoint();
	}

	// 鼠标监听事件
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		checkP = e.getPoint();
		// 开始菜单
		if (menuMode == 0) {
			// 开始游戏
			if ((moveP.getX() >= 600 && moveP.getX() <= 800) &&
					(moveP.getY() >= 400 && moveP.getY() <= 500)) {
				gameSet();
				setMenuMode(3);
			}
			// 说明
			if ((moveP.getX() >= 600 && moveP.getX() <= 800) &&
					(moveP.getY() >= 530 && moveP.getY() <= 630)) {
				setMenuMode(1);
			}
			// 退出
			if ((moveP.getX() >= 600 && moveP.getX() <= 800) &&
					(moveP.getY() >= 650 && moveP.getY() <= 750)) {
				setMenuMode(2);
			}
		} 
		// 说明菜单
		if (menuMode == 1) {
			if ((moveP.getX() >= 705 && moveP.getX() <= 835) &&
					(moveP.getY() >= 920 && moveP.getY() <= 1000)) {
				setMenuMode(0);
			}
		}
		// 继续页面
		if (menuMode == 4) {
			// 继续
			if ((moveP.getX() >= 20 && moveP.getX() <= 310) &&
					(moveP.getY() >= 670 && moveP.getY() <= 850)) {
				System.out.println("继续");
				setMenuMode(5);
			}
			// 回主菜单
            if ((moveP.getX() >= 350 && moveP.getX() <= 620) &&
					(moveP.getY() >= 670 && moveP.getY() <= 850)) {
            	player.erase();
            	gameSet();
            	bgm[1].stop();
            	bgm[2].stop();
            	bgm[7].stop();
            	time4 = -1;
            	setMenuMode(0);
			}
		}
		// 胜利页面
		if (menuMode == 6) {
        	if ((moveP.getX() >= 740 && moveP.getX() <= 815) &&
					(moveP.getY() >= 200 && moveP.getY() <= 720)) {
            	setMenuMode(0);
            	bgm[6].stop();
            	gameSet();
			}
        }
	}

}
