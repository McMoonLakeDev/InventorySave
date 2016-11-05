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


package com.minecraft.moonlake.inventorysave.config;

import com.minecraft.moonlake.api.annotation.plugin.PluginAnnotationFactory;
import com.minecraft.moonlake.api.annotation.plugin.config.ConfigValue;
import com.minecraft.moonlake.inventorysave.InventorySavePlugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class InventorySaveConfig {

    @ConfigValue(path = "Prefix", colorChar = '&')
    private String prefix;

    @ConfigValue(path = "Message.Enable")
    private boolean messageEnable;

    @ConfigValue(path = "Message.NoSave", colorChar = '&')
    private String messageNoSave;

    @ConfigValue(path = "Message.YesSave", colorChar = '&')
    private String messageYesSave;

    @ConfigValue(path = "DefaultSaveWorlds")
    private List<String> defaultSaveWorldList;

    @ConfigValue(path = "InventorySaveWorlds")
    private List<String> inventorySaveWorlds;

    private final InventorySavePlugin main;

    public InventorySaveConfig(InventorySavePlugin main) {
        this.main = main;
    }

    public InventorySavePlugin getMain() {
        return main;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean reload() {
        if(!getMain().getDataFolder().exists())
            getMain().getDataFolder().mkdirs();
        File config = new File(getMain().getDataFolder(), "config.yml");
        if(!config.exists())
            getMain().saveDefaultConfig();
        PluginAnnotationFactory.get().config().load(getMain(), "config.yml", this);

        return true;
    }

    public boolean isMessageEnable() {
        return messageEnable;
    }

    public String getMessageNoSave() {
        return messageNoSave;
    }

    public String getMessageYesSave() {
        return messageYesSave;
    }

    public boolean isAllowSaveInv(Player player) {
        String world = player.getWorld().getName();

        if(defaultSaveWorldList.contains(world))
            return true;
        return player.hasPermission("moonlake.inventorysave.inv") && inventorySaveWorlds.contains(world);
    }

    public boolean isAllowSaveLevel(Player player) {
        String world = player.getWorld().getName();

        if(defaultSaveWorldList.contains(world))
            return true;
        return player.hasPermission("moonlake.inventorysave.lvl") && inventorySaveWorlds.contains(world);
    }
}
