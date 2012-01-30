/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuwebdev.grevert.Listener;

import com.manuwebdev.grevert.GRevert;
import com.manuwebdev.grevert.MYSQL.MYSQLActions;
import com.manuwebdev.grevert.MYSQL.MYSQLInterface;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author Manuel Gauto
 */
public class BlockListener implements Listener {

    /**
     * Bridge to MYSQL
     */
    MYSQLInterface mysqlInterface;
    
    /**
     * MYSQL Actions
     */
    MYSQLActions mysqlActions;

    /**
     * Logger
     */
    private Logger log;
    
    /**
     * 
     * @param mysqlInterface
     * @param log 
     */
    public BlockListener(MYSQLInterface mysqlInterface, GRevert plugin) {
        mysqlActions = new MYSQLActions(mysqlInterface, log,plugin.getMYSQLTableName());
        this.log=plugin.getLogger();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(final BlockBreakEvent event) {
        mysqlActions.addDestroyedBlock(event.getBlock());
    }
     
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace(final BlockBreakEvent event) {
        mysqlActions.addPlacedBlock(event.getBlock());
    }
}
