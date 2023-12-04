package dad.login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        LoginController controller = new LoginController();

        Scene scene = new Scene(controller.getView());

        primaryStage.setTitle("Login.fxml");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}