package me.bluedragonplayz2.dragonassistance.JDAcommand;
import me.bluedragonplayz2.dragonassistance.Config;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Commandmanager {


    private final List<ICommand> commands = new ArrayList<>();
    private void addCommand(ICommand cmd){
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
        if (nameFound){
            throw new IllegalArgumentException("A command with this name is already present");

        }
        commands.add(cmd);
    }
    @Nullable
    private ICommand getCommand(String search){
        String searchLower = search.toLowerCase();
        for (ICommand cmd : this.commands){
            if(cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)){
                return cmd;
            }
        }
        return null;
    }
    public void handle(GuildMessageReceivedEvent event){
        String[] spilt = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")),"")
                .split("\\s+");
        String invoke = spilt[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);
        if(cmd != null){
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(spilt).subList(1, spilt.length);
            Commandcontext ctx = new Commandcontext(event, args);
        }
    }
}
