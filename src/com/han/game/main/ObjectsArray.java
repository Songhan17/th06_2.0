package com.han.game.main;

import java.awt.Graphics;

import com.han.game.model.Boss;
import com.han.game.model.Bullet;
import com.han.game.model.Enemy;
import com.han.game.model.GameObject;
import com.han.game.model.Shoot;

/**
 * 数组类
 * 
 * @author 张涵霖
 *
 */
public class ObjectsArray {
	// 父类对象
	private GameObject gameObject[];
	private int emptySearch;
	// 数组上限
	private int arrayMax;
	private int i;

	/**
	 * 区分数组存放对象
	 * 
	 * @param s
	 * @param j
	 */
	public ObjectsArray(String s, int j) {
		emptySearch = 0;
		arrayMax = j;
		gameObject = new GameObject[j];
		for (i = 0; i < j; i++) {
			if (s.equals("Bullet")) {
				gameObject[i] = new Bullet();
			}
			if (s.equals("Shoot")) {
				gameObject[i] = new Shoot();
			}
			if (s.equals("Enemy")) {
				gameObject[i] = new Enemy();
			}
			if (s.equals("Boss")) {
				gameObject[i] = new Boss();
			}
		}
	}

	public void allMove() {
		for (i = 0; i < arrayMax; i++) {
			if (gameObject[i].getExist()) {
				gameObject[i].move();
			}
		}
	}

	public void allDraw(Graphics g) {
		for (i = 0; i < arrayMax; i++) {
			if (gameObject[i].getExist()) {
				gameObject[i].draw(g);
			}
		}
	}

	/**
	 * 移除所有
	 */
	public void allErase() {
		for (i = 0; i < arrayMax; i++)
			gameObject[i].erase();
	}
	// 返回空闲数组位
	public GameObject getEmpty() {
		for (i = 0; i < arrayMax; i++) {
			// 如果该数组位未被占用
			if (!gameObject[emptySearch].getExist()) {
				// 传回该数组
				return gameObject[emptySearch];
			}
			// 下标+1
			emptySearch++;
			// 如果下标超过规定数组
			if (emptySearch >= arrayMax) {
				// 返回第零位数组
				emptySearch = 0;
			}
		}
		return null;
	}

	public GameObject getObject(int j) {
		return gameObject[j];
	}

	public int getArrayMax() {
		return arrayMax;
	}
}
