/*
 * The MIT License
 *
 * Copyright 2012 Manuel Gauto.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.manuwebdev.grevert.util;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 *
 * @author Manuel Gauto
 */
public class BlockArrayUtils {

    /**
     * Counts how many blocks of the specified type are
     * in the ArrayList
     * @param blocks ArrayList to search
     * @param material Material to search for
     * @return number of blocks of specified type
     */
    public static int countBlocksofType(ArrayList<Block> blocks, Material material) {
        int total = 0;
        for (int i = 0; i < blocks.size(); i++) {
            Block tocheck = blocks.get(i);
            if (tocheck.getType().equals(material)) {
                total++;
            }
        }
        return total;
    }

    /**
     * Gets the types of blocks that are in the ArrayList
     * @param blocks ArrayList to search
     * @return Materials in ArrayList
     */
    public static ArrayList<Material> getBlockTypesInArrayList(ArrayList<Block> blocks) {
        ArrayList<Material> materials = new ArrayList<Material>();
        for (int i = 0; i < blocks.size(); i++) {
            Block tocheck = blocks.get(i);
            boolean alreadylisted=false;
            for (int ii = 0; ii < materials.size(); ii++) {
                if(tocheck.getType().equals(materials.get(ii))) alreadylisted=true;
            }
            if(alreadylisted==false){
                materials.add(tocheck.getType());
            }
        }
        return materials;
    }
}
