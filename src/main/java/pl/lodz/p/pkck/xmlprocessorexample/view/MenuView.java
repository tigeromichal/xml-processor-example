package pl.lodz.p.pkck.xmlprocessorexample.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuView extends View {

    public MenuView(final String loaderSourcePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(MenuView.class.getResource(loaderSourcePath));
        Parent graphRoot = loader.load();
        scene = new Scene(graphRoot);
        controller = loader.getController();
    }

}
