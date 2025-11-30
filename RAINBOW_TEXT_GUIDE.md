# 🌈 彩色流动文字效果使用指南

## 📋 功能说明

为**抗战方块**添加了类似"寰宇支配之剑"的彩色流动字体效果！

---

## ✨ 实现的效果

### **1️⃣ 物品栏显示**
当你把鼠标悬停在抗战方块上时：
- ✅ **彩色流动文字**："抗日战争胜利80周年"
- ✅ **红金交替**：象征国旗颜色
- ✅ **动态变化**：每个字符独立变色
- ✅ **加粗效果**：文字更醒目

### **2️⃣ 世界渲染**
当你看向抗战方块时（准星对准）：
- ✅ **浮动文字**：在方块上方显示
- ✅ **面向玩家**：始终朝向你
- ✅ **发光效果**：在黑暗中也清晰可见
- ✅ **彩色流动**：同样的颜色效果

---

## 🎨 可用颜色方案

在 `RainbowTextHandler.java` 中有三种颜色方案：

### **方案 1：红金交替（当前使用）**
```java
createRainbowText("抗日战争胜利80周年", 1.0f)
```
- 适合爱国主题
- 红色和金色之间流动
- 象征国旗颜色

### **方案 2：平滑彩虹**
```java
createSmoothRainbowText("抗日战争胜利80周年", 1.0f)
```
- 七彩渐变
- 更加华丽
- 平滑过渡

### **方案 3：国旗色系**
```java
createChinaFlagText("抗日战争胜利80周年", 1.0f)
```
- 红色和黄色跳变
- 更鲜明的对比
- 纯国旗配色

---

## 🔧 如何切换颜色方案

### **修改物品栏颜色**
打开 `RainbowTextHandler.java`，找到第 29 行：
```java
MutableComponent rainbowText = createRainbowText("抗日战争胜利80周年", 1.0f);
```

改为：
```java
// 彩虹效果
MutableComponent rainbowText = createSmoothRainbowText("抗日战争胜利80周年", 1.0f);

// 或者国旗色系
MutableComponent rainbowText = createChinaFlagText("抗日战争胜利80周年", 1.0f);
```

### **修改世界渲染颜色**
打开 `BlockNameRenderer.java`，找到第 58 行：
```java
int color = ColorCycleHelper.getRedGoldColor(i * 0.5f, 1.0f);
```

改为：
```java
// 平滑彩虹
int color = ColorCycleHelper.getSmoothRainbowColor(i * 0.3f, 1.0f);

// 或者国旗色
int color = ColorCycleHelper.getChinaFlagColor(i * 0.8f, 1.0f);
```

---

## ⚙️ 调整参数

### **速度调节**
```java
createRainbowText("文字", 2.0f)  // 变化更快
createRainbowText("文字", 0.5f)  // 变化更慢
```

### **颜色偏移**
在 `ColorCycleHelper` 方法中，第一个参数控制字符间的颜色差异：
```java
getRedGoldColor(i * 1.0f, 1.0f)  // 颜色差异大
getRedGoldColor(i * 0.2f, 1.0f)  // 颜色差异小
```

### **浮动高度**
在 `BlockNameRenderer.java` 第 47 行：
```java
double y = pos.getY() + 2.0 - camera.getPosition().y;  // 更高
double y = pos.getY() + 1.0 - camera.getPosition().y;  // 更低
```

### **文字大小**
在 `BlockNameRenderer.java` 第 53 行：
```java
float scale = 0.03f;   // 更大
float scale = 0.02f;   // 更小
```

---

## 📁 文件结构

```
src/main/java/com/mike/extratools/client/
├── ColorCycleHelper.java      # 颜色计算工具
├── RainbowTextHandler.java    # 物品栏文字处理
└── BlockNameRenderer.java     # 世界文字渲染
```

---

## 🎮 游戏中效果

### **物品栏效果**
1. 打开背包
2. 找到抗战方块
3. 鼠标悬停
4. 看到彩色流动文字！

### **世界效果**
1. 放置抗战方块
2. 准星对准方块
3. 看到浮动的彩色文字！

---

## 🛠️ 技术原理

### **颜色生成**
- 使用系统时间生成动态颜色
- 每个字符有独立的偏移量
- HSV/RGB 色彩空间转换
- 正弦波实现平滑过渡

### **文字渲染**
- `ItemTooltipEvent` - 拦截物品提示
- `RenderHighlightEvent` - 在方块高亮时渲染
- 每帧重新计算颜色
- 使用 Minecraft 字体渲染器

---

## 💡 扩展用法

### **给其他方块添加效果**
在 `RainbowTextHandler.java` 的 `onItemTooltip` 方法中添加：
```java
if (blockItem.getBlock() == ModBlocks.XUANYU_BLOCK.get()) {
    event.getToolTip().clear();
    MutableComponent text = createSmoothRainbowText("张轩瑀", 1.5f);
    event.getToolTip().add(text);
}
```

### **给物品添加效果**
```java
if (stack.getItem() == ModItems.COPPER_SWORD.get()) {
    event.getToolTip().set(0, createRainbowText("传说中的铜剑", 1.0f));
}
```

---

## 🎨 预设颜色方案对比

| 方案 | 颜色 | 特点 | 推荐用途 |
|------|------|------|---------|
| 红金交替 | 🔴🟡 | 象征国旗 | 爱国主题 |
| 平滑彩虹 | 🌈 | 七彩渐变 | 传奇物品 |
| 国旗色系 | 🔴🟨 | 红黄跳变 | 纪念主题 |

---

## ⚠️ 注意事项

1. **性能影响**：颜色每帧计算，但开销很小
2. **客户端渲染**：效果仅在客户端显示
3. **兼容性**：需要 NeoForge 1.21+
4. **多人游戏**：每个玩家看到的效果相同

---

## 🚀 测试步骤

1. **编译模组**
   ```bash
   ./gradlew build
   ```

2. **运行客户端**
   ```bash
   ./gradlew runClient
   ```

3. **进入游戏**
   - 创造模式打开物品栏
   - 找到"更多同学"标签页
   - 查看抗战方块效果

4. **测试世界渲染**
   - 放置抗战方块
   - 准星对准方块
   - 观察浮动文字

---

**作者**: Mike Z  
**创建日期**: 2025年  
**效果灵感**: 寰宇支配之剑
