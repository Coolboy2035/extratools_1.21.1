package com.mike.extratools.item;

import com.mike.extratools.block.ModBlocks;
import com.mike.extratools.client.ColorFontEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

/**
 * 抗战方块物品类
 * 提供彩色字体效果
 */
public class KangzhanBlockItem extends BlockItem {
    
    public KangzhanBlockItem(Block block, Properties properties) {
        super(block, properties);
    }
    
    @Override
    public Component getName(ItemStack stack) {
        // 返回彩色的物品名称，这会在快捷栏、物品栏等地方显示
        return ColorFontEffects.createColorText("抗日战争胜利80周年");
    }
    
    @Override
    public Component getDescription() {
        // 物品的描述信息（可选）
        return Component.literal("抗战纪念方块");
    }
}