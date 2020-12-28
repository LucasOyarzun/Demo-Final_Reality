package com.github.LucasOyarzun.finalreality.gui;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.Computer;
import com.github.LucasOyarzun.finalreality.model.Player;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.InvalidEquipException;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Engineer;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Knight;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Thief;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.WhiteMage;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;
import com.github.LucasOyarzun.finalreality.phases.InvalidDecisionException;
import com.github.LucasOyarzun.finalreality.phases.InvalidTransitionException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Main entry point for the application.
 * This game has just one battle between 4 playerCharacters and 4 enemies.
 * The battle ends when every enemies or characters died.
 * To attack it's necessary to start Attack Phase
 * Player can change character's weapon just in Main Phase.
 * Some character's classes can equip predefined weapons, if it can't, an announcement will say it.
 * @author Lucas Oyarzun Mendez.
 */
public class FinalReality extends Application {
  private GameController controller = new GameController();
  private BlockingQueue<ICharacter> turns = controller.getTurns();
  private Player player = controller.getPlayer();
  private Computer com = controller.getCom();

  private static final String RESOURCE_PATH = "src/main/resources/";

  private final Knight adelbert = new Knight("Adelbert", 200, 30, turns);
  private final WhiteMage eiko = new WhiteMage("Eiko", 100, 10, 80, turns);
  private final Thief zidane = new Thief("Zidane", 100, 15, turns);
  private final Engineer cid = new Engineer("Cid", 130, 20, turns);

  private final Enemy devil1 = new Enemy("Devil", 60, 10, 20, 65, turns);
  private final Enemy devil2 = new Enemy("Devil", 60, 10, 20, 65, turns);
  private final Enemy ent = new Enemy("Ent", 500, 20, 40, 50, turns);
  private final Enemy assassin = new Enemy("Assassin", 80, 5, 25, 90, turns);

  private final Axe bigAxe = new Axe("Big Axe", 70, 40);
  private final Axe shortAxe = new Axe("Short Axe", 40, 20);
  private final Staff magicStaff = new Staff("Magic Staff", 25, 10, 100);
  private final Sword dragonSword = new Sword("Dragon Sword", 60, 35);
  private final Sword longSword = new Sword("Long Sword", 50, 25);
  private final Bow lightningBow = new Bow("Lightning Bow", 50, 20);
  private final Knife laserKnife = new Knife("Laser Knife", 55, 20);
  private final Knife littleKnife = new Knife("Little Knife", 40, 15);


  private final Label actualCharacter = new Label("");
  private final Label actualPhase = new Label("");

  private final Label weapon1 = new Label("");
  private final Label weapon2 = new Label("");
  private final Label weapon3 = new Label("");
  private final Label weapon4 = new Label("");

  private final Label character1 = new Label("");
  private final Label character2 = new Label("");
  private final Label character3 = new Label("");
  private final Label character4 = new Label("");

  private final Label enemy1 = new Label("");
  private final Label enemy2 = new Label("");
  private final Label enemy3 = new Label("");
  private final Label enemy4 = new Label("");

  private final Label invalidAction = new Label("");
  private final Label lastAction = new Label("");
  private final Label preLastAction = new Label("");

  private final Button attackEnemy1Button = new Button("");
  private final Button attackEnemy2Button = new Button("");
  private final Button attackEnemy3Button = new Button("");
  private final Button attackEnemy4Button = new Button("");

  private ImageView spriteEnemy1 = new ImageView();
  private ImageView spriteEnemy2 = new ImageView();
  private ImageView spriteEnemy3 = new ImageView();
  private ImageView spriteEnemy4 = new ImageView();

  private ImageView spriteCharacter1 = new ImageView();
  private ImageView spriteCharacter2 = new ImageView();
  private ImageView spriteCharacter3 = new ImageView();
  private ImageView spriteCharacter4 = new ImageView();

  private ImageView spriteWeapon1 = new ImageView();
  private ImageView spriteWeapon2 = new ImageView();
  private ImageView spriteWeapon3 = new ImageView();
  private ImageView spriteWeapon4 = new ImageView();

  private ImageView background = new ImageView();

  private Button toAttackPhaseButton = new Button("");
  private Label availableWeapons = new Label("");
  private Label weaponAtk = new Label("");
  private Label weaponSpd = new Label("");

  private Button equipWeapon1Button = new Button("");
  private Button equipWeapon2Button = new Button("");
  private Button equipWeapon3Button = new Button("");
  private Button equipWeapon4Button = new Button("");

  private Label finalLabel = new Label("");

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws InterruptedException, FileNotFoundException, InvalidEquipException {

    controller.addPlayerCharacter(adelbert);
    controller.addPlayerCharacter(eiko);
    controller.addPlayerCharacter(zidane);
    controller.addPlayerCharacter(cid);

    controller.addWeapon(bigAxe);
    controller.addWeapon(magicStaff);
    controller.addWeapon(dragonSword);
    controller.addWeapon(lightningBow);
    controller.addWeapon(laserKnife);
    controller.addWeapon(longSword);
    controller.addWeapon(littleKnife);
    controller.addWeapon(shortAxe);

    controller.equipWeapontoCharacter(dragonSword, adelbert);
    controller.equipWeapontoCharacter(magicStaff, eiko);
    controller.equipWeapontoCharacter(longSword, zidane);
    controller.equipWeapontoCharacter(bigAxe, cid);

    controller.addEnemy(devil1);
    controller.addEnemy(devil2);
    controller.addEnemy(ent);
    controller.addEnemy(assassin);

    controller.startGame();

    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(false);
    Group root = new Group();
    Group gameOverRoot = new Group();

    // Music
    String audioFilePath = RESOURCE_PATH + "Music.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
              new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ignored) {
    }

    // Back Ground
    background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);


    // Final Label

    finalLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
    finalLabel.setLayoutX(500);
    finalLabel.setLayoutY(550);
    finalLabel.setTextFill(Color.WHITE);
    root.getChildren().add(finalLabel);

    //ToAttackPhaseButton
    toAttackPhaseButton.setText("Change to AttackPhase");
    toAttackPhaseButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    toAttackPhaseButton.setLayoutX(700);
    toAttackPhaseButton.setLayoutY(450);
    toAttackPhaseButton.setOnAction(event -> {
      try {
        controller.tryToStartAttack();
        invalidAction.setText("");
      } catch (InvalidTransitionException e) {
        invalidAction.setText("Can't Start Attack now");
      }
    });
    root.getChildren().add(toAttackPhaseButton);

    // Information Texts
    invalidAction.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    invalidAction.setLayoutX(100);
    invalidAction.setLayoutY(150);
    invalidAction.setTextFill(Color.BLACK);
    root.getChildren().add(invalidAction);

    lastAction.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    lastAction.setLayoutX(20);
    lastAction.setLayoutY(240);
    lastAction.setTextFill(Color.BLACK);
    root.getChildren().add(lastAction);

    preLastAction.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    preLastAction.setLayoutX(20);
    preLastAction.setLayoutY(200);
    preLastAction.setTextFill(Color.BLACK);
    root.getChildren().add(preLastAction);

    // Actual Character
    actualCharacter.setText("Actual Character: " + controller.getActualCharacterName());
    actualCharacter.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    actualCharacter.setTextFill(Color.BLACK);
    actualCharacter.setLayoutX(700);
    actualCharacter.setLayoutY(400);
    root.getChildren().add(actualCharacter);

    // Actual Phase
    actualPhase.setText("Actual Phase: " + controller.getActualPhaseName());
    actualPhase.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    actualPhase.setTextFill(Color.BLACK);
    actualPhase.setLayoutX(700);
    actualPhase.setLayoutY(420);
    root.getChildren().add(actualPhase);



    // Available Weapons Text


    availableWeapons.setText("Available weapons ");
    availableWeapons.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    availableWeapons.setTextFill(Color.BLACK);
    availableWeapons.setLayoutX(20);
    availableWeapons.setLayoutY(300);
    root.getChildren().add(availableWeapons);

    weaponAtk.setText("Atk: ");
    weaponAtk.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    weaponAtk.setTextFill(Color.BLACK);
    weaponAtk.setLayoutX(200);
    weaponAtk.setLayoutY(320);
    root.getChildren().add(weaponAtk);

    weaponSpd.setText("Weight: ");
    weaponSpd.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    weaponSpd.setTextFill(Color.BLACK);
    weaponSpd.setLayoutX(250);
    weaponSpd.setLayoutY(320);
    root.getChildren().add(weaponSpd);



    // Weapon Buttons
    equipWeapon1Button.setText("Equip");
    equipWeapon1Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon1Button.setLayoutX(300);
    equipWeapon1Button.setLayoutY(360);
    equipWeapon1Button.setOnAction(event -> {
              try {
                controller.trytoChangeWeapon(controller.getPlayerInventory().get(0));
                invalidAction.setText("");
              } catch (InvalidEquipException e) {
                invalidAction.setText(controller.getActualCharacterName() + " Can't Equip " +
                        controller.getWeaponName(0));
              } catch (InvalidDecisionException e) {
                invalidAction.setText(" Can't Equip weapons in Attack Phase");
              }
            }
    );
    root.getChildren().add(equipWeapon1Button);

    equipWeapon2Button.setText("Equip");
    equipWeapon2Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon2Button.setLayoutX(300);
    equipWeapon2Button.setLayoutY(360+100);
    equipWeapon2Button.setOnAction(event -> {
      try {
        controller.trytoChangeWeapon(controller.getPlayerInventory().get(1));
        invalidAction.setText("");
      } catch (InvalidEquipException e) {
        invalidAction.setText(controller.getActualCharacterName() + " Can't Equip " +
                controller.getWeaponName(1));
      } catch (InvalidDecisionException e) {
        invalidAction.setText(" Can't Equip weapons in Attack Phase");
      }
    });
    root.getChildren().add(equipWeapon2Button);

    equipWeapon3Button.setText("Equip");
    equipWeapon3Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon3Button.setLayoutX(300);
    equipWeapon3Button.setLayoutY(360+100*2);
    equipWeapon3Button.setOnAction(event -> {
      try {
        controller.trytoChangeWeapon(controller.getPlayerInventory().get(2));
        invalidAction.setText("");
      } catch (InvalidEquipException e) {
        invalidAction.setText(controller.getActualCharacterName() + " Can't Equip " +
                controller.getWeaponName(2));
      } catch (InvalidDecisionException e) {
        invalidAction.setText(" Can't Equip weapons in Attack Phase");
      }
    });
    root.getChildren().add(equipWeapon3Button);

    equipWeapon4Button.setText("Equip");
    equipWeapon4Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon4Button.setLayoutX(300);
    equipWeapon4Button.setLayoutY(360+100*3);
    equipWeapon4Button.setOnAction(event -> {
      try {
        controller.trytoChangeWeapon(controller.getPlayerInventory().get(3));
        invalidAction.setText("");
      } catch (InvalidEquipException e) {
        invalidAction.setText(controller.getActualCharacterName() + " Can't Equip " +
                controller.getWeaponName(3));
      } catch (InvalidDecisionException e) {
        invalidAction.setText(" Can't Equip weapons in Attack Phase");
      }
    });
    root.getChildren().add(equipWeapon4Button);


    // Weapon Sprites
    spriteWeapon1.setLayoutX(10);
    spriteWeapon1.setLayoutY(340);
    root.getChildren().add(spriteWeapon1);

    spriteWeapon2.setLayoutX(10);
    spriteWeapon2.setLayoutY(340 + 100);
    root.getChildren().add(spriteWeapon2);

    spriteWeapon3.setLayoutX(10);
    spriteWeapon3.setLayoutY(340 + 100*2);
    root.getChildren().add(spriteWeapon3);

    spriteWeapon4.setLayoutX(10);
    spriteWeapon4.setLayoutY(340 + 100*3);
    root.getChildren().add(spriteWeapon4);


    weapon1.setLayoutX(80);
    weapon2.setLayoutX(80);
    weapon3.setLayoutX(80);
    weapon4.setLayoutX(80);
    weapon1.setLayoutY(360);
    weapon2.setLayoutY(460);
    weapon3.setLayoutY(560);
    weapon4.setLayoutY(660);

    root.getChildren().add(weapon1);
    root.getChildren().add(weapon2);
    root.getChildren().add(weapon3);
    root.getChildren().add(weapon4);



    // Attack Buttons
    attackEnemy1Button.setText("Attack");
    attackEnemy1Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    attackEnemy1Button.setLayoutX(650);
    attackEnemy1Button.setLayoutY(200);
    attackEnemy1Button.setOnAction(event -> {
      try {
        String lastCharacter = controller.getActualCharacterName();
        String enemySelected = controller.getEnemyName(0);
        String damage = controller.getDamageDealt(controller.getActualCharacter(),
                controller.getEnemy(0));
        controller.tryToAttack(controller.getEnemy(0));
        preLastAction.setText(lastAction.getText());
        lastAction.setText(lastCharacter + " attacked " + enemySelected + " and did " + damage);
        invalidAction.setText("");
      } catch (InvalidDecisionException e) {
        invalidAction.setText("You're not in Attack Phase");
      }
    });
    root.getChildren().add(attackEnemy1Button);

    attackEnemy2Button.setText("Attack");
    attackEnemy2Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    attackEnemy2Button.setLayoutX(650 +130);
    attackEnemy2Button.setLayoutY(200);
    attackEnemy2Button.setOnAction(event -> {
      try {
        String lastCharacter = controller.getActualCharacterName();
        String enemySelected = controller.getEnemyName(1);
        String damage = controller.getDamageDealt(controller.getActualCharacter(),
                controller.getEnemy(1));
        controller.tryToAttack(controller.getEnemy(1));
        preLastAction.setText(lastAction.getText());
        lastAction.setText(lastCharacter + " attacked " + enemySelected + " and did " + damage + " HP." );
        invalidAction.setText("");
      } catch (InvalidDecisionException e) {
        invalidAction.setText("You're not in Attack Phase");
      }
    });
    root.getChildren().add(attackEnemy2Button);

    attackEnemy3Button.setText("Attack");
    attackEnemy3Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    attackEnemy3Button.setLayoutX(650 +130*2);
    attackEnemy3Button.setLayoutY(200);
    attackEnemy3Button.setOnAction(event -> {
      try {
        String lastCharacter = controller.getActualCharacterName();
        String enemySelected = controller.getEnemyName(2);
        String damage = controller.getDamageDealt(controller.getActualCharacter(),
                controller.getEnemy(2));
        controller.tryToAttack(controller.getEnemy(2));
        preLastAction.setText(lastAction.getText());
        lastAction.setText(lastCharacter + " attacked " + enemySelected + " and did " + damage + " HP." );
        invalidAction.setText("");
      } catch (InvalidDecisionException e) {
        invalidAction.setText("You're not in Attack Phase");
      }
    });
    root.getChildren().add(attackEnemy3Button);

    attackEnemy4Button.setText("Attack");
    attackEnemy4Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    attackEnemy4Button.setLayoutX(650 +130*3);
    attackEnemy4Button.setLayoutY(200);
    attackEnemy4Button.setOnAction(event -> {
      try {
        String lastCharacter = controller.getActualCharacterName();
        String enemySelected = controller.getEnemyName(3);
        String damage = controller.getDamageDealt(controller.getActualCharacter(),
                controller.getEnemy(3));
        controller.tryToAttack(controller.getEnemy(3));
        preLastAction.setText(lastAction.getText());
        lastAction.setText(lastCharacter + " attacked " + enemySelected + " and did " + damage + " HP." );
        invalidAction.setText("");
      } catch (InvalidDecisionException e) {
        invalidAction.setText("You're not in Attack Phase");
      }
    });
    root.getChildren().add(attackEnemy4Button);

    // Enemies
    spriteEnemy1.setLayoutX(650);
    spriteEnemy1.setLayoutY(10);
    root.getChildren().add(spriteEnemy1);

    spriteEnemy2.setLayoutX(650 + 130);
    spriteEnemy2.setLayoutY(10);
    root.getChildren().add(spriteEnemy2);

    spriteEnemy3.setLayoutX(650 + 130*2);
    spriteEnemy3.setLayoutY(10);
    root.getChildren().add(spriteEnemy3);

    spriteEnemy4.setLayoutX(650 + 130*3);
    spriteEnemy4.setLayoutY(10);
    root.getChildren().add(spriteEnemy4);

    enemy1.setLayoutX(650);
    enemy2.setLayoutX(650+130);
    enemy3.setLayoutX(650+130*2);
    enemy4.setLayoutX(650+130*3);

    enemy1.setLayoutY(80);
    enemy2.setLayoutY(80);
    enemy3.setLayoutY(80);
    enemy4.setLayoutY(80);

    root.getChildren().add(enemy1);
    root.getChildren().add(enemy2);
    root.getChildren().add(enemy3);
    root.getChildren().add(enemy4);


    // Player Characters
    spriteCharacter1.setLayoutX(470);
    spriteCharacter1.setLayoutY(510);
    root.getChildren().add(spriteCharacter1);

    spriteCharacter2.setLayoutX(470 + 180);
    spriteCharacter2.setLayoutY(510);
    root.getChildren().add(spriteCharacter2);

    spriteCharacter3.setLayoutX(470 + 180*2);
    spriteCharacter3.setLayoutY(510);
    root.getChildren().add(spriteCharacter3);

    spriteCharacter4.setLayoutX(470 + 180*3);
    spriteCharacter4.setLayoutY(510);
    root.getChildren().add(spriteCharacter4);

    character1.setLayoutX(450);
    character2.setLayoutX(450+180);
    character3.setLayoutX(450+180*2);
    character4.setLayoutX(450+180*3);

    character1.setLayoutY(580);
    character2.setLayoutY(580);
    character3.setLayoutY(580);
    character4.setLayoutY(580);

    root.getChildren().add(character1);
    root.getChildren().add(character2);
    root.getChildren().add(character3);
    root.getChildren().add(character4);


    // animationTimer
    setupTimer();

    // Final Scenes
    Scene gameOverScene = new Scene(gameOverRoot, 612, 344);

    // Main Scene
    Scene scene = new Scene(root, 1150, 720);
    primaryStage.setScene(scene);

    // Show primaryStage
    primaryStage.show();
  }

  private void setupTimer() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if(!(controller.getPlayer().askWin() | controller.getCom().askWin())) {
        //Actual Phase and Character
        actualPhase.setText("Actual Phase: " + controller.getActualPhaseName());
        actualCharacter.setText("Actual Character: " + controller.getActualCharacterName());

        // Weapons update
        weapon1.setText(controller.getWeaponName(0) + "              " +
                controller.getWeaponAttack(0) + "              " +
                controller.getWeaponWeight(0));
        weapon1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        weapon1.setTextFill(Color.BLACK);
        weapon2.setText(controller.getWeaponName(1) + "              " +
                controller.getWeaponAttack(1) + "              " +
                controller.getWeaponWeight(1));
        weapon2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        weapon2.setTextFill(Color.BLACK);
        weapon3.setText(controller.getWeaponName(2) + "              " +
                controller.getWeaponAttack(2) + "              " +
                controller.getWeaponWeight(2));
        weapon3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        weapon3.setTextFill(Color.BLACK);
        weapon4.setText(controller.getWeaponName(3) + "              " +
                controller.getWeaponAttack(3) + "              " +
                controller.getWeaponWeight(3));
        weapon4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        weapon4.setTextFill(Color.BLACK);

        try {
          spriteWeapon1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                  controller.getWeaponName(0) + ".png")));
          spriteWeapon2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                  controller.getWeaponName(1) + ".png")));
          spriteWeapon3.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                  controller.getWeaponName(2) + ".png")));
          spriteWeapon4.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                  controller.getWeaponName(3) + ".png")));
        } catch (FileNotFoundException ignored) {
        }

        // Enemies Update
        if (controller.getEnemiesListSize() > 0) {
          enemy1.setText(controller.getEnemyName(0) + "\n \n" +
                  "HP: " + controller.getEnemyHP(0) + "\n" +
                  "Atk: " + controller.getEnemyAttack(0) + "\n" +
                  "Def: " + controller.getEnemyDefense(0));
          enemy1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
          enemy1.setTextFill(Color.BLACK);

          if (controller.getEnemiesListSize() > 1) {
            enemy2.setText(controller.getEnemyName(1) + "\n \n" +
                    "HP: " + controller.getEnemyHP(1) + "\n" +
                    "Atk: " + controller.getEnemyAttack(1) + "\n" +
                    "Def: " + controller.getEnemyDefense(1));
            enemy2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            enemy2.setTextFill(Color.BLACK);

            if (controller.getEnemiesListSize() > 2) {
              enemy3.setText(controller.getEnemyName(2) + "\n \n" +
                      "HP: " + controller.getEnemyHP(2) + "\n" +
                      "Atk: " + controller.getEnemyAttack(2) + "\n" +
                      "Def: " + controller.getEnemyDefense(2));
              enemy3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
              enemy3.setTextFill(Color.BLACK);

              if (controller.getEnemiesListSize() > 3) {
                enemy4.setText(controller.getEnemyName(3) + "\n \n" +
                        "HP: " + controller.getEnemyHP(3) + "\n" +
                        "Atk: " + controller.getEnemyAttack(3) + "\n" +
                        "Def: " + controller.getEnemyDefense(3));
                enemy4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
                enemy4.setTextFill(Color.BLACK);
              }
            }
          }
        }

        // Enemies not Visible
        if (controller.getEnemiesListSize() == 4) {
          try {
            spriteEnemy1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(0) + ".png")));
            spriteEnemy2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(1) + ".png")));
            spriteEnemy3.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(2) + ".png")));
            spriteEnemy4.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(3) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
        }
        if (controller.getEnemiesListSize() == 3) {
          try {
            spriteEnemy1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(0) + ".png")));
            spriteEnemy2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(1) + ".png")));
            spriteEnemy3.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(2) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
          enemy4.setVisible(false);
          spriteEnemy4.setVisible(false);
          attackEnemy4Button.setVisible(false);
        }
        if (controller.getEnemiesListSize() == 2) {
          try {
            spriteEnemy1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(0) + ".png")));
            spriteEnemy2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(1) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
          enemy3.setVisible(false);
          spriteEnemy3.setVisible(false);
          attackEnemy3Button.setVisible(false);
        }

        if (controller.getEnemiesListSize() == 1) {
          try {
            spriteEnemy1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getEnemyName(0) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
          enemy2.setVisible(false);
          spriteEnemy2.setVisible(false);
          attackEnemy2Button.setVisible(false);
        }

        if (controller.getEnemiesListSize() == 0) {
          spriteEnemy1.setVisible(false);
          attackEnemy1Button.setVisible(false);
          enemy1.setDisable(true);
          //WIN
        }

        // Characters Update
        if (controller.getPlayerListSize() > 0) {
          character1.setText(controller.getPlayerCharacterName(0) + "\n \n" +
                  "HP: " + controller.getPlayerCharacterHP(0) + "\n" +
                  "Atk: " + controller.getPlayerCharacterAttack(0) + "\n" +
                  "Def: " + controller.getPlayerCharacterDefense(0) + "\n" +
                  "Class: " + controller.getPlayerCharacterClass(0) + "\n" +
                  "Wpn: " + controller.getPlayerCharacterWeaponName(0));
          character1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
          character1.setTextFill(Color.BLACK);

          if (controller.getPlayerListSize() > 1) {
            character2.setText(controller.getPlayerCharacterName(1) + "\n \n" +
                    "HP: " + controller.getPlayerCharacterHP(1) + "\n" +
                    "Atk: " + controller.getPlayerCharacterAttack(1) + "\n" +
                    "Def: " + controller.getPlayerCharacterDefense(1) + "\n" +
                    "Class: " + controller.getPlayerCharacterClass(1) + "\n" +
                    "Wpn: " + controller.getPlayerCharacterWeaponName(1));
            character2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            character2.setTextFill(Color.BLACK);

            if (controller.getPlayerListSize() > 2) {
              character3.setText(controller.getPlayerCharacterName(2) + "\n \n" +
                      "HP: " + controller.getPlayerCharacterHP(2) + "\n" +
                      "Atk: " + controller.getPlayerCharacterAttack(2) + "\n" +
                      "Def: " + controller.getPlayerCharacterDefense(2) + "\n" +
                      "Class: " + controller.getPlayerCharacterClass(2) + "\n" +
                      "Wpn: " + controller.getPlayerCharacterWeaponName(2));
              character3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
              character3.setTextFill(Color.BLACK);

              if (controller.getPlayerListSize() > 3) {
                character4.setText(controller.getPlayerCharacterName(3) + "\n \n" +
                        "HP: " + controller.getPlayerCharacterHP(3) + "\n" +
                        "Atk: " + controller.getPlayerCharacterAttack(3) + "\n" +
                        "Def: " + controller.getPlayerCharacterDefense(3) + "\n" +
                        "Class: " + controller.getPlayerCharacterClass(3) + "\n" +
                        "Wpn: " + controller.getPlayerCharacterWeaponName(3));
                character4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
                character4.setTextFill(Color.BLACK);
              }
            }
          }
        }
        // Characters Not Visible
        if (controller.getPlayerListSize() == 4) {
          try {
            spriteCharacter1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(0) + ".png")));
            spriteCharacter2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(1) + ".png")));
            spriteCharacter3.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(2) + ".png")));
            spriteCharacter4.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(3) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
        }
        if (controller.getPlayerListSize() == 3) {
          try {
            spriteCharacter1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(0) + ".png")));
            spriteCharacter2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(1) + ".png")));
            spriteCharacter3.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(2) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
          character4.setVisible(false);
          spriteCharacter4.setVisible(false);
        }
        if (controller.getPlayerListSize() == 2) {
          try {
            spriteCharacter1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(0) + ".png")));
            spriteCharacter2.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(1) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
          character3.setVisible(false);
          spriteCharacter3.setVisible(false);
        }

        if (controller.getPlayerListSize() == 1) {
          try {
            spriteCharacter1.setImage(new Image(new FileInputStream(RESOURCE_PATH +
                    controller.getPlayerCharacterName(0) + ".png")));
          } catch (FileNotFoundException ignored) {
          }
          character2.setVisible(false);
          spriteCharacter2.setVisible(false);
        }

        if (controller.getPlayerListSize() == 0) {
          character1.setVisible(false);
          spriteCharacter1.setVisible(false);
        }

        if(controller.getActualCharacter() == null) {
          try {
            Thread.sleep(1500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          if (controller.getActualCharacter().isEnemy()) {
            String savedLastAction = lastAction.getText();

            lastAction.setText(controller.makeEnemyMove(lastAction.getText()));
            if (!savedLastAction.equals(lastAction.getText())) {
              preLastAction.setText(savedLastAction);
            }

            try {
              Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
          }
        }
      } else {
          character1.setVisible(false);
          character2.setVisible(false);
          character3.setVisible(false);
          character4.setVisible(false);
          enemy1.setVisible(false);
          enemy2.setVisible(false);
          enemy3.setVisible(false);
          enemy4.setVisible(false);
          weapon1.setVisible(false);
          weapon2.setVisible(false);
          weapon3.setVisible(false);
          weapon4.setVisible(false);
          attackEnemy4Button.setVisible(false);
          attackEnemy3Button.setVisible(false);
          attackEnemy2Button.setVisible(false);
          attackEnemy1Button.setVisible(false);
          actualCharacter.setVisible(false);
          actualPhase.setVisible(false);
          spriteWeapon1.setVisible(false);
          spriteWeapon2.setVisible(false);
          spriteWeapon3.setVisible(false);
          spriteWeapon4.setVisible(false);
          spriteCharacter1.setVisible(false);
          spriteCharacter2.setVisible(false);
          spriteCharacter3.setVisible(false);
          spriteCharacter4.setVisible(false);
          spriteEnemy1.setVisible(false);
          spriteEnemy2.setVisible(false);
          spriteEnemy3.setVisible(false);
          spriteEnemy4.setVisible(false);
          equipWeapon1Button.setVisible(false);
          equipWeapon2Button.setVisible(false);
          equipWeapon3Button.setVisible(false);
          equipWeapon4Button.setVisible(false);
          toAttackPhaseButton.setVisible(false);
          availableWeapons.setVisible(false);
          weaponAtk.setVisible(false);
          weaponSpd.setVisible(false);

          lastAction.setTextFill(Color.WHITE);
          preLastAction.setTextFill(Color.WHITE);
          preLastAction.setText("Last Action: ");

         try {
         background.setImage(
         (new Image(new FileInputStream(RESOURCE_PATH + "Game Over.png"))));

         } catch (FileNotFoundException ignored) {
         }

         if(controller.getCom().askWin()) { // You Lose
            finalLabel.setText("You Lose");
         } else {                           // You Win
           finalLabel.setText("You Win");
         }
         }
      }

    };
    timer.start();
  }
}

