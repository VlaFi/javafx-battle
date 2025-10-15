// GameUI.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.Random;

public class GameUI extends Application {
    Random random = new Random();

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        ImageView background = new ImageView(new javafx.scene.image.Image("file:images/background.png"));
        background.setFitWidth(800);
        background.setFitHeight(600);

        Character hero = new Character("Пепе", 100, 20, 80,
                "file:images/hero.png", 200, 200, 100, 250);
        Character enemy = new Character("Скелет", 100, 19, 80,
                "file:images/skelet.png", 200, 200, 500, 250);

        Label heroHP = new Label();
        heroHP.setFont(new Font("Arial", 20));
        heroHP.setTextFill(Color.WHITE);
        heroHP.setLayoutX(50);
        heroHP.setLayoutY(20);

        Label enemyHP = new Label();
        enemyHP.setFont(new Font("Arial", 20));
        enemyHP.setTextFill(Color.WHITE);
        enemyHP.setLayoutX(550);
        enemyHP.setLayoutY(20);

        updateHPLabels(hero, enemy, heroHP, enemyHP);

        Button attackBtn = new Button("Атаковать");
        attackBtn.setLayoutX(350);
        attackBtn.setLayoutY(500);

        attackBtn.setOnAction(e -> {
            if (!hero.isAlive() || !enemy.isAlive()) return;

            animateAttack(hero.getSprite());

            double minHeroDamage = hero.attack * 0.7;
            double heroDamage = minHeroDamage + random.nextDouble() * (hero.attack - minHeroDamage);
            hero.attack(enemy, heroDamage);
            updateHPLabels(hero, enemy, heroHP, enemyHP);

            if (!enemy.isAlive()) {
                enemyHP.setText("Враг повержен!");
                return;
            }

            animateAttack(enemy.getSprite());

            double minEnemyDamage = enemy.attack * 0.7;
            double enemyDamage = minEnemyDamage + random.nextDouble() * (enemy.attack - minEnemyDamage);
            enemy.attack(hero, enemyDamage);
            updateHPLabels(hero, enemy, heroHP, enemyHP);

            if (!hero.isAlive()) {
                heroHP.setText("Герой пал!");
            }
        });

        root.getChildren().addAll(background, hero.getSprite(), enemy.getSprite(), heroHP, enemyHP, attackBtn);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Мини-бой");
        stage.setScene(scene);
        stage.show();
    }

    public void animateAttack(ImageView attacker) {
        TranslateTransition moveForward = new TranslateTransition(Duration.millis(100), attacker);
        moveForward.setByX(40);

        TranslateTransition moveBack = new TranslateTransition(Duration.millis(100), attacker);
        moveBack.setByX(-40);

        moveForward.setOnFinished(e -> moveBack.play());

        moveForward.play();

    }

    private void updateHPLabels(Character hero, Character enemy, Label heroHP, Label enemyHP) {
        heroHP.setText("Здоровье героя: " + (int) hero.health);
        enemyHP.setText("Здоровье врага: " + (int) enemy.health);
    }

    public static void main(String[] args) {
        launch();
    }
}
