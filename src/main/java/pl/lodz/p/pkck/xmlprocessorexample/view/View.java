package pl.lodz.p.pkck.xmlprocessorexample.view;

import javafx.scene.Scene;
import pl.lodz.p.pkck.xmlprocessorexample.controller.Controller;

public abstract class View {

    protected Scene scene;
    protected Controller controller;

    public Scene getScene() {
        return scene;
    }

    public Controller getController() {
        return controller;
    }

}
