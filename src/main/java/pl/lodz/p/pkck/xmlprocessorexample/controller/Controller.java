package pl.lodz.p.pkck.xmlprocessorexample.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {

    protected Stage stage = null;

    public void setStage(final Stage stage) {
        this.stage = stage;
    }
}
