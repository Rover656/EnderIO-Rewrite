package com.enderio.base.common.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @deprecated Replaced by EnderIngredient
 */
@Deprecated
public class EIOIngredient extends Ingredient {
    private final int count;

    private static final EIOIngredient EMPTY = new EIOIngredient(Stream.empty(), 0);

    public EIOIngredient(Stream<? extends Value> values, int count) {
        super(values);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public static EIOIngredient fromValues(int count, Stream<? extends Ingredient.Value> stream) {
        EIOIngredient ingredient = new EIOIngredient(stream, count);
        return ingredient.values.length == 0 ? EMPTY : ingredient;
    }

    public static EIOIngredient of() {
        return EMPTY;
    }

    public static EIOIngredient of(ItemLike... items) {
        return of(Arrays.stream(items).map(ItemStack::new));
    }

    public static EIOIngredient of(int count, ItemLike... items) {
        return of(count, Arrays.stream(items).map(ItemStack::new));
    }

    public static EIOIngredient of(ItemStack... stacks) {
        return of(Arrays.stream(stacks));
    }

    public static EIOIngredient of(int count, ItemStack... stacks) {
        return of(count, Arrays.stream(stacks));
    }

    public static EIOIngredient of(Stream<ItemStack> stacks) {
        return of(1, stacks);
    }

    public static EIOIngredient of(int count, Stream<ItemStack> stacks) {
        return fromValues(count, stacks.filter((stack) -> !stack.isEmpty()).map(Ingredient.ItemValue::new));
    }

    public static EIOIngredient of(Tag<Item> tag) {
        return fromValues(1, Stream.of(new EIOIngredient.TagValue(tag)));
    }

    public static EIOIngredient of(int count, Tag<Item> tag) {
        return fromValues(count, Stream.of(new EIOIngredient.TagValue(tag)));
    }

    public static EIOIngredient fromNetwork(FriendlyByteBuf buf) {
        return Serializer.INSTANCE.parse(buf);
    }

    public static EIOIngredient fromJson(JsonElement json) {
        return Serializer.INSTANCE.parse(json.getAsJsonObject());
    }

    public static EIOIngredient fromVanilla(Ingredient ingredient) {
        return fromVanilla(ingredient, 1);
    }

    public static EIOIngredient fromVanilla(Ingredient ingredient, int count) {
        return new EIOIngredient(Arrays.stream(ingredient.values), count);
    }

    @Override
    public ItemStack[] getItems() {
        ItemStack[] matchingStacks = super.getItems();
        for (ItemStack matchingStack : matchingStacks) {
            matchingStack.setCount(count);
        }
        return matchingStacks;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.add("items", super.toJson());
        json.addProperty("count", count);
        return json;
    }

    @Override
    public boolean test(@Nullable ItemStack itemStack) {
        return super.test(itemStack) && itemStack.getCount() >= count;
    }

    @Override
    public IIngredientSerializer<EIOIngredient> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Serializer implements IIngredientSerializer<EIOIngredient> {
        public static final Serializer INSTANCE = new Serializer();

        private Serializer() {
        }

        @Override
        public EIOIngredient parse(JsonObject json) {
            Ingredient parent = Ingredient.fromJson(json.get("items"));
            int count = json.get("count").getAsInt();
            return new EIOIngredient(Arrays.stream(parent.values), count);
        }

        @Override
        public EIOIngredient parse(FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            return new EIOIngredient(Arrays.stream(ingredient.values), buffer.readShort());
        }

        @Override
        public void write(FriendlyByteBuf buffer, EIOIngredient ingredient) {
            ingredient.toNetwork(buffer);
            buffer.writeShort(ingredient.count);
        }
    }
}
