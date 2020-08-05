package su.egorovna.coffee.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import su.egorovna.coffee.entity.ChecklinesEntity;
import su.egorovna.coffee.entity.GoodsEntity;
import su.egorovna.coffee.service.GoodsService;
import su.egorovna.coffee.ui.ButtonGrid;
import su.egorovna.coffee.ui.PayDialog;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private static final int COUNT_BY_PAGE = 12;

    private final GoodsService goodsService = new GoodsService();

    private final ObservableList<ChecklinesEntity> observableList = FXCollections.observableArrayList();

    @FXML
    private StackPane root;

    @FXML
    private Pagination menu;

    @FXML
    private ListView<ChecklinesEntity> shoppingCart;

    @FXML
    private Label count;

    @FXML
    private Label sum;

    @FXML
    private Button pay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        observableList.addListener((ListChangeListener<ChecklinesEntity>) c -> update());

        menu.setPageFactory(param -> {
            menu.setPageCount((int) Math.ceil((double) goodsService.findAll().size() / COUNT_BY_PAGE));
            List<GoodsEntity> entities = goodsService.findAllByPage(param, COUNT_BY_PAGE);
            ButtonGrid grid = new ButtonGrid(4, 0.6, 5, 5);
            grid.setPadding(new Insets(5));
            grid.getChildren().addAll(entities.stream().map(this::createButton).collect(Collectors.toList()));
            return grid;
        });

        pay.setOnAction(event -> {
            if (!observableList.isEmpty()) new PayDialog(root, observableList).show();
        });

        shoppingCart.setItems(observableList);
        shoppingCart.setCellFactory(new Callback<ListView<ChecklinesEntity>, ListCell<ChecklinesEntity>>() {
            @Override
            public ListCell<ChecklinesEntity> call(ListView<ChecklinesEntity> param) {
                return new ListCell<ChecklinesEntity>() {
                    @Override
                    protected void updateItem(ChecklinesEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Button delete = new Button();
                            delete.setMaxWidth(Integer.MAX_VALUE);
                            delete.setMaxHeight(Integer.MAX_VALUE);
                            delete.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
                            delete.setOnAction(event -> {
                                if (item.getQuantity() == 1) {
                                    observableList.remove(item);
                                } else {
                                    item.setQuantity(item.getQuantity() - 1);
                                }
                                update();
                            });
                            Label text = new Label(item.getGoodsEntity().getName() + " x" + item.getQuantity() + " " + item.getGoodsEntity().getCost().doubleValue() * item.getQuantity() + " \u20BD");
                            text.setMaxWidth(Integer.MAX_VALUE);
                            text.setMaxHeight(Integer.MAX_VALUE);
                            HBox.setHgrow(text, Priority.ALWAYS);
                            setText("");
                            setGraphic(new HBox(5, text, delete));
                        }
                    }
                };
            }
        });
        update();
    }

    private Node createButton(GoodsEntity entity) {
        Button button = new Button(entity.getName() + "\n" + entity.getCost() + " \u20BD");
        button.setWrapText(true);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setOnAction(event -> {
            ChecklinesEntity checklines = null;
            for (ChecklinesEntity c : observableList) {
                if (c.getGoodsEntity().equals(entity)) checklines = c;
            }
            if (checklines == null) {
                checklines = new ChecklinesEntity();
                checklines.setQuantity(1);
                checklines.setGoodsEntity(entity);
                observableList.add(checklines);
            } else {
                checklines.setQuantity(checklines.getQuantity() + 1);
            }
            update();
        });
        return button;
    }

    private void update() {
        count.setText("Выбрано: " + observableList.stream().mapToInt(ChecklinesEntity::getQuantity).sum());
        sum.setText("Итого: " + observableList.stream().mapToDouble(value -> value.getQuantity() * value.getGoodsEntity().getCost().doubleValue()).sum() + " \u20BD");
        shoppingCart.refresh();
    }

}
