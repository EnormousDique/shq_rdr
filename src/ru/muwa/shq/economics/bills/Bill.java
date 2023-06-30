package ru.muwa.shq.economics.bills;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Bill extends Item {
    @Override
    public Item copy() {
        return new Bill(total,week);
    }

    private static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "money\\bill.png"));
        } catch (IOException e) {
            System.out.println("failed to load bill image");
        }
    }

    private boolean isPayed,isSent;
    private int total;
    private int week;
    public Bill(int total, int week)
    {
        super(0,0,0.0,img);
        this.total=total;
        this.week = week;
        this.isSent = true;
        description = "Счет на сумму : " + total + " за неделю : " +week;


    }

    public static BufferedImage getImg() {
        return img;
    }

    public static void setImg(BufferedImage img) {
        Bill.img = img;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    private Bill(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        if(Player.get().money>=total)
        {
            Player.get().money = Player.get().money - total;
            isPayed=true;
            Inventory.getInstance().getItems().remove(this);
            Renderer.addMessage("Оплачен");
        }else{
            Renderer.addMessage("Не хватает денег на оплату счета.");
        }
    }
}
