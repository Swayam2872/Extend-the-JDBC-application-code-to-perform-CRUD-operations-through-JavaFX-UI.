import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    public void start(Stage stage) {
        Button restaurantBtn = new Button("Restaurant CRUD");
        Button menuItemBtn = new Button("MenuItem CRUD");

        restaurantBtn.setOnAction(e -> new RestaurantMenuUI().show());
        menuItemBtn.setOnAction(e -> new MenuItemUI().show());

        VBox root = new VBox(15, restaurantBtn, menuItemBtn);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}