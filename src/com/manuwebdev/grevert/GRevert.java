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
package com.manuwebdev.grevert;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Manuel Gauto
 */
public class GRevert extends JavaPlugin {

    /**
     * Log to console
     */
    static final Logger log = Logger.getLogger("Minecraft");
    /**
     * Color to use for messages
     */
    public final ChatColor MessageColor = ChatColor.GREEN;
    /**
     * Enabled Boolean
     */
    boolean pluginEnabled = false;
    /**
     * Name of plugin
     */
    private final String PLUGIN_NAME = "GRevert";
    /**
     * WorldEdit
     */
    WorldEditPlugin WorldEdit;
    /**
     * Table Name
     */
    private String TABLE_NAME = "GRevert";

    @Override
    public void onDisable() {
        log.info("[" + PLUGIN_NAME + "] Disabled.");
    }

    @Override
    public void onEnable() {
        //Get WorldEdit API
        WorldEdit = getWorldEditPlugin();
        log.info("[" + PLUGIN_NAME + "] Enabled.");
    }

    /**
     * Inspired by SimpleSpleef which was inspired by MultiversePortals
     *
     * @return true if worldedit is installed
     */
    private boolean isWorldEditInstalled() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldEdit");
        if (plugin != null) {
            // check version
            try {
                double version = Double.parseDouble(plugin.getDescription().getVersion());
                if (version < 5.0) {
                    //Not 5.0 or higher
                    log.log(Level.SEVERE, "[" + PLUGIN_NAME + "] WorldEdit version must be higher than 5.0");
                    return false;
                }
            } catch (Exception e) {
                log.log(Level.INFO, "[" + PLUGIN_NAME + "] Strange WorldEdit version error...Plugin should be fine :)");
            }
            //Version OK
            return true;
        } else {
            //Plugin not installed
            return false;
        }
    }

    /**
     * Gets WorldEditPlugin instance or null if WorldEdit is not installed
     *
     * @return plguin
     */
    private WorldEditPlugin getWorldEditPlugin() {
        if (isWorldEditInstalled()) {
            Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldEdit");
            WorldEditPlugin WorldEdit = (WorldEditPlugin) plugin;
            return WorldEdit;
        }
        return null;
    }

    /**
     * Get WorldEdit plugin
     *
     * @return WorldEditPlugin
     */
    public WorldEditPlugin getWorldEdit() {
        return WorldEdit;
    }

    /**
     * Get name for MYSQL Table
     *
     * @return
     */
    public String getMYSQLTableName() {
        return TABLE_NAME;
    }
}
