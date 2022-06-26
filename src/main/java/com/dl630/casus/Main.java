package com.dl630.casus;

import com.dl630.casus.core.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.util.ArrayList;

public class Main extends Application {
    public static final String RESOURCE_ROOT_COMPLETE = "src/main/resources/";
    public static final Integer DEFAULT_WIDTH = 1060;
    public static final Integer DEFAULT_HEIGHT = 720;

    private static ArrayList<Session> sessions = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage root) throws Exception {
        Session session = startSession();
        session.setStage(root);
        session.setScene("Login");
        root.show();
        //ScenicView.show(root.getScene());
    }

    public static Session startSession() {
        Session session = new Session();
        sessions.add(session);
        session.getStage().setOnCloseRequest(event -> {
            sessions.remove(session);
        });
        return session;
    }

    public static ArrayList<Session> getSessions() {
        return new ArrayList<>(sessions);
    }
}
