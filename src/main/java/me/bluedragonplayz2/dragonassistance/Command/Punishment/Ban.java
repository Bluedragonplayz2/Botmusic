package me.bluedragonplayz2.dragonassistance.Command.Punishment;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.bluedragonplayz2.dragonassistance.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public class Ban extends Command {
    public Ban(){
        this.name = "kick";
        this.help = "kick a player";
    }
    @Override
    protected void execute(CommandEvent e) {
        String[] name = e.getMessage().getContentRaw().split(" ");
        if (!name[1].isEmpty()) {
            Member member = e.getMember();
            Member target = e.getMessage().getMentionedMembers().get(0);
            if (!member.canInteract(target) || !member.hasPermission(Permission.BAN_MEMBERS)) {
                e.reply("You dont have the permission to ban" + target.getEffectiveName());
            }
            if (!e.getGuild().getSelfMember().canInteract(target) || !e.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
                e.reply("Error! I don't have the permission to ban" + target.getEffectiveName());
            }
            if (!name[2].isEmpty()) {
                e.reply("enter the amount of days to ban" + target.getEffectiveName());
            }
            int dur = Integer.parseInt(name[2]);
            if(dur == 0){
                e.reply("unable to ban" + target.getEffectiveName() + " for " + name[2] + " days");
            }
            if (!name[3].isEmpty()) {
                String reason = name[2];
                e.getGuild().ban(target, dur).reason(reason).queue();
            } else {
                e.getGuild().ban(target, dur).reason("for no reason").queue();
            }
        } else {
            e.reply("The argument for the command is " + Config.get("prefix") + "ban <mention player to be kicked> <number of days> <reason>");
        }
    }
}
