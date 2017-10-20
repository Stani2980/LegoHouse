/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stanislav
 */
public class Layer {

    List<HouseSide> sides = new ArrayList<>();

    public Layer() {
    }

    public void setSide(HouseSide side) {
        sides.add(side);
    }

    public List<HouseSide> getSides() {
        return sides;
    }

}
