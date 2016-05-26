/*
 * AntiCheat for Bukkit.
 * Copyright (C) 2012-2014 AntiCheat Team | http://gravitydevelopment.net
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.gravitydevelopment.anticheat.command.executors;

import net.gravitydevelopment.anticheat.command.CommandBase;
import net.gravitydevelopment.anticheat.util.Permission;
import org.bukkit.command.CommandSender;

public class CommandHelp extends CommandBase {

    private static final String NAME = "AntiCheatReloaded Help";
    private static final String COMMAND = "help";
    private static final String USAGE = "anticheat help";
    private static final Permission PERMISSION = Permission.SYSTEM_HELP;
    private static final String[] HELP = {
            RED + "/anti " + GRAY + "help",
            RED + "/anti " + GRAY + "reload",
            RED + "/anti " + GRAY + "update",
            RED + "/anti " + GRAY + "debug " + WHITE + "<user>",
            RED + "/anti " + GRAY + "check " + WHITE + "[check] [on/off]",
            RED + "/anti " + GRAY + "log " + WHITE + "[file/console] [on/off]",
            RED + "/anti " + GRAY + "report " + WHITE + "[group/user]",
            RED + "/anti " + GRAY + "reset " + WHITE + "[user]",
            RED + "/anti " + GRAY + "spy " + WHITE + "[user]",
    };

    public CommandHelp() {
        super(NAME, COMMAND, USAGE, HELP, PERMISSION);
    }

    @Override
    protected void execute(CommandSender cs, String[] args) {
        sendHelp(cs);
    }
}
