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


package com.minecraft.moonlake.inventorysave.commands;

import com.minecraft.moonlake.api.annotation.plugin.command.Command;
import com.minecraft.moonlake.api.annotation.plugin.command.CommandArgument;
import com.minecraft.moonlake.api.annotation.plugin.command.CommandPermission;
import com.minecraft.moonlake.inventorysave.InventorySavePlugin;
import org.bukkit.command.CommandSender;

public class CommandInventorySave {

    private final InventorySavePlugin main;

    public CommandInventorySave(InventorySavePlugin main) {
        this.main = main;
    }

    public InventorySavePlugin getMain() {
        return main;
    }

    @Command(name = "inventorysave", usage = "<help|reload>", min = 1, max = 1)
    @CommandPermission(value = "moonlake.inventorysave")
    public void onCommand(CommandSender sender, @CommandArgument String arg) {
        if(arg.equalsIgnoreCase("help")) {
            sender.sendMessage(new String[] {
                    "/inventorysave help - 查看命令帮助.",
                    "/inventorysave reload - 重新载入配置文件."
            });
        }
        else if(arg.equalsIgnoreCase("reload")) {
            if(getMain().getConfiguration().reload()) {
                sender.sendMessage(getMain().getConfiguration().getPrefix() + "配置文件已经重新载入...");
            }
        }
        else {
            sender.sendMessage(getMain().getConfiguration().getPrefix() + "未知的命令参数.");
        }
    }
}
