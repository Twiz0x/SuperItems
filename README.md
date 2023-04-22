

# SuperItems

> A plugin that permits you to add custom behaviors to items.
> You can make your own item properties through a simple configuration file.

## Preview

*TODO: Add a preview gif*
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

> Note: you can also provide your own properties using our [API](https://todo.com)
> and make a pull request to add it to the plugin.

### Wiki links
*TODO: Link to the wiki*
- Commands: [Link](https://todo.com)
- Permissions: [Link](https://todo.com)
- Configuration: [Link](https://todo.com)
- API: [Link](https://todo.com)