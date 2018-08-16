/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.BorderLayout;
import java.awt.Component;

/**
 *
 * @author Adrián Scott
 */
public class SpinnerLayout extends BorderLayout {
  @Override public void addLayoutComponent(Component comp, Object constraints) {
    if("Editor".equals(constraints)) {
      constraints = "Center";
    } else if("Next".equals(constraints)) {
      constraints = "North";
    } else if("Previous".equals(constraints)) {
      constraints = "South";
    }
    super.addLayoutComponent(comp, constraints);
  }
}
