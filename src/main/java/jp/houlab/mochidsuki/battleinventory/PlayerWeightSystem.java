package jp.houlab.mochidsuki.battleinventory;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static jp.houlab.mochidsuki.battleinventory.Main.plugin;

public class PlayerWeightSystem extends BukkitRunnable {
    @Override
    public void run() {
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            int armor = 0;
            for(int i = 0;i <= 35;i++) {
                if(player.getInventory().getItem(i) != null) {
                    switch (player.getInventory().getItem(i).getType()) {
                        case LEATHER_HELMET:
                        case CHAINMAIL_HELMET:
                        case IRON_HELMET:
                        case GOLDEN_HELMET:
                        case DIAMOND_HELMET:
                        case NETHERITE_HELMET:
                        case LEATHER_BOOTS:
                        case CHAINMAIL_BOOTS:
                        case IRON_BOOTS:
                        case GOLDEN_BOOTS:
                        case DIAMOND_BOOTS:
                        case NETHERITE_BOOTS: {
                            armor += 1;
                            break;
                        }
                        case LEATHER_CHESTPLATE:
                        case CHAINMAIL_CHESTPLATE:
                        case IRON_CHESTPLATE:
                        case GOLDEN_CHESTPLATE:
                        case DIAMOND_CHESTPLATE:
                        case NETHERITE_CHESTPLATE:{
                            armor += 3;
                            break;
                        }
                    }
                }
            }
            if(armor > 5) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, armor-3));
            }
        }
    }
}
