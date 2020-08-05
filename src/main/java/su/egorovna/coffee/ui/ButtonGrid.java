package su.egorovna.coffee.ui;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ButtonGrid extends Pane {

    private int columnCount;
    private double heightToWidthRatio;
    private double HGap;
    private double VGap;

    public ButtonGrid(int columnCount, double heightToWidthRatio, double HGap, double VGap) {
        this.columnCount = columnCount;
        this.heightToWidthRatio = heightToWidthRatio;
        this.HGap = HGap;
        this.VGap = VGap;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public double getHeightToWidthRatio() {
        return heightToWidthRatio;
    }

    public void setHeightToWidthRatio(double heightToWidthRatio) {
        this.heightToWidthRatio = heightToWidthRatio;
    }

    public double getHGap() {
        return HGap;
    }

    public void setHGap(double HGap) {
        this.HGap = HGap;
    }

    public double getVGap() {
        return VGap;
    }

    public void setVGap(double VGap) {
        this.VGap = VGap;
    }

    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected void layoutChildren() {
        double left = getInsets().getLeft();
        double top = getInsets().getTop();

        double tileWidth = calculateTileWidth(getWidth());
        double tileHeight = calculateTileHeight(getWidth());

        ObservableList<Node> children = getChildren();
        double currentX = left;
        double currentY = top;
        for (int idx = 0; idx < children.size(); idx++) {
            if (idx > 0 && idx % columnCount == 0) {
                currentX = left;
                currentY = currentY + tileHeight;
            }
            if (idx > 0 && (idx + 1) % columnCount == 0) {
                children.get(idx).resize(tileWidth, tileHeight - VGap);
            } else {
                children.get(idx).resize(tileWidth - HGap, tileHeight - VGap);
            }
            children.get(idx).relocate(currentX, currentY);
            currentX = currentX + tileWidth;
        }
    }

    @Override
    protected double computePrefWidth(double height) {
        double w = 0;
        for (int idx = 0; idx < columnCount; idx++) {
            Node node = getChildren().get(idx);
            w += node.prefWidth(-1);
        }
        return getInsets().getLeft() + w + getInsets().getRight();
    }

    @Override
    protected double computePrefHeight(double width) {
        double h = calculateTileHeight(width) * getRowCount();
        return getInsets().getTop() + h + getInsets().getBottom();
    }

    private double calculateTileHeight(double width) {
        return calculateTileWidth(width) * heightToWidthRatio;
    }

    private double calculateTileWidth(double width) {
        return (-getInsets().getLeft() + width - getInsets().getRight()) / columnCount;
    }

    private int getRowCount() {
        return getChildren().size() / columnCount;
    }
}