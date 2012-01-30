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
package com.manuwebdev.grevert.WorldEdit;

import com.manuwebdev.grevert.GRevert;
import com.manuwebdev.grevert.util.BlockArrayUtils;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 *
 * @author Manuel Gauto
 */
public class WorldEditInterface {

    /**
     * Represents the plugin
     */
    GRevert plugin;
    /**
     * Represents WorldEditPlugin
     */
    WorldEditPlugin WorldEdit;
    /**
     * Represents WorldEditAPI
     */
    WorldEditAPI api;

    /**
     *
     * @param Plugin
     */
    public WorldEditInterface(GRevert plugin) {
        this.plugin = plugin;
        this.WorldEdit = plugin.getWorldEdit();
        api = new WorldEditAPI(WorldEdit);
    }

    /**
     * Gets the specified player's LocalSession
     *
     * @param p player to get session for
     * @return Player's session
     */
    public LocalSession getPlayerSession(Player p) {
        return api.getSession(p);
    }

    /**
     * Returns blocks in selection or empty ArrayList if selection is empty or incomplete
     * @param p player to get selection from
     * @return  Blocks in Selection as ArrayList
     */
    public ArrayList<Block> getSelectedBlocks(Player p) {
        //Session: The basis of WorldEdit Operations
        LocalSession ls = getPlayerSession(p);
        //Get world selection is in
        LocalWorld world = ls.getSelectionWorld();
        //Make ArrayList for blocks in region
        ArrayList<Block> blocks = new ArrayList<Block>();
        //Selected Region
        Region rg = null;

        //Get region is complete
        try {
            rg = ls.getSelection(world);
        } catch (IncompleteRegionException ex) {
            //Region was not selected completely
            p.sendMessage(plugin.MessageColor + "Please Complete Your Region");
        }
        if (rg != null) {
            //Blocks in Selection
            Iterator RegionIterator = rg.iterator();
            //Convert Iterator to ArrayList
            while (RegionIterator.hasNext()) {
                Object element = RegionIterator.next();
                Block toadd=(Block)element;
                blocks.add(toadd);
            }
            return blocks;
        }
        else{
            return new ArrayList<Block>();
        }
    }
    
    /**
     * Prints out the block types and their counts
     * @param p Player to send messages to
     * @param blocks Blocks to print
     */
    public void printSelectionBlockTypes(Player p, ArrayList<Block> blocks){
        p.sendMessage(plugin.MessageColor + "---------Blocks Added----------");
        ArrayList<Material> materials=BlockArrayUtils.getBlockTypesInArrayList(blocks);
        for(int i=0;i<materials.size();i++){
              Material BlockType=materials.get(i);
              int BlockCount=BlockArrayUtils.countBlocksofType(blocks, BlockType);
              p.sendMessage(plugin.MessageColor+"   "+BlockType+": "+BlockCount);
        }
    }
}
