package fr.twizox.items.items.properties.farm;

import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PlantingProperty implements ItemProperty<PlayerInteractEvent> {

    private final int radius;

    public PlantingProperty(int radius) {
        this.radius = radius;
    }

    @Override
    public void handle(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock == null && (clickedBlock.getType() != Material.FARMLAND && clickedBlock.getType() != Material.SOUL_SAND)) return;

        ItemStack[] inventoryContents = player.getInventory().getContents();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Block floor = clickedBlock.getRelative(x, 0, z);
                if (floor.getType() != Material.FARMLAND && floor.getType() != Material.SOUL_SAND) continue;
                Block seed = floor.getRelative(0, 1, 0);
                if (seed.getType() != Material.AIR) continue;

                Arrays.stream(inventoryContents)
                        .filter(itemStack -> itemStack != null && isPlantable(itemStack.getType(), floor.getType()))
                        .findFirst()
                        .ifPresent(itemStack -> {
                            seed.setType(getCropMaterialFromItem(itemStack));
                            itemStack.setAmount(itemStack.getAmount() - 1);
                        });
            }
        }


    }

    private static boolean isPlantable(Material type, Material floor) {
        return ((type == Material.POTATO || type == Material.CARROT || type == Material.WHEAT_SEEDS || type == Material.BEETROOT_SEEDS) && floor == Material.FARMLAND)
                || (type == Material.NETHER_WART && floor == Material.SOUL_SAND);
    }

    private static Material getCropMaterialFromItem(ItemStack item) {
        return switch(item.getType()) {
            case CARROT -> Material.CARROTS;
            case POTATO -> Material.POTATOES;
            case WHEAT_SEEDS -> Material.WHEAT;
            case BEETROOT_SEEDS -> Material.BEETROOTS;
            default -> item.getType();

        };
    }

}
