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
public class BlockFactory {

    public static List<Layer> getBlocks(int length, int width, int layers) {

        List<Layer> blocks = new ArrayList<>();

        for (int i = 1; i <= layers; i++) {
            blocks.add(calculateLayer(length, width, i));
        }
        return blocks;
    }

    private static Layer calculateLayer(int length, int width, int layerNr) {
        Layer layer = new Layer();
        boolean door;
        boolean window;

        if (layerNr == 1 || layerNr == 2 || layerNr == 3) {
            door = true;
        } else {
            door = false;
        }
        if (layerNr == 2 || layerNr == 3) {
            window = true;
        } else {
            window = false;
        }

        // Frontside == 1, Backside == 2, Sides == 0
        layer.setSide(createSide(length, layerNr, door, window, 1));
        layer.setSide(createSide(length, layerNr, door, window, 2));
        layer.setSide(createSide(width, layerNr, door, window, 0));
        layer.setSide(createSide(width, layerNr, door, window, 0));
        return layer;
    }

    private static HouseSide createSide(int size, int layerNr, boolean door, boolean window, int type) {

        // Checks to that every other layer is in bonding.
        if (layerNr % 2 > 0 && type != 0) {
            size += -4;
        } else if (layerNr % 2 == 0 && type == 0) {
            size += -4;
        }

        // removes space for door
        if (door && type == 1) {
            size += -2;
        }

        // removes space for window
        if (window && type == 2) {
            size += -2;
        }

        int bricks4x4 = size / 4;
        int bricks2x2 = (size % 4) / 2;
        int bricks1x2 = (size % 4) % 2;

// special occasion for smallest possible house
        if ((layerNr == 1 || layerNr == 2 || layerNr == 3) && type == 1 && bricks4x4 == 1 && bricks2x2 == 0) {
            bricks4x4 = 0;
            bricks2x2 += 2;
        }

// special occasion for smallest possible house
        if ((layerNr == 2 || layerNr == 3) && type == 2 && bricks4x4 == 1  && bricks2x2 == 0) {
            bricks4x4 = 0;
            bricks2x2 += 2;
        }

        return new HouseSide(bricks4x4, bricks2x2, bricks1x2);
    }

}
