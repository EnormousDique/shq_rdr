package ru.muwa.shq.zones;

import javax.swing.*;

public class GifSceneZone extends GameZone {
    public JLabel SceneWindow;
    private boolean isGifSceneAuto, isGifSceneActive;
    public boolean isGifSceneAuto() {
        return isGifSceneAuto;
    }

    public void setGifSceneAuto(boolean auto) {
        isGifSceneAuto = auto;
    }
    public boolean isGifSceneActive() {
        return isGifSceneActive;
    }
    public void setGifSceneActive(boolean active) {
        isGifSceneActive = active;
    }
    public GifSceneZone(int x, int y, int width, int height, boolean isAuto) {
        super(x, y, width, height);
        this.isGifSceneAuto = isAuto;
    }
}
