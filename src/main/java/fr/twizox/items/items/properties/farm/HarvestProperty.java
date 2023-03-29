package fr.twizox.items.items.properties.farm;

import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class HarvestProperty implements ItemProperty<BlockBreakEvent> {

    @Override
    public void handle(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Block block = event.getBlock();
        if(!(block.getBlockData() instanceof Ageable)) return;

        Location location = block.getLocation();
        location.getBlock().setType(block.getType());
    }

}
