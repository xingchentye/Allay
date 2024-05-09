package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.CraftingContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.allaymc.api.container.FullContainerType.CREATED_OUTPUT;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeActionProcessor implements ContainerActionProcessor<CraftRecipeAction> {

    public static final String RECIPE_DATA_KEY = "recipe";

    @Override
    public ActionResponse handle(CraftRecipeAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool) {
        CraftingContainer craftingContainer = player.getContainer(FullContainerType.CRAFTING_TABLE);
        if (craftingContainer == null) {
            // The player is not opening a crafting table, using crafting grid instead
            craftingContainer = player.getContainer(FullContainerType.CRAFTING_GRID);
        }
        var recipe = RecipeRegistry.getRegistry().getRecipeByNetworkId(action.getRecipeNetworkId());
        var input = craftingContainer.createCraftingInput();
        var matched = recipe.match(input);
        if (!matched) {
            log.warn("Mismatched recipe! Network id: {}", recipe.getNetworkId());
            return error();
        } else {
            dataPool.put(RECIPE_DATA_KEY, recipe);
            // Validate the consume action count which client sent
            // Some checks are placed inside the ConsumeActionProcessor (such as checking the quantity of consumed items)
            var consumeActions = findAllConsumeActions(actions, currentActionIndex + 1);
            var consumeActionCountNeeded = craftingContainer.calculateShouldConsumedItemCount();
            if (consumeActions.size() != consumeActionCountNeeded) {
                log.warn("Mismatched consume action count! Expected: {}, Actual: {}", consumeActionCountNeeded, consumeActions.size());
                return error();
            }
            if (recipe.getOutputs().length == 1) {
                // If the recipe output is 1 item, the client will not send a CreateAction.
                // In this case, we directly output the item to CREATED_OUTPUT in the CraftRecipeAction
                // If the recipe output is multiple items, the client will send a CreateAction.
                // In this case, we will output the items to CREATED_OUTPUT inside the CreateActionProcessor
                var output = recipe.getOutputs()[0];
                var createdOutput = player.getContainer(CREATED_OUTPUT);
                createdOutput.setItemStack(0, output);
            }
        }
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE;
    }

    protected List<ConsumeAction> findAllConsumeActions(ItemStackRequestAction[] actions, int startIndex) {
        List<ConsumeAction> found = new ArrayList<>();
        for (int i = startIndex; i < actions.length; i++) {
            if (actions[i] instanceof ConsumeAction consumeAction) {
                found.add(consumeAction);
            }
        }
        return found;
    }
}
