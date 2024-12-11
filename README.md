

# SuperItems

A Mincraft plugin that permits you to add custom behaviors to items.
You can make your own item properties through a simple configuration file.

## Preview
### Example configuration
```yaml
behaviors:
  farm_tool:
    - my_dirt_excavator # Property specified below
    - harvest # Default property to replant crops
  
properties:
  my_dirt_excavator:
      type: excavator # The type of property
      radius: 1 # The radius of the excavation
      depth: 0 # The depth of the excavation
      materials: # The materials to break
        - DIRT
        - GRASS_BLOCK
```


### Properties To-Do
- [x] **Excavator**: Breaks blocks in a provided radius and depth.
- [x] **Harvest**: Replant crops automatically when breaking them.
- [ ] **Planting**: Plant crops in a provided radius.
- [ ] **EatEffect**: Apply a potion effect when eating the item.
