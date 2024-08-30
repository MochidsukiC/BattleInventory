package jp.houlab.mochidsuki.battleinventory;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static jp.houlab.mochidsuki.battleinventory.Main.config;
import static jp.houlab.mochidsuki.battleinventory.Main.plugin;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (Objects.requireNonNull(event.getMaterial())) {
                case LEATHER_HELMET:
                case CHAINMAIL_HELMET:
                case IRON_HELMET:
                case GOLDEN_HELMET:
                case DIAMOND_HELMET:
                case NETHERITE_HELMET:
                    ItemStack originItemH = event.getPlayer().getInventory().getItem(config.getInt("HeadSlot"));
                    ItemStack newItemH = event.getItem();
                    event.getPlayer().getInventory().setItem(config.getInt("HeadSlot"), newItemH);
                    event.getPlayer().getInventory().setItemInMainHand(originItemH);
                    break;
                case LEATHER_CHESTPLATE:
                case CHAINMAIL_CHESTPLATE:
                case IRON_CHESTPLATE:
                case GOLDEN_CHESTPLATE:
                case DIAMOND_CHESTPLATE:
                case NETHERITE_CHESTPLATE:
                    ItemStack originItemC = event.getPlayer().getInventory().getItem(config.getInt("ChestSlot"));
                    ItemStack newItemC = event.getItem();
                    event.getPlayer().getInventory().setItem(config.getInt("ChestSlot"), newItemC);
                    event.getPlayer().getInventory().setItemInMainHand(originItemC);
                    break;
                case LEATHER_BOOTS:
                case CHAINMAIL_BOOTS:
                case IRON_BOOTS:
                case GOLDEN_BOOTS:
                case DIAMOND_BOOTS:
                case NETHERITE_BOOTS:
                    ItemStack originItemB = event.getPlayer().getInventory().getItem(config.getInt("BootsSlot"));
                    ItemStack newItemB = event.getItem();
                    event.getPlayer().getInventory().setItem(23, newItemB);
                    event.getPlayer().getInventory().setItemInMainHand(originItemB);
                    break;
            }
        }
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        plugin.reloadConfig();
        config = plugin.getConfig();
        Optional<Material> cursor = Optional.of(Objects.requireNonNull(event.getCursor()).getType());
        int slot = event.getSlot();
        if(event.getClickedInventory() != null) {
            if (!(Objects.requireNonNull(event.getClickedInventory()).getType() == InventoryType.PLAYER)) {
                slot = 0;
            }
        }

        List<Integer> allowList = config.getIntegerList("AllowSlot");
        if(slot == config.getInt("HeadSlot")) {
            if (!(cursor.orElse(Material.LEATHER_HELMET) == Material.LEATHER_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.CHAINMAIL_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.GOLDEN_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.IRON_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.DIAMOND_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.NETHERITE_HELMET || event.getAction() == InventoryAction.DROP_ONE_SLOT || event.getAction() == InventoryAction.PICKUP_ALL)) {
                event.setCancelled(true);
                event.setResult(org.bukkit.event.Event.Result.DENY);
            }
        }else if(slot == config.getInt("ChestSlot")) {
            if (!(cursor.orElse(Material.LEATHER_CHESTPLATE) == Material.LEATHER_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.CHAINMAIL_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.IRON_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.GOLDEN_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.DIAMOND_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.NETHERITE_CHESTPLATE || event.getAction() == InventoryAction.DROP_ONE_SLOT|| event.getAction() == InventoryAction.PICKUP_ALL)) {
                event.setCancelled(true);
                event.setResult(org.bukkit.event.Event.Result.DENY);
            }
        }else if(slot == config.getInt("BootsSlot")) {
            if (!(cursor.orElse(Material.LEATHER_BOOTS) == Material.LEATHER_BOOTS || cursor.orElse(Material.LEATHER_BOOTS) == Material.CHAINMAIL_BOOTS || cursor.orElse(Material.LEATHER_HELMET) == Material.IRON_BOOTS || cursor.orElse(Material.LEATHER_HELMET) == Material.GOLDEN_BOOTS || cursor.orElse(Material.LEATHER_BOOTS) == Material.DIAMOND_BOOTS || cursor.orElse(Material.LEATHER_HELMET) == Material.NETHERITE_BOOTS || event.getAction() == InventoryAction.DROP_ONE_SLOT|| event.getAction() == InventoryAction.PICKUP_ALL)) {
                event.setCancelled(true);
                event.setResult(org.bukkit.event.Event.Result.DENY);
            }
        }else if(!(allowList.contains(slot)||event.getClickedInventory().getType() != InventoryType.PLAYER)) {
            event.setCancelled(true);
            event.setResult(org.bukkit.event.Event.Result.DENY);
        }else if((cursor.orElse(Material.LEATHER_HELMET) == Material.LEATHER_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.CHAINMAIL_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.GOLDEN_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.IRON_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.DIAMOND_HELMET || cursor.orElse(Material.LEATHER_HELMET) == Material.NETHERITE_HELMET || cursor.orElse(Material.LEATHER_CHESTPLATE) == Material.LEATHER_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.CHAINMAIL_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.IRON_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.GOLDEN_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.DIAMOND_CHESTPLATE || cursor.orElse(Material.LEATHER_HELMET) == Material.NETHERITE_CHESTPLATE || cursor.orElse(Material.LEATHER_BOOTS) == Material.LEATHER_BOOTS || cursor.orElse(Material.LEATHER_BOOTS) == Material.CHAINMAIL_BOOTS || cursor.orElse(Material.LEATHER_HELMET) == Material.IRON_BOOTS || cursor.orElse(Material.LEATHER_HELMET) == Material.GOLDEN_BOOTS || cursor.orElse(Material.LEATHER_BOOTS) == Material.DIAMOND_BOOTS || cursor.orElse(Material.LEATHER_HELMET) == Material.NETHERITE_BOOTS )&& event.getClickedInventory().getType() == InventoryType.PLAYER ){
            event.setCancelled(true);
            event.setResult(org.bukkit.event.Event.Result.DENY);
        }

    }

}
