package fr.twizox.items.listeners;

import fr.twizox.items.items.behaviors.BehaviorManager;
import fr.twizox.items.items.behaviors.ItemBehavior;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class PlayerListeners implements Listener {


    private static void handleItem(Player player, Event event, ItemStack item) {
        if (item == null) return;
        boolean itemHold = item.isSimilar(player.getInventory().getItemInMainHand());
        Optional<ItemBehavior> behavior = BehaviorManager.INSTANCE.getBehavior(item);

        behavior.ifPresent(itemBehavior -> itemBehavior.getProperties().forEach(property -> property.handleEvent(event, itemHold)));
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
    public void onConsume(PlayerInteractEvent event) {
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
