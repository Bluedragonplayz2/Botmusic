package me.bluedragonplayz2.dragonassistance.Event;

import me.bluedragonplayz2.dragonassistance.MsSQL;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Guild_join extends ListenerAdapter {
    @Override
    public void onGuildJoin( GuildJoinEvent e) {
        MsSQL mssql = new MsSQL();
        mssql.SQL_test();
        mssql.SQL_ins("Guild_ID:" + e.getGuild().getId());
    }
}
