import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Character hero = new Character("Пепе", 100,20,80,
                "file:images/hero.png", 200, 200, 100,250);
        Character enemy = new Character("Скелет", 100,19,80,
                "file:images/skelet.png", 200, 200, 500, 250);

        System.out.println("Герой: " + hero.name + " | Здоровье: " + hero.health +
                " | Сила: " + hero.attack + " | Инициатива: " + hero.agility);
        System.out.println("Противник: " + enemy.name + " | Здоровье: " + enemy.health
                + " | Сила: " + enemy.attack + " | Инициатива: " + enemy.agility);
        System.out.println("\nБИТВА НАЧИНАЕТСЯ!\n");

        Random random = new Random();
        double minHeroDamage = hero.attack * 0.7;
        double minEnemyDamage = enemy.attack * 0.7;

        int round = 1;

        while (hero.isAlive() && enemy.isAlive()) {
            System.out.println("РАУНД №" + round);
            round++;
            double heroDamage = minHeroDamage + random.nextDouble() * (hero.attack - minHeroDamage);
            double enemyDamage = minEnemyDamage + random.nextDouble() * (enemy.attack - minEnemyDamage);

            boolean heroAttacksFirst = random.nextInt(100) < hero.agility;
            boolean enemyAttacksFirst = random.nextInt(100) < enemy.agility;

            if (!heroAttacksFirst && !enemyAttacksFirst) {
                heroAttacksFirst = random.nextBoolean();
                enemyAttacksFirst = !heroAttacksFirst;
            }

            if (heroAttacksFirst) {
                hero.attack(enemy, heroDamage);
                if (!enemy.isAlive()) {
                    System.out.println(enemy.name + " пал! " + hero.name + " победил!");
                    break;
                }

                enemy.attack(hero, enemyDamage);
                if (!hero.isAlive()) {
                    System.out.println(hero.name + " пал! " + enemy.name + " победил!");
                    break;
                }
            } else {
                enemy.attack(hero, enemyDamage);
                if (!hero.isAlive()) {
                    System.out.println(hero.name + " пал! " + enemy.name + " победил!");
                    break;
                }

                hero.attack(enemy, heroDamage);
                if (!enemy.isAlive()) {
                    System.out.println(enemy.name + " пал! " + hero.name + " победил!");
                    break;
                }
            }
            System.out.println();
        }
    }
   }