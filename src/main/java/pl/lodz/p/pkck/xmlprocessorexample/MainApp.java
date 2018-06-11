package pl.lodz.p.pkck.xmlprocessorexample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.view.MenuView;
import pl.lodz.p.pkck.xmlprocessorexample.view.View;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    private static View menuView;

    public static void main(final String[] args) {
        launch(args);
    }

    public static View getMenuView() {
        return menuView;
    }

    public void start(final Stage primaryStage) {
        try {
            menuView = new MenuView("/views/MenuScene.fxml");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        menuView.getController().setStage(primaryStage);

        primaryStage.setTitle("XML processor example");
        primaryStage.setScene(menuView.getScene());
        primaryStage.show();
    }

}