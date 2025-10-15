// Character.java
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {
    public String name;
    public double health;
    public int attack;
    public int agility;
    private ImageView sprite;

    public Character(String name, double health, int attack, int agility,
                     String imagePath, double fitWidth, double fitHeight,
                     double layoutX, double layoutY) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.agility = agility;

        Image img = new Image(imagePath);
        this.sprite = new ImageView(img);
        this.sprite.setFitWidth(fitWidth);
        this.sprite.setFitHeight(fitHeight);
        this.sprite.setPreserveRatio(true);
        this.sprite.setLayoutX(layoutX);
        this.sprite.setLayoutY(layoutY);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void attack(Character target, double damage) {
        target.takeDamage(damage);
        System.out.printf("%s атаковал %s на %.1f урона. Здоровье %s: %.1f%n",
                this.name, target.name, damage, target.name, target.health);
    }
}
