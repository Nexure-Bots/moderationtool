/*
  ███╗   ██╗███████╗██╗  ██╗██╗   ██╗██████╗ ███████╗    ██████╗  ██████╗ ████████╗███████╗
  ████╗  ██║██╔════╝╚██╗██╔╝██║   ██║██╔══██╗██╔════╝    ██╔══██╗██╔═══██╗╚══██╔══╝██╔════╝
  ██╔██╗ ██║█████╗   ╚███╔╝ ██║   ██║██████╔╝█████╗      ██████╔╝██║   ██║   ██║   ███████╗
  ██║╚██╗██║██╔══╝   ██╔██╗ ██║   ██║██╔══██╗██╔══╝      ██╔══██╗██║   ██║   ██║   ╚════██║
  ██║ ╚████║███████╗██╔╝ ██╗╚██████╔╝██║  ██║███████╗    ██████╔╝╚██████╔╝   ██║   ███████║
  ╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝    ╚═════╝  ╚═════╝    ╚═╝   ╚══════╝

Made by Nexure Bots - dsc.gg/nexure-bots

 */

package de.nexurebots;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class moderationstool extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ban" -> {
                if (event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                    Member member = event.getOption("user").getAsMember();

                    if (member.getUser().isBot()) {
                        event.reply("`❌` » Du kannst keine Bots bannen!").setEphemeral(true).queue();
                        return;
                    }

                    member.ban(7, TimeUnit.DAYS).queue();
                    event.reply("`✅` » " + member.getAsMention() + " wurde Erfolgreich vom Server gebannt!").setEphemeral(true).queue();
                } else {
                    event.reply("`❌` » Du hast keine Berechtigungen, um dies zu tun!").setEphemeral(true).queue();
                }
            }
            case "kick" -> {
                if (event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                    Member member = event.getOption("user").getAsMember();

                    if (member.getUser().isBot()) {
                        event.reply("`❌` » Du kannst keine Bots kicken!").setEphemeral(true).queue();
                        return;
                    }

                    member.kick().queue();
                    event.reply("`✅` » " + member.getAsMention() + " wurde Erfolgreich vom Server gekickt!").setEphemeral(true).queue();
                } else {
                    event.reply("`❌` » Du hast keine Berechtigungen, um dies zu tun!").setEphemeral(true).queue();
                }
            }
        }
    }
}
