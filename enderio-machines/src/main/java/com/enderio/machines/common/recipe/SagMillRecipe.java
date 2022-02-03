package com.enderio.machines.common.recipe;

import com.enderio.base.common.recipe.DataGenSerializer;
import com.enderio.machines.common.init.MachineRecipes;
import com.google.gson.*;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SagMillRecipe implements IMachineRecipe<SagMillRecipe, Container> {
    public record Output(Item item, int count, float chance) {
        public static Output of(ItemLike item) {
            return of(item, 1);
        }

        public static Output of(ItemLike item, int count) {
            return of(item, count, 1.0f);
        }

        public static Output of(ItemLike item, int count, float chance) {
            return new Output(item.asItem(), count, chance);
        }

        public static Output fromJson(JsonObject json) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("item").getAsString()));
            int count = json.get("count").getAsInt();
            float chance = json.get("chance").getAsFloat();
            return new Output(item, count, chance);
        }

        public JsonObject toJson() {
            JsonObject json = new JsonObject();
            json.addProperty("item", item.getRegistryName().toString());
            json.addProperty("count", count);
            json.addProperty("chance", chance);
            return json;
        }

        public static Output fromNetwork(FriendlyByteBuf buffer) {
            Item item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
            int count = buffer.readInt();
            float chance = buffer.readFloat();
            return new Output(item, count, chance);
        }

        public void toNetwork(FriendlyByteBuf buffer) {
            buffer.writeResourceLocation(item.getRegistryName());
            buffer.writeInt(count);
            buffer.writeFloat(chance);
        }
    }

    private final ResourceLocation id;
    private final Ingredient input;
    private final List<Output> outputs;
    private final int energy;
    private final RecipeBonusType bonus;

    public SagMillRecipe(ResourceLocation id, Ingredient input, List<Output> outputs, int energy, RecipeBonusType bonus) {
        this.id = id;
        this.input = input;
        this.outputs = outputs;
        this.energy = energy;
        this.bonus = bonus;
    }

    public Ingredient getInput() {
        return input;
    }

    public RecipeBonusType getBonus() {
        return bonus;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(Container container) {
        return null;
    }

    @Override
    public ItemStack getResultItem() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public DataGenSerializer<SagMillRecipe, Container> getSerializer() {
        return MachineRecipes.Serializer.SAGMILLING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MachineRecipes.Types.SAGMILLING;
    }

    // TODO: Rewrite this part of the interface
    @Override
    public List<List<ItemStack>> getAllInputs() {
        return Collections.singletonList(Arrays.stream(input.getItems()).toList());
    }

    @Override
    public List<ItemStack> getAllOutputs() {
        return List.of();
    }

    @Override
    public int getEnergyCost() {
        return energy;
    }

    public static class Serializer extends DataGenSerializer<SagMillRecipe, Container> {

        @Override
        public SagMillRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe) {
            Ingredient input = Ingredient.fromJson(serializedRecipe.get("input"));

            JsonArray jsonOutputs = serializedRecipe.get("outputs").getAsJsonArray();
            List<Output> outputs = new ArrayList<>(jsonOutputs.size());
            for (int i = 0; i < jsonOutputs.size(); i++) {
                outputs.add(Output.fromJson(jsonOutputs.get(i).getAsJsonObject()));
            }

            int energy = serializedRecipe.get("energy").getAsInt();
            RecipeBonusType bonus = RecipeBonusType.valueOf(serializedRecipe.get("bonus").getAsString().toUpperCase());

            return new SagMillRecipe(recipeId, input, outputs, energy, bonus);
        }

        @Override
        public void toJson(SagMillRecipe recipe, JsonObject json) {
            json.add("input", recipe.input.toJson());

            JsonArray outputs = new JsonArray();
            for (Output output : recipe.outputs) {
                outputs.add(output.toJson());
            }
            json.add("outputs", outputs);
            json.addProperty("energy", recipe.energy);
            json.addProperty("bonus", recipe.bonus.toString());
        }

        @Override
        public SagMillRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            int outputCount = buffer.readVarInt();
            List<Output> outputs = new ArrayList<>(outputCount);
            for (int i = 0; i < outputCount; i++) {
                outputs.add(Output.fromNetwork(buffer));
            }
            int energy = buffer.readInt();
            RecipeBonusType bonusType = buffer.readEnum(RecipeBonusType.class);

            return new SagMillRecipe(recipeId, input, outputs, energy, bonusType);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SagMillRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeVarInt(recipe.outputs.size());
            for (Output output : recipe.outputs) {
                output.toNetwork(buffer);
            }
            buffer.writeInt(recipe.energy);
            buffer.writeEnum(recipe.bonus);
        }
    }

}
