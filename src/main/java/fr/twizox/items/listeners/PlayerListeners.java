package fr.twizox.items.listeners;

import fr.twizox.items.items.behaviors.BehaviorManager;
import fr.twizox.items.items.behaviors.ItemBehavior;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class PlayerListeners implements Listener {


    private void handleEvent(Player player, Event event) {
        Optional<ItemBehavior> behavior = BehaviorManager.INSTANCE.getBehavior(player.getInventory().getItemInMainHand());
        behavior.ifPresent(itemBehavior -> itemBehavior.getApplicableProperties(event).forEach(property -> property.handle(event)));
    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        handleEvent(event.getPlayer(), event);
    }

    @EventHandler
    public void onSwitch(PlayerItemHeldEvent event) {
        handleEvent(event.getPlayer(), event);
    }

    @EventHandler
    public void onConsume(PlayerInteractEvent event) {
        handleEvent(event.getPlayer(), event);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        handleEvent(event.getPlayer(), event);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        ItemStack hoe = new ItemStack(Material.IRON_HOE);
        BehaviorManager.INSTANCE.getBehavior("farm").ifPresent(behavior -> behavior.apply(hoe));
        player.getInventory().addItem(hoe);
    }

}
