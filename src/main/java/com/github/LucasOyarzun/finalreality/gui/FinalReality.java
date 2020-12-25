package com.github.LucasOyarzun.finalreality.gui;

import com.github.LucasOyarzun.finalreality.controller.GameController;
import com.github.LucasOyarzun.finalreality.model.Computer;
import com.github.LucasOyarzun.finalreality.model.Player;
import com.github.LucasOyarzun.finalreality.model.character.Enemy;
import com.github.LucasOyarzun.finalreality.model.character.ICharacter;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Engineer;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Knight;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.Thief;
import com.github.LucasOyarzun.finalreality.model.character.player.classes.WhiteMage;
import com.github.LucasOyarzun.finalreality.model.weapon.classes.*;
import com.github.LucasOyarzun.finalreality.phases.InvalidTransitionException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public class FinalReality extends Application {
  private GameController controller = new GameController();
  private BlockingQueue<ICharacter> turns = controller.getTurns();
  private Player player = controller.getPlayer();
  private Computer com = controller.getCom();

  private static final String RESOURCE_PATH = "src/main/resources/";

  private final Knight adelbert = new Knight("Adelbert", 200, 30, turns);
  private final WhiteMage eiko = new WhiteMage("Eiko", 100, 10, 80, turns);
  private final Knight zidane = new Knight("Zidane", 100, 15, turns);
  private final Engineer cid = new Engineer("Cid", 130, 20, turns);

  private final Enemy devil1 = new Enemy("Devil", 60, 25, 32, 40, turns);
  private final Enemy devil2 = new Enemy("Devil", 60, 25, 42, 40, turns);
  private final Enemy ent = new Enemy("Ent", 200, 40, 60, 52, turns);
  private final Enemy assassin = new Enemy("Assassin", 80, 200, 32, 80, turns);

  private final Axe testAxe = new Axe("Big Axe", 60, 40);
  private final Staff testStaff = new Staff("Magic Staff", 10, 10, 100);
  private final Sword testSword = new Sword("Dragon Sword", 50, 35);
  private final Bow testBow = new Bow("Lightning Bow", 15, 20);
  private final Knife testKnife = new Knife("Little Knife", 20, 15);


  private final Label failedStartAttack = new Label("");
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



  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws InterruptedException, FileNotFoundException {

    controller.addPlayerCharacter(adelbert);
    controller.addPlayerCharacter(eiko);
    controller.addPlayerCharacter(zidane);
    controller.addPlayerCharacter(cid);

    controller.addWeapon(testAxe);
    controller.addWeapon(testStaff);
    controller.addWeapon(testSword);
    controller.addWeapon(testBow);
    controller.addWeapon(testKnife);
    controller.addWeapon(testSword);
    controller.addWeapon(testKnife);
    controller.addWeapon(testStaff);

    controller.equipWeapontoCharacter(testSword, adelbert);
    controller.equipWeapontoCharacter(testStaff, eiko);
    controller.equipWeapontoCharacter(testKnife, zidane);
    controller.equipWeapontoCharacter(testAxe, cid);

    controller.addEnemy(devil1);
    controller.addEnemy(devil2);
    controller.addEnemy(ent);
    controller.addEnemy(assassin);

    controller.startGame();

    FileInputStream enemy1Image = new FileInputStream(RESOURCE_PATH + controller.getEnemyName(0) + ".png");
    FileInputStream enemy2Image = new FileInputStream(RESOURCE_PATH + controller.getEnemyName(1) + ".png");
    FileInputStream enemy3Image = new FileInputStream(RESOURCE_PATH + controller.getEnemyName(2) + ".png");
    FileInputStream enemy4Image = new FileInputStream(RESOURCE_PATH + controller.getEnemyName(3) + ".png");
    var spriteEnemy1 = new ImageView(new Image(enemy1Image));
    var spriteEnemy2 = new ImageView(new Image(enemy2Image));
    var spriteEnemy3 = new ImageView(new Image(enemy3Image));
    var spriteEnemy4 = new ImageView(new Image(enemy4Image));

    FileInputStream weapon1Image = new FileInputStream(RESOURCE_PATH + controller.getWeaponName(0)+ ".png");
    FileInputStream weapon2Image = new FileInputStream(RESOURCE_PATH + controller.getWeaponName(1) + ".png");
    FileInputStream weapon3Image = new FileInputStream(RESOURCE_PATH + controller.getWeaponName(2) + ".png");
    FileInputStream weapon4Image = new FileInputStream(RESOURCE_PATH + controller.getWeaponName(3) + ".png");
    var spriteWeapon1 = new ImageView(new Image(weapon1Image));
    var spriteWeapon2 = new ImageView(new Image(weapon2Image));
    var spriteWeapon3 = new ImageView(new Image(weapon3Image));
    var spriteWeapon4 = new ImageView(new Image(weapon4Image));

    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(false);
    Group root = new Group();

    // Back Ground
    var background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);
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

    Label availableWeapons = new Label("Available weapons ");
    availableWeapons.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    availableWeapons.setTextFill(Color.BLACK);
    availableWeapons.setLayoutX(20);
    availableWeapons.setLayoutY(300);
    root.getChildren().add(availableWeapons);

    Label weaponAtk = new Label("Atk: ");
    weaponAtk.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    weaponAtk.setTextFill(Color.BLACK);
    weaponAtk.setLayoutX(200);
    weaponAtk.setLayoutY(320);
    root.getChildren().add(weaponAtk);

    Label weaponSpd = new Label("Weight: ");
    weaponSpd.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    weaponSpd.setTextFill(Color.BLACK);
    weaponSpd.setLayoutX(250);
    weaponSpd.setLayoutY(320);
    root.getChildren().add(weaponSpd);

    Button equipWeapon1Button = new Button("Equip");
    equipWeapon1Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon1Button.setLayoutX(300);
    equipWeapon1Button.setLayoutY(360);
    equipWeapon1Button.setOnAction(event -> controller.changeWeapon(controller.getPlayerInventory().get(0)));
    equipWeapon1Button.setVisible(true);
    root.getChildren().add(equipWeapon1Button);

    Button equipWeapon2Button = new Button("Equip");
    equipWeapon2Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon2Button.setLayoutX(300);
    equipWeapon2Button.setLayoutY(360+100);
    equipWeapon2Button.setOnAction(event -> controller.changeWeapon(controller.getPlayerInventory().get(0)));
    equipWeapon2Button.setVisible(true);
    root.getChildren().add(equipWeapon2Button);

    Button equipWeapon3Button = new Button("Equip");
    equipWeapon3Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon3Button.setLayoutX(300);
    equipWeapon3Button.setLayoutY(360+100*2);
    equipWeapon3Button.setOnAction(event -> controller.changeWeapon(controller.getPlayerInventory().get(0)));
    equipWeapon3Button.setVisible(true);
    root.getChildren().add(equipWeapon3Button);

    Button equipWeapon4Button = new Button("Equip");
    equipWeapon4Button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    equipWeapon4Button.setLayoutX(300);
    equipWeapon4Button.setLayoutY(360+100*3);
    equipWeapon4Button.setOnAction(event -> controller.changeWeapon(controller.getPlayerInventory().get(0)));
    root.getChildren().add(equipWeapon4Button);

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

    Button attackEnemy1Button = new Button("Attack");
    attackEnemy1Button.setLayoutX(650);
    attackEnemy1Button.setLayoutY(200);
    attackEnemy1Button.setOnAction(event -> controller.tryToAttack(controller.getEnemy(0)));
    root.getChildren().add(attackEnemy1Button);

    Button attackEnemy2Button = new Button("Attack");
    attackEnemy2Button.setLayoutX(650 +130);
    attackEnemy2Button.setLayoutY(200);
    attackEnemy2Button.setOnAction(event -> controller.tryToAttack(controller.getEnemy(0)));
    root.getChildren().add(attackEnemy2Button);

    Button attackEnemy3Button = new Button("Attack");
    attackEnemy3Button.setLayoutX(650 +130*2);
    attackEnemy3Button.setLayoutY(200);
    attackEnemy3Button.setOnAction(event -> controller.tryToAttack(controller.getEnemy(0)));
    root.getChildren().add(attackEnemy3Button);

    Button attackEnemy4Button = new Button("Attack");
    attackEnemy4Button.setLayoutX(650 +130*3);
    attackEnemy4Button.setLayoutY(200);
    attackEnemy4Button.setOnAction(event -> controller.tryToAttack(controller.getEnemy(0)));
    root.getChildren().add(attackEnemy4Button);

    if(controller.getEnemiesListSize()>0) {
      spriteEnemy1.setLayoutX(650);
      spriteEnemy1.setLayoutY(10);
      root.getChildren().add(spriteEnemy1);

      enemy1.setText(controller.getEnemyName(0) + "\n \n" +
                     "HP: " + controller.getEnemyHP(0) + "\n" +
                     "Atk: " + controller.getEnemyAttack(0) + "\n" +
                     "Def: "  + controller.getEnemyDefense(0));
      enemy1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
      enemy1.setTextFill(Color.BLACK);

      if(controller.getEnemiesListSize()>1) {
        spriteEnemy2.setLayoutX(650 + 130);
        spriteEnemy2.setLayoutY(10);
        root.getChildren().add(spriteEnemy2);

        enemy2.setText(controller.getEnemyName(1) + "\n \n" +
                       "HP: " + controller.getEnemyHP(1) + "\n" +
                       "Atk: " + controller.getEnemyAttack(1) + "\n" +
                       "Def: "  + controller.getEnemyDefense(1));
        enemy2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        enemy2.setTextFill(Color.BLACK);

        if(controller.getEnemiesListSize()>2) {
          spriteEnemy3.setLayoutX(650 + 130*2);
          spriteEnemy3.setLayoutY(10);
          root.getChildren().add(spriteEnemy3);
          enemy3.setText(controller.getEnemyName(2) + "\n \n" +
                         "HP: " + controller.getEnemyHP(2) + "\n" +
                         "Atk: " + controller.getEnemyAttack(2) + "\n" +
                         "Def: "  + controller.getEnemyDefense(2));
          enemy3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
          enemy3.setTextFill(Color.BLACK);

          if(controller.getEnemiesListSize()>3) {
            spriteEnemy4.setLayoutX(650 + 130*3);
            spriteEnemy4.setLayoutY(10);
            root.getChildren().add(spriteEnemy4);

            enemy4.setText(controller.getEnemyName(3) + "\n \n" +
                           "HP: " + controller.getEnemyHP(3) + "\n" +
                           "Atk: " + controller.getEnemyAttack(3) + "\n" +
                           "Def: "  + controller.getEnemyDefense(3));
            enemy4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            enemy4.setTextFill(Color.BLACK);
          }
        }
      }
    }

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


    //playerCharacters
    for (int i=0; i < controller.getPlayerListSize(); i++) {
      //Names
      Label playerCharacterName = new Label(controller.getPlayerCharacterName(i));
      playerCharacterName.setLayoutX(600 + 150*i);
      playerCharacterName.setLayoutY(500);
      root.getChildren().add(playerCharacterName);

      //HP
      Label playerCharacterHP = new Label("HP: " + controller.getPlayerCharacterHP(i));
      playerCharacterHP.setLayoutX(600 + 150*i);
      playerCharacterHP.setLayoutY(520);
      root.getChildren().add(playerCharacterHP);

      //Attack
      Label playerCharacterAttack = new Label("Atk: " + controller.getPlayerCharacterAttack(i));
      playerCharacterAttack.setLayoutX(600 + 150*i);
      playerCharacterAttack.setLayoutY(540);
      root.getChildren().add(playerCharacterAttack);

      //Defense
      Label playerCharacterDefense = new Label("Def: " + controller.getPlayerCharacterDefense(i));
      playerCharacterDefense.setLayoutX(600 + 150*i);
      playerCharacterDefense.setLayoutY(560);
      root.getChildren().add(playerCharacterDefense);

      //EquipedWeapon
      Label playerCharacterWeapon = new Label("Wpn: " + controller.getPlayerCharacterWeaponName(i));
      playerCharacterWeapon.setLayoutX(600 + 150*i);
      playerCharacterWeapon.setLayoutY(580);
      root.getChildren().add(playerCharacterWeapon);

      //ClassName
      Label playerCharacterClass = new Label("Class: " + controller.getPlayerCharacterClass(i));
      playerCharacterClass.setLayoutX(600 + 150*i);
      playerCharacterClass.setLayoutY(600);
      root.getChildren().add(playerCharacterClass);
    }

    //ToAttackPhaseButton
    Button toAttackPhaseButton = new Button("Change to AttackPhase");
    toAttackPhaseButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    toAttackPhaseButton.setLayoutX(700);
    toAttackPhaseButton.setLayoutY(450);
    toAttackPhaseButton.setOnAction(event -> {
      try {
        controller.tryToStartAttack();
        failedStartAttack.setText("");
      } catch (InvalidTransitionException e) {
        failedStartAttack.setText("Can´t Start Attack now");
        failedStartAttack.setLayoutX(50);
        failedStartAttack.setLayoutY(200);
        root.getChildren().add(failedStartAttack);
      }
    });
    root.getChildren().add(toAttackPhaseButton);

    // animationTimer
    setupTimer();
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
        actualPhase.setText("Actual Phase: " + controller.getActualPhaseName());
        actualCharacter.setText("Actual Character: " + controller.getActualCharacterName());
        //controller.tryToPick();
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




      }
    };
    timer.start();
  }
}