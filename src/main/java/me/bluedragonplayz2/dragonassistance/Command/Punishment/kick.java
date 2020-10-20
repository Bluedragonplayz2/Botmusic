package me.bluedragonplayz2.dragonassistance.Command.Punishment;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.bluedragonplayz2.dragonassistance.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;



public class kick extends Command {
    public kick(){
        this.name = "kick";
        this.help = "kick a player";
    }
    @Override
    protected void execute(CommandEvent e) {
        String[] name = e.getMessage().getContentRaw().split(" ");
        if(!name[1].isEmpty()){
            Member member = e.getMember();
            Member target = e.getMessage().getMentionedMembers().get(0);
            if(!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)){
                e.reply("You dont have the permission to kick" + target.getEffectiveName());
            }
            if (!e.getGuild().getSelfMember().canInteract(target) || !e.getGuild().getSelfMember().hasPermission(Permission.KICK_MEMBERS)){
                e.reply("Error! I don't have the permission to kick" + target.getEffectiveName());
            }
            if(!name[2].isEmpty()){
                String reason = name[2];
                e.getGuild().kick(target).reason(reason).queue();
            }else{
                e.getGuild().kick(target).reason("for no reason").queue();
            }
        }else{
            e.reply("The argument for the command is "+ Config.get("prefix")+"kick <mention player to be kicked>");
        }
    }
}
