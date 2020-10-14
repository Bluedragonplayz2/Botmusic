package me.bluedragonplayz2.dragonassistance;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Listerner extends ListenerAdapter {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Listerner.class);

    @Override
    public void onReady( ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMessageReceived( GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        if(user.isBot() || event.isWebhookMessage()){
            return;
        }
        String prefix = Config.get("prefix");
        String raw = event.getMessage().getContentRaw();
        if(raw.equalsIgnoreCase(prefix+"shutdown")
                && user.getId().equals(Config.get("owner_id"))){
            event.getChannel().sendMessage("shutdown in:").queue();
            event.getChannel().sendMessage("5").queue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("4").queue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("3").queue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("2").queue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("1").queue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("goodbye").queue();
            LOGGER.info("Bot will now exit");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
            return;
        }




    }

}
