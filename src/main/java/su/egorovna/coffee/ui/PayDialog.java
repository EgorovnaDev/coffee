package su.egorovna.coffee.ui;

import com.jfoenix.controls.JFXDialog;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import su.egorovna.coffee.entity.ChecklinesEntity;
import su.egorovna.coffee.entity.ChecksEntity;
import su.egorovna.coffee.service.ChecklinesService;
import su.egorovna.coffee.service.ChecksService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PayDialog extends JFXDialog {

    private final ChecksService checksService = new ChecksService();
    private final ChecklinesService checklinesService = new ChecklinesService();

    private double totalSum = 0;

    @FXML
    private Label payText;

    @FXML
    private TextField payField;

    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    public PayDialog(StackPane dialogContainer, ObservableList<ChecklinesEntity> data) {
        super(dialogContainer, null, DialogTransition.CENTER, false);
        setContent(createContent(data));
        totalSum = data.stream().mapToDouble(value -> value.getQuantity() * value.getGoodsEntity().getCost().doubleValue()).sum();
        payText.setText("Итого к оплате " + totalSum + " \u20BD");
        payField.setPromptText("Ввевиде сумму перевода");
        cancel.setOnAction(event -> close());
        ok.setOnAction(event -> {
            if (totalSum == Double.parseDouble(payField.getText())) {
                ChecksEntity checksEntity = new ChecksEntity();
                checksEntity.setSum(totalSum);
                checksEntity.setDate(LocalDate.now());
                checksEntity.setTime(LocalTime.now());
                checksService.save(checksEntity);
                for (ChecklinesEntity e : data) {
                    e.setChecksEntity(checksEntity);
                    checklinesService.save(e);
                }
                data.clear();
                close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Суммы не совпадают");
                alert.showAndWait();
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        payField.appendText(((Button) event.getSource()).getText());
    }

    @FXML
    void point(ActionEvent event) {
        if (!payField.getText().contains(".")) payField.appendText(".");
    }

    private Region createContent(ObservableList<ChecklinesEntity> data) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payView.fxml"));
        loader.setController(this);
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
