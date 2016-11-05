/*
 * Copyright (C) 2016 The MoonLake Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.minecraft.moonlake.inventorysave.listeners;

import com.minecraft.moonlake.api.event.MoonLakeListener;
import com.minecraft.moonlake.inventorysave.InventorySavePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements MoonLakeListener {

    private final InventorySavePlugin main;

    public PlayerListener(InventorySavePlugin main) {
        this.main = main;
    }

    public InventorySavePlugin getMain() {
        return main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(getMain().getConfiguration().isAllowSaveInv(player)) {
            event.setKeepInventory(true);

            if(getMain().getConfiguration().isMessageEnable()) {
                player.sendMessage(getMain().getConfiguration().getPrefix() + getMain().getConfiguration().getMessageYesSave());
            }
        }
        else {
            if(getMain().getConfiguration().isMessageEnable()) {
                player.sendMessage(getMain().getConfiguration().getPrefix() + getMain().getConfiguration().getMessageNoSave());
            }
        }
        if(getMain().getConfiguration().isAllowSaveLevel(player)) {
            event.setKeepLevel(true);
            event.setDroppedExp(0);
        }
    }
}
