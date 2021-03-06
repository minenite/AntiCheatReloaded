/*
 * AntiCheatReloaded for Bukkit and Spigot.
 * Copyright (c) 2012-2015 AntiCheat Team
 * Copyright (c) 2016-2020 Rammelkast
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

package com.rammelkast.anticheatreloaded.check.combat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.rammelkast.anticheatreloaded.AntiCheatReloaded;
import com.rammelkast.anticheatreloaded.check.CheckResult;
import com.rammelkast.anticheatreloaded.check.CheckType;
import com.rammelkast.anticheatreloaded.event.EventListener;

public class VelocityCheck {

	private static final Map<UUID, Integer> VL_COUNT = new HashMap<UUID, Integer>();

	public static void cleanPlayer(Player p) {
		VL_COUNT.remove(p.getUniqueId());
	}

	public static void runCheck(EntityDamageByEntityEvent e, final Player p) {
		if (AntiCheatReloaded.getManager().getCheckManager().isOpExempt(p)
				|| AntiCheatReloaded.getManager().getCheckManager().isExempt(p, CheckType.VELOCITY))
			return;
		final Location then = p.getLocation();
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!p.isOnline())
					return;
				if (then.distance(p.getLocation()) < 0.125) {
					if (!VL_COUNT.containsKey(p.getUniqueId()))
						VL_COUNT.put(p.getUniqueId(), 1);
					else {
						VL_COUNT.put(p.getUniqueId(), VL_COUNT.get(p.getUniqueId()) + 1);
						if (VL_COUNT.get(p.getUniqueId()) > AntiCheatReloaded.getManager().getBackend().getMagic()
								.VELOCITY_MAX_FLAGS()) {
							EventListener.log(new CheckResult(CheckResult.Result.FAILED,
									p.getName() + " failed Velocity, had zero/low velocity "
											+ VL_COUNT.get(p.getUniqueId()) + " times (max="
											+ AntiCheatReloaded.getManager().getBackend().getMagic().VELOCITY_MAX_FLAGS()
											+ ", dist=" + then.distance(p.getLocation()) + ")").getMessage(),
									p, CheckType.VELOCITY);
							VL_COUNT.remove(p.getUniqueId());
						}
					}
				} else {
					VL_COUNT.remove(p.getUniqueId());
				}
			}
		}.runTaskLater(AntiCheatReloaded.getPlugin(), 4);
	}

}
