package com.example.coloredanvils.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void colorAnvil(CallbackInfo ci) {

        AnvilScreenHandler handler = (AnvilScreenHandler)(Object)this;

        ItemStack output = handler.getSlot(2).getStack();
        if (output.isEmpty()) return;

        String name = handler.getNewItemName();
        if (name == null || name.isEmpty()) return;

        output.setCustomName(
            Text.literal(name).styled(style -> style.withColor(0xFF0000))
        );
    }
}
