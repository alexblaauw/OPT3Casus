package com.dl630.casus.core;

import com.dl630.casus.scene.controller.ValueReader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HuurProperty<T> {
    private T value;
    private final ValueReader read;
    private final TextField textField;

    private final Label label;

    public HuurProperty(ValueReader read) {
        this.textField = new TextField();
        this.label = new Label();
        this.read = read;
    }
    public HuurProperty(T value, ValueReader read) {
        this(read);
        label.setText(value.toString());
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        label.setText(value.toString());
    }

    public void read() {
        read.readValue(this, textField);
    }

    public static ValueReader getStringReader() {
        return (huurProperty, textField) -> huurProperty.set(textField.getText());
    }

    public static ValueReader getDoubleReader() {
        return (huurProperty, textField) -> {
            System.out.println("hello");
            huurProperty.set(Double.parseDouble(textField.getText()));
        };
    }

    public TextField getTextField() {
        return textField;
    }

    public Node getValueNode(boolean edit) {
        return edit ? textField : label;
    }
}
