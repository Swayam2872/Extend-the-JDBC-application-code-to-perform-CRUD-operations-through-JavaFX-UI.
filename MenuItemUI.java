import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class MenuItemUI {

    public void show() {
        Stage stage = new Stage();

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField price = new TextField();
        price.setPromptText("Price");

        TextField resId = new TextField();
        resId.setPromptText("Restaurant ID");

        TextArea output = new TextArea();

        Button insert = new Button("Insert");
        Button select = new Button("Select");
        Button update = new Button("Update");
        Button delete = new Button("Delete");

        // INSERT
        insert.setOnAction(e -> {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO MenuItem(Name, Price, ResId) VALUES (?,?,?)");
                ps.setString(1, name.getText());
                ps.setInt(2, Integer.parseInt(price.getText()));
                ps.setInt(3, Integer.parseInt(resId.getText()));
                ps.executeUpdate();
                output.setText("Inserted successfully");
            } catch (Exception ex) {
                output.setText(ex.getMessage());
            }
        });

        // SELECT
        select.setOnAction(e -> {
            try (Connection con = DBConnection.getConnection()) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM MenuItem");

                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append(rs.getInt(1)).append("\t")
                      .append(rs.getString(2)).append("\t")
                      .append(rs.getInt(3)).append("\t")
                      .append(rs.getInt(4)).append("\n");
                }
                output.setText(sb.toString());
            } catch (Exception ex) {
                output.setText(ex.getMessage());
            }
        });

        // UPDATE
        update.setOnAction(e -> {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE MenuItem SET Price=? WHERE Name=?");
                ps.setInt(1, Integer.parseInt(price.getText()));
                ps.setString(2, name.getText());
                ps.executeUpdate();
                output.setText("Updated successfully");
            } catch (Exception ex) {
                output.setText(ex.getMessage());
            }
        });

        // DELETE
        delete.setOnAction(e -> {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM MenuItem WHERE Name=?");
                ps.setString(1, name.getText());
                ps.executeUpdate();
                output.setText("Deleted successfully");
            } catch (Exception ex) {
                output.setText(ex.getMessage());
            }
        });

        VBox root = new VBox(10, name, price, resId, insert, select, update, delete, output);
        stage.setScene(new Scene(root, 400, 450));
        stage.setTitle("MenuItem CRUD");
        stage.show();
    }
}