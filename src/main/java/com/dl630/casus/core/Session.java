package com.dl630.casus.core;

import com.dl630.casus.Main;
import com.dl630.casus.gebruiker.Gebruiker;
import com.dl630.casus.scene.SceneFactory;
import com.dl630.casus.scene.SceneInterface;
import com.dl630.casus.scene.controller.SimpleController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Session {

    private Stage stage;
    private Gebruiker loggedInUser;

    private EventSubscriber eventSubscriber;

    public Session() {
        this.eventSubscriber = new EventSubscriber();
        setStage(new Stage());
    }

    private boolean alignmentFix;

    public Gebruiker getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Gebruiker loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void setScene(String scene) {
        SceneFactory sceneFactory = new SceneFactory();
        setSceneByController(sceneFactory.create(scene));
    }

    public void setScene(SceneInterface scene) {
        SimpleController controller = scene.getSceneController();
        System.out.println(new Scene(controller.scenePane));
        fixAlignment();
    }

    public void setSceneByController(SimpleController controller) {
        stage.setScene(new Scene(controller.scenePane));
        System.out.println(stage.getScene());
        controller.init();
        fixAlignment();
    }

    public void fixAlignment() {
        /* I don't know how, I don't know why, but for some reason unless we forcefully update the window size,
         * the root pane won't align itself with the window properly.
         */
        stage.setWidth(stage.getWidth() + 0.1 + (alignmentFix ? 0 : -0.2));
        stage.setHeight(stage.getHeight() + 0.1 + (alignmentFix ? 0 : -0.2));
        //Toolkit.getToolkit().requestNextPulse(); - would be better, but currently causes a crash so eh
    }

    public static Session getSessionFromScene(Scene scene) {
        System.out.println("load attempt");
        for (Session session : Main.getSessions()) {
            System.out.println(session.getStage().getScene());
            if (session.getStage().getScene().equals(scene)) {
                System.out.println("test");
                return session;
            }
        }

        return null;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setWidth(Main.DEFAULT_WIDTH);
        stage.setHeight(Main.DEFAULT_HEIGHT);
    }
    public Stage getStage() {
        return stage;
    }

    public EventSubscriber getEventSubscriber() {
        return eventSubscriber;
    }
}
