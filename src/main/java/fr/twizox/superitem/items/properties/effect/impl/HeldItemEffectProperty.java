package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.SuperItems;
import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeldItemEffectProperty extends AbstractEffectProperty<Event> {

    public HeldItemEffectProperty(PotionEffectType type, int amplifier) {
        super(new PotionEffect(type, Integer.MAX_VALUE, amplifier));
    }

    public HeldItemEffectProperty(PotionEffect potionEffect) {
        this(potionEffect.getType(), potionEffect.getAmplifier());
    }

    public HeldItemEffectProperty() {
        this(PotionEffectType.SPEED, 1);
    }

    @Override
    public void handle(Event event) {

        Player player;
        ItemStack item;

        if (event instanceof PlayerItemHeldEvent playerItemHeldEvent) {
            player = (playerItemHeldEvent).getPlayer();
            item = player.getInventory().getItem(playerItemHeldEvent.getNewSlot());
            performAction(player, item);
            return;
        }

        if (event instanceof PlayerDropItemEvent playerDropItemEvent) {
            player = playerDropItemEvent.getPlayer();
            performAction(player, player.getInventory().getItemInMainHand());
            return;
        }

        if (event instanceof EntityPickupItemEvent entityPickupItemEvent) {
            if (!(entityPickupItemEvent.getEntity() instanceof Player)) return;
            player = (Player) entityPickupItemEvent.getEntity();

            int selectedSlot = player.getInventory().getHeldItemSlot();
            int pickupSlot = player.getInventory().firstEmpty();

            if (selectedSlot != pickupSlot) return;
            item = entityPickupItemEvent.getItem().getItemStack();
            performAction(player, item);
            return;
        }

        if (event instanceof InventoryClickEvent inventoryClickEvent) {
            if (!(inventoryClickEvent.getWhoClicked() instanceof Player playerWhoClicked)) return;

            player = playerWhoClicked;
            int selectedSlot = player.getInventory().getHeldItemSlot();

            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(SuperItems.class), () -> {
                ItemStack newItem = playerWhoClicked.getInventory().getItem(selectedSlot);
                performAction(player, newItem);
            }, 1);
        }

    }


    private void performAction(Player player, ItemStack item) {
        if (player == null) return;
        if (!checkItem(player, item)) removeEffect(player);
        else applyEffect(player);
    }

    @Override
    public boolean handleOnlyWhenItemHold() {
        return false;
    }


    @Override
    public HeldItemEffectProperty deserialize(ConfigurationSection section) {
        return new HeldItemEffectProperty(getPotionEffect(section));
    }

}
