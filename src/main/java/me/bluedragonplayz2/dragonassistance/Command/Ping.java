package me.bluedragonplayz2.dragonassistance.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.JDA;

public class Ping extends Command {

    public Ping(){
        this.name = "Ping";
        this.help = "See gateway and api pings";
    }

    @Override
    protected void execute(CommandEvent e) {
        JDA jda = e.getJDA();
        jda.getRestPing().queue( (time)  ->
                e.getChannel().sendMessageFormat("Rest ping: %d ms", time).queue()
        );
        long ping = jda.getGatewayPing();
        e.getChannel().sendMessageFormat("websocket ping: %d ms", ping).queue();
    }
}
