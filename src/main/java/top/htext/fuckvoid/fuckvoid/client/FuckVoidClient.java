package top.htext.fuckvoid.fuckvoid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FuckVoidClient implements ClientModInitializer {
    public static final Logger logger = LogManager.getLogger("FuckVoid");

    @Override
    public void onInitializeClient() {
        logger.info("Successfully loaded. And fuck you, the void!");

        // Reminded player.
        ClientTickEvents.END_CLIENT_TICK.register((MinecraftClient client) -> {
            var player = client.player;
            if (player != null && !player.isCreative() && !player.isSpectator() && player.getY() < 0) {
                client.inGameHud.setTitles(new TranslatableText("title.fuckvoid.warning").formatted(Formatting.RED), null, 0, 1, 0);
                player.playSound(new SoundEvent(new Identifier("minecraft", "entity.arrow.hit_player")), SoundCategory.AMBIENT, 1.0f, 1.0f);
            }
        });
    }
}
