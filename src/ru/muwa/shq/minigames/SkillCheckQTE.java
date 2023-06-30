package ru.muwa.shq.minigames;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SkillCheckQTE extends MiniGame {
    public static int getNeedlePosition() {
        return needlePosition;
    }
    private static int needlePosition;
    private static int needleDirection; // Направление движения иглы
    private static Timer skillCheckTimer;
    public SkillCheckQTE() {
        startSkillCheck();
    }
    public static void startSkillCheck() {
        needlePosition = 0;
        needleDirection = 15; // Скорость движения иглы
        skillCheckTimer = new Timer(50, e -> {
            needlePosition += needleDirection;
            if (needlePosition >= 200) {
                needleDirection = -needleDirection; // Меняем направление на обратное
            } else if (needlePosition <= 0) {
                needleDirection = -needleDirection; // Меняем направление на вправо
            }
        });
        skillCheckTimer.start();
    }
    public static void checkSkillCheck() {
        int greenZoneStart = 40;
        int greenZoneEnd = 60;
        if (needlePosition >= greenZoneStart && needlePosition <= greenZoneEnd) {
            System.out.println("МАЛАДЕЦ");
            Engine.pause = false;
            MiniGameHUD.currentMiniGame = null;
            skillCheckTimer.stop();
        } else {
            System.out.println("ЛОХ");
        }
    }
}