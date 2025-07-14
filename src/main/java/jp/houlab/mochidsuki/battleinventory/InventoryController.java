package jp.houlab.mochidsuki.battleinventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

import static jp.houlab.mochidsuki.battleinventory.Main.config;
import static jp.houlab.mochidsuki.battleinventory.Main.plugin;

/**
 * インベントリの制限、同期を司るクラス
 * @author Mochidsuki
 */
public class InventoryController extends BukkitRunnable {
    /**
     * 実行
     */
    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            //防具のアイテムをゲット
            ItemStack head = player.getInventory().getItem(config.getInt("HeadSlot"));
            ItemStack chest = player.getInventory().getItem(config.getInt("ChestSlot"));
            ItemStack leggings = player.getInventory().getItem(config.getInt("LeggingsSlot"));
            ItemStack boots = player.getInventory().getItem(config.getInt("BootsSlot"));

            //ジェットパック中の処理
            if(player.getScoreboardTags().contains("JetPack")){
                chest = new ItemStack(Material.ELYTRA);
            }

            if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {//透明化の防具自動削除
                player.getInventory().setItem(EquipmentSlot.HEAD, head);

                if (chest != null && chest.getType().equals(Material.LEATHER_CHESTPLATE)&& chest.getType().equals(Material.CHAINMAIL_CHESTPLATE)&& chest.getType().equals(Material.IRON_CHESTPLATE)&& chest.getType().equals(Material.GOLDEN_CHESTPLATE)&& chest.getType().equals(Material.DIAMOND_CHESTPLATE)&& chest.getType().equals(Material.NETHERITE_CHESTPLATE)) {
                    chest = chest.clone();
                    if (player.getLevel() >= 10 && chest.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) < 2) {
                        chest.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
                    }
                    if (player.getLevel() >= 30 && chest.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) < 2) {
                        chest.addEnchantment(Enchantment.PROTECTION_FIRE, 2);
                    }
                    if (player.getLevel() >= 70 && chest.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 1) {
                        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    }
                    if (player.getLevel() >= 140 && chest.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 2) {
                        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                    }
                    if(player.getLevel() >= 210 && chest.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 3){
                        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    }

                }
                player.getInventory().setItem(EquipmentSlot.CHEST, chest);
                player.getInventory().setItem(EquipmentSlot.FEET, boots);
                player.getInventory().setItem(EquipmentSlot.LEGS, leggings);
                //player.updateInventory();
            } else {//防具同期
                player.getInventory().setItem(EquipmentSlot.HEAD, new ItemStack(Material.AIR));
                if (chest == null || chest.getType() != Material.ELYTRA) {
                    player.getInventory().setItem(EquipmentSlot.CHEST, new ItemStack(Material.AIR));
                } else {

                    player.getInventory().setItem(EquipmentSlot.CHEST, chest);
                }
                player.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.AIR));
                player.getInventory().setItem(EquipmentSlot.LEGS, new ItemStack(Material.AIR));
                //player.updateInventory();
            }


            //不許可スロットの拒否
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

