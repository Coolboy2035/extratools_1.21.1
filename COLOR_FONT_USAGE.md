# 彩色字体效果使用指南

## 概述

`ColorFontEffects` 类是一个独立的字体效果工具类，可以在任何物品、方块或其他需要彩色文字的地方使用。

## 基本使用方法

### 1. 导入类
```java
import com.mike.extratools.client.ColorFontEffects;
```

### 2. 创建彩色文字
```java
// 使用当前配置的颜色方案（国旗色或彩虹色）
Component coloredText = ColorFontEffects.createColorText("你的文字");

// 使用国旗配色方案
Component flagText = ColorFontEffects.createNationalFlagText("爱国文字");

// 使用彩虹配色方案
Component rainbowText = ColorFontEffects.createRainbowText("彩虹文字");

// 使用静态三色（红-金-白）
Component staticText = ColorFontEffects.createStaticTriColorText("静态彩色");

// 使用自定义渐变
Component gradientText = ColorFontEffects.createGradientText("渐变文字", 0xFF0000, 0x0000FF);
```

## 配置选项

### 切换颜色方案
```java
// 切换到国旗配色
ColorFontEffects.setColorScheme(true);

// 切换到彩虹配色
ColorFontEffects.setColorScheme(false);

// 检查当前方案
boolean isFlagScheme = ColorFontEffects.isUsingNationalFlagColors();
```

## 实际应用示例

### 1. 在方块类中使用
```java
public class YourBlock extends Block {
    
    public static Component getColoredName() {
        return ColorFontEffects.createColorText("特殊方块名称");
    }
    
    @Override
    protected ItemInteractionResult useItemOn(...) {
        // 在交互时显示彩色消息
        if (level.isClientSide()) {
            player.sendSystemMessage(ColorFontEffects.createColorText("交互成功！"));
        }
        return ItemInteractionResult.SUCCESS;
    }
}
```

### 2. 在物品类中使用
```java
public class YourItem extends Item {
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipComponents, flag);
        // 添加彩色提示信息
        tooltipComponents.add(ColorFontEffects.createStaticTriColorText("★ 特殊物品 ★"));
    }
}
```

### 3. 在事件处理器中使用
```java
public class YourEventHandler {
    
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        
        if (stack.getItem() == YourItems.SPECIAL_ITEM.get()) {
            // 替换物品名称为彩色版本
            if (!event.getToolTip().isEmpty()) {
                event.getToolTip().set(0, ColorFontEffects.createColorText("特殊物品名称"));
            }
        }
    }
}
```

## 可用的颜色效果

### 1. 国旗配色 (createNationalFlagText)
- 深红 → 红色 → 金色 → 橙黄 → 深红
- 3.5秒循环
- 适合爱国主题

### 2. 彩虹配色 (createRainbowText)
- 完整彩虹光谱
- 4秒循环  
- 视觉效果丰富

### 3. 静态三色 (createStaticTriColorText)
- 红 → 金 → 白 循环
- 不随时间变化
- 简洁明了

### 4. 自定义渐变 (createGradientText)
- 任意两种颜色之间渐变
- 静态效果
- 高度可定制

## 颜色代码示例

```java
// 常用颜色
int RED = 0xFF0000;        // 红色
int GREEN = 0x00FF00;      // 绿色
int BLUE = 0x0000FF;       // 蓝色
int GOLD = 0xFFD700;       // 金色
int WHITE = 0xFFFFFF;      // 白色
int BLACK = 0x000000;      // 黑色
int FLAG_RED = 0xDE2910;   // 国旗红
int FLAG_YELLOW = 0xFFDE00; // 国旗黄
```

## 注意事项

1. **客户端限制**: 彩色字体效果只在客户端有效，服务端无法显示
2. **性能考虑**: 流动效果会实时计算，大量使用可能影响性能
3. **兼容性**: 确保在客户端事件中使用，避免在服务端调用
4. **字体样式**: 所有彩色效果默认使用粗体 (withBold(true))

## 配置文件支持

将来可以通过配置文件来控制默认颜色方案：

```java
// 示例配置读取
private static boolean readConfig() {
    // 从配置文件读取设置
    boolean useFlagColors = Config.USE_FLAG_COLORS.get();
    ColorFontEffects.setColorScheme(useFlagColors);
    return useFlagColors;
}
```