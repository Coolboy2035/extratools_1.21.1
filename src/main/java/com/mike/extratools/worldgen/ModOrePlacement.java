package com.mike.extratools.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    /**
     * 基础矿石生成配置
     * @param pCountPlacement 数量/稀有度配置
     * @param pHeightRange 高度范围配置
     * @return 配置修改器列表
     */
    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(
                pCountPlacement,          // 控制生成次数或稀有度
                InSquarePlacement.spread(), // 在区块内随机分布
                pHeightRange,              // 控制生成高度
                BiomeFilter.biome()        // 生物群系过滤
        );
    }

    /**
     * 常见矿石生成配置
     * @param pCount 每个区块尝试生成的次数
     * @param pHeightRange 高度范围
     * @return 配置修改器列表
     */
    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    /**
     * 稀有矿石生成配置
     * @param pChance 平均每多少个区块生成一次 (数字越大越稀有)
     * @param pHeightRange 高度范围
     * @return 配置修改器列表
     */
    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
