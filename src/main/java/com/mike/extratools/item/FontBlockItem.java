package com.mike.extratools.item;

import com.mike.extratools.client.ModFontRenderer;
import com.mike.extratools.client.ModFontStyle;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class FontBlockItem extends BlockItem {

    private final ModFontStyle fontStyle;

    public FontBlockItem(Block block, Properties properties, ModFontStyle fontStyle) {
        super(block, properties);
        this.fontStyle = fontStyle;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component baseName = super.getName(stack);
        return ModFontRenderer.applyFont(this.fontStyle, baseName);
    }
}
