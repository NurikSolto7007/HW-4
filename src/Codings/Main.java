package Codings;

import java.util.Random;

public class Main {


    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 400, 350};
    public static int[] heroesDamage = {10, 20, 5, 10, 7};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Mental",  "Thor",  "Medical"};

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            stan();
            round();
        }
    }
    public static void stan () {
        Random random = new Random();
        int r = random.nextInt(3);
        if (r == 1) {
            System.out.println("_____________________");

            System.out.println("Thor stunned the Boss");
            System.out.println("_____________________");
            bossDamage=0;

        }else {
            bossDamage=50;
        }
    }


    public static int aibolit(int indexPlayer) {
        if (heroesHealth[indexPlayer] > 0 && heroesHealth[4] > 0) {
            return heroesDamage[4] + heroesHealth[indexPlayer];
        }
        return heroesHealth[indexPlayer];
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        printStatistics();
        for (int i = 0; i <= 3; i++) {
            heroesHealth[i] = aibolit(i);
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1]
                <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }

            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(10) + 2; // 0,1,2,3,4,5
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[4]);
        System.out.println("Tor health: " + heroesHealth[3]);
    }
}
