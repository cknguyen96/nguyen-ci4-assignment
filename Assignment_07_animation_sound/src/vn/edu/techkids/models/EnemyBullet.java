package vn.edu.techkids.models;

import vn.edu.techkids.controllers.enemybullets.EnemyBulletType;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBullet extends GameObject {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int DAMAGE_DEFAULT = 1;

    private int damage = DAMAGE_DEFAULT;
    private EnemyBulletType enemyBulletType = EnemyBulletType.NONE;

    public EnemyBullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public EnemyBullet(int x, int y, int width, int height, int damage)
    {
        this(x, y, width, height);
        this.damage = damage;
    }

    public EnemyBulletType getEnemyBulletType() {
        return enemyBulletType;
    }

    public EnemyBullet(int x, int y, int width, int height, int damage, EnemyBulletType enemyBulletType)
    {
        this(x, y, width, height);
        this.damage = damage;
        this.enemyBulletType = enemyBulletType;

    }

    public int getDamage() {
        return damage;
    }
}
