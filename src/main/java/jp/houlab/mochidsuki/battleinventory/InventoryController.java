package jp.houlab.mochidsuki.battleinventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

import static jp.houlab.mochidsuki.battleinventory.Main.config;
import static jp.houlab.mochidsuki.battleinventory.Main.plugin;


public class InventoryController extends BukkitRunnable {
    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            ItemStack head = player.getInventory().getItem(config.getInt("HeadSlot"));
            ItemStack chest = player.getInventory().getItem(config.getInt("ChestSlot"));
            ItemStack leggings = player.getInventory().getItem(config.getInt("LeggingsSlot"));
            ItemStack boots = player.getInventory().getItem(config.getInt("BootsSlot"));

            if(player.getScoreboardTags().contains("JetPack")){
                chest = new ItemStack(Material.ELYTRA);
            }

            if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                player.getInventory().setItem(EquipmentSlot.HEAD, head);

                if (!Objects.equals(player.getInventory().getItem(EquipmentSlot.CHEST), new ItemStack(Material.ELYTRA))) {
                    player.getInventory().setItem(EquipmentSlot.CHEST, chest);
                }
                player.getInventory().setItem(EquipmentSlot.FEET, boots);
                player.getInventory().setItem(EquipmentSlot.LEGS, leggings);
                //player.updateInventory();
            } else {
                player.getInventory().setItem(EquipmentSlot.HEAD, new ItemStack(Material.AIR));
                if(chest == null || chest.getType() != Material.ELYTRA) {
                    player.getInventory().setItem(EquipmentSlot.CHEST, new ItemStack(Material.AIR));
                }else {
                    player.getInventory().setItem(EquipmentSlot.CHEST, chest);
                }
                player.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.AIR));
                player.getInventory().setItem(EquipmentSlot.LEGS, new ItemStack(Material.AIR));
                //player.updateInventory();
            }
            List<Integer> allowList = config.getIntegerList("AllowSlot");
            allowList.add(config.getInt("HeadSlot"));
            allowList.add(config.getInt("ChestSlot"));
            allowList.add(config.getInt("LeggingsSlot"));
            allowList.add(config.getInt("BootsSlot"));
            for(int i = 0; i <= 35; i++) {
                if(!allowList.contains(i)) {
                    player.getInventory().setItem(i, new ItemStack(Material.BARRIER, 1));
                }
            }
        }
    }
}

