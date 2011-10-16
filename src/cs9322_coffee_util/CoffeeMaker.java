/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package cs9322_coffee_util;

import cs9322_coffee_model.Coffee;
import org.w3c.dom.Node;

/**
 *
 * @author charles
 */
public class CoffeeMaker {
  public static void make(Coffee coffee, Node coffeeNode) {
    coffeeNode = coffeeNode.getFirstChild();
    coffee.setAddition(coffeeNode.getTextContent());
    coffeeNode = coffeeNode.getNextSibling();
    coffee.setCost(Float.parseFloat(coffeeNode.getTextContent()));
    coffeeNode = coffeeNode.getNextSibling();
    coffee.setDrink(coffeeNode.getTextContent());
    coffeeNode = coffeeNode.getNextSibling();
    coffee.setId(coffeeNode.getTextContent());
    coffeeNode = coffeeNode.getNextSibling();
    coffee.setSize(coffeeNode.getTextContent());
    coffeeNode = coffeeNode.getNextSibling();
    coffee.setStatusFlag(Boolean.parseBoolean(coffeeNode.getTextContent()));
  }
}
