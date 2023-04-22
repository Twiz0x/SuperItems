package fr.twizox.superitem.listeners;

import fr.twizox.superitem.items.behaviors.BehaviorManager;
import fr.twizox.superitem.items.behaviors.ItemBehavior;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class PlayerListeners implements Listener {

    private final BehaviorManager behaviorManager = Bukkit.getServicesManager().load(BehaviorManager.class);

    private void handleItem(Player player, Event event, ItemStack item) {
        if (item == null) return;
        boolean itemHold = item.isSimilar(player.getInventory().getItemInMainHand());
        Optional<ItemBehavior> behavior = behaviorManager.getBehavior(item);

        behavior.ifPresent(itemBehavior -> itemBehavior
                .getProperties()
                .forEach(property -> property.handleEvent(event, itemHold)));
    }

    private static Stream<ItemStack> getInventoryItems(Player player) {
        return Arrays.stream(player.getInventory().getContents()).filter(Objects::nonNull);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }

    @EventHandler
    public void onSwitch(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        handleItem(player, event, event.getItemDrop().getItemStack());
        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Bukkit.broadcastMessage(event.getAction().name());
        if (event.getAction().name().contains("PLACE"))
            handleItem(player, event, event.getCursor());
        else if (event.getAction().name().contains("HOTBAR") ||
                (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
                        && !player.getInventory().equals(event.getClickedInventory())))
            handleItem(player, event, event.getCurrentItem());

        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        handleItem(player, event, event.getItem().getItemStack());
        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }


    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        getInventoryItems(player).forEach(item -> handleItem(player, event, item));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }

}
