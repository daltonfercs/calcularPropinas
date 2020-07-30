/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dalstrom.propinas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 *
 * @author dalton
 */
public class FXMLPrincipalController implements Initializable {

    private static final NumberFormat moneda
            = NumberFormat.getCurrencyInstance();

    private static final NumberFormat porcentaje
            = NumberFormat.getPercentInstance();

    private BigDecimal porcentajePropina = new BigDecimal(0.15);

    @FXML
    Label lblPorcentaje;

    @FXML
    TextField txtCantidad;

    @FXML
    TextField txtPropina;

    @FXML
    TextField txtTotal;

    @FXML
    Slider sldPorcentaje;

    @FXML
    Button btnCalcular;

    @FXML
    private void Calcular(ActionEvent envt) {

        try {
            BigDecimal cantidad = new BigDecimal(txtCantidad.getText());
            BigDecimal propina = cantidad.multiply(porcentajePropina);
            BigDecimal total = cantidad.add(propina);

            txtPropina.setText(moneda.format(propina));
            txtTotal.setText(moneda.format(total));
        } catch (Exception e) {
            
            txtCantidad.setText("ingrese un valor numerico");
            txtCantidad.selectAll();
            txtCantidad.requestFocus();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       moneda.setRoundingMode(RoundingMode.HALF_UP);
       sldPorcentaje.valueProperty().addListener(
               new ChangeListener<Number>(){
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               porcentajePropina = BigDecimal.valueOf(newValue.intValue()/100.0);
               lblPorcentaje.setText(porcentaje.format(porcentajePropina));
           }
    }
       );
    }

}
