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


package com.minecraft.moonlake.inventorysave;

import com.minecraft.moonlake.MoonLakePlugin;
import com.minecraft.moonlake.api.annotation.plugin.PluginAnnotationFactory;
import com.minecraft.moonlake.event.EventHelper;
import com.minecraft.moonlake.inventorysave.commands.CommandInventorySave;
import com.minecraft.moonlake.inventorysave.config.InventorySaveConfig;
import com.minecraft.moonlake.inventorysave.listeners.PlayerListener;
import com.minecraft.moonlake.logger.MLogger;
import com.minecraft.moonlake.logger.MLoggerWrapped;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class InventorySavePlugin extends JavaPlugin {

    private final MLogger mLogger;
    private InventorySaveConfig configuration;

    public InventorySavePlugin() {
        this.mLogger = new MLoggerWrapped("MoonLakeInventorySave");
    }

    @Override
    public void onEnable() {
        if(!setupMoonLake()) {
            this.getMLogger().error("前置月色之湖核心API插件加载失败.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.configuration = new InventorySaveConfig(this);
        this.configuration.reload();

        PluginAnnotationFactory.get().command().registerCommand(this, new CommandInventorySave(this));
        EventHelper.registerEvent(new PlayerListener(this), this);

        this.getMLogger().info("月色之湖背包保存 InventorySave 插件 v" + getDescription().getVersion() + " 成功加载.");
    }

    @Override
    public void onDisable() {
    }

    public MLogger getMLogger() {
        return mLogger;
    }

    public InventorySaveConfig getConfiguration() {
        return configuration;
    }

    private boolean setupMoonLake() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin("MoonLake");
        return plugin != null && plugin instanceof MoonLakePlugin;
    }
}
