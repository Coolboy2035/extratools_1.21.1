package com.mike.extratools.client;

import com.mike.extratools.ExtraToolsMod;
import com.mike.extratools.block.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import org.joml.Matrix4f;

/**
 * 方块名称渲染器
 * 在玩家看向抗战方块时渲染彩色流动文字
 */
@EventBusSubscriber(modid = ExtraToolsMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.FORGE)
public class BlockNameRenderer {
    
    @SubscribeEvent
    public static void onRenderBlockHighlight(RenderHighlightEvent.Block event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        
        HitResult hitResult = mc.hitResult;
        if (hitResult == null || hitResult.getType() != HitResult.Type.BLOCK) return;
        
        BlockHitResult blockHit = (BlockHitResult) hitResult;
        BlockPos pos = blockHit.getBlockPos();
        BlockState state = mc.level.getBlockState(pos);
        
        // 检查是否是抗战方块
        if (state.getBlock() == ModBlocks.KANGZHAN_BLOCK.get()) {
            renderFloatingText(event.getPoseStack(), event.getMultiBufferSource(), 
                              event.getCamera(), pos, "抗日战争胜利80周年");
        }
    }
    
    /**
     * 渲染浮动文字
     */
    private static void renderFloatingText(PoseStack poseStack, MultiBufferSource buffer, 
                                          Camera camera, BlockPos pos, String text) {
        Minecraft mc = Minecraft.getInstance();
        Font font = mc.font;
        
        poseStack.pushPose();
        
        // 移动到方块上方
        double x = pos.getX() + 0.5 - camera.getPosition().x;
        double y = pos.getY() + 1.5 - camera.getPosition().y;
        double z = pos.getZ() + 0.5 - camera.getPosition().z;
        
        poseStack.translate(x, y, z);
        
        // 面向玩家
        poseStack.mulPose(camera.rotation());
        
        // 缩放
        float scale = 0.025f;
        poseStack.scale(-scale, -scale, scale);
        
        Matrix4f matrix = poseStack.last().pose();
        
        // 渲染每个字符
        float totalWidth = font.width(text);
        float xOffset = -totalWidth / 2f;
        
        for (int i = 0; i < text.length(); i++) {
            String character = String.valueOf(text.charAt(i));
            int color = ColorCycleHelper.getRedGoldColor(i * 0.5f, 1.0f);
            
            // 添加发光效果
            font.drawInBatch(character, xOffset, 0, color | 0xFF000000, false, 
                           matrix, buffer, Font.DisplayMode.NORMAL, 0x40000000, 15728880);
            
            xOffset += font.width(character);
        }
        
        poseStack.popPose();
    }
}
