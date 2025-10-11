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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ModerationTool extends ListenerAdapter {

    private static final String NO_PERMISSION = "`❌` » Du hast keine Berechtigungen, um dies zu tun!";
    private static final String CANNOT_ACTION_BOT = "`❌` » Du kannst keine Bots moderieren!";
    private static final String USER_NOT_FOUND = "`❌` » Der angegebene Nutzer konnte nicht gefunden werden!";
    private static final String MISSING_BOT_PERMISSIONS = "`❌` » Der Bot hat nicht die nötigen Rechte, um das zu tun!";

    private static final String LOG_CHANNEL_ID = "DEINE_LOG_CHANNEL_ID";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {

            case "ban" -> {
                if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                    event.reply(NO_PERMISSION).setEphemeral(true).queue();
                    return;
                }

                Member target = getTargetMember(event);
                if (target == null) {
                    event.reply(USER_NOT_FOUND).setEphemeral(true).queue();
                    return;
                }

                if (target.getUser().isBot()) {
                    event.reply(CANNOT_ACTION_BOT).setEphemeral(true).queue();
                    return;
                }

                if (!event.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
                    event.reply(MISSING_BOT_PERMISSIONS).setEphemeral(true).queue();
                    return;
                }

                String reason = getReason(event);
                target.ban(7, TimeUnit.DAYS)
                        .reason(reason)
                        .queue(
                                success -> event.reply("`✅` » " + target.getAsMention() + " wurde erfolgreich gebannt. Grund: `" + reason + "`").setEphemeral(true).queue(),
                                error -> event.reply("`❌` » Fehler beim Bannen: `" + error.getMessage() + "`").setEphemeral(true).queue()
                        );

                EmbedBuilder BanLog = new EmbedBuilder()
                        .setColor(Color.red)
                        .setDescription("## Ban Log \n" +
                                "> Es wurde gerade ein Mitglied vom Server gebannt! \n" +
                                "> - **gebanntes Mitglied:** " + target.getAsMention() + " \n" +
                                "> - **Moderator:** " + event.getMember().getAsMention())
                        .setFooter("Moderationtool by Nexure Bots");

                event.getGuild().getTextChannelById(LOG_CHANNEL_ID).sendMessageEmbeds(BanLog.build()).queue();
            }

            case "kick" -> {
                if (!event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                    event.reply(NO_PERMISSION).setEphemeral(true).queue();
                    return;
                }

                Member target = getTargetMember(event);
                if (target == null) {
                    event.reply(USER_NOT_FOUND).setEphemeral(true).queue();
                    return;
                }

                if (target.getUser().isBot()) {
                    event.reply(CANNOT_ACTION_BOT).setEphemeral(true).queue();
                    return;
                }

                if (!event.getGuild().getSelfMember().hasPermission(Permission.KICK_MEMBERS)) {
                    event.reply(MISSING_BOT_PERMISSIONS).setEphemeral(true).queue();
                    return;
                }

                String reason = getReason(event);
                target.kick()
                        .reason(reason)
                        .queue(
                                success -> event.reply("`✅` » " + target.getAsMention() + " wurde erfolgreich gekickt. Grund: `" + reason + "`").setEphemeral(true).queue(),
                                error -> event.reply("`❌` » Fehler beim Kicken: `" + error.getMessage() + "`").setEphemeral(true).queue()
                        );

                EmbedBuilder KickLog = new EmbedBuilder()
                        .setColor(Color.red)
                        .setDescription("## Kick Log \n" +
                                "> Es wurde gerade ein Mitglied vom Server gekickt! \n" +
                                "> - **gekicktes Mitglied:** " + target.getAsMention() + " \n" +
                                "> - **Moderator:** " + event.getMember().getAsMention())
                        .setFooter("Moderationtool by Nexure Bots");

                event.getGuild().getTextChannelById(LOG_CHANNEL_ID).sendMessageEmbeds(KickLog.build()).queue();
            }
        }
    }

    private Member getTargetMember(SlashCommandInteractionEvent event) {
        OptionMapping userOption = event.getOption("user");
        return userOption != null ? userOption.getAsMember() : null;
    }

    private String getReason(SlashCommandInteractionEvent event) {
        OptionMapping reasonOption = event.getOption("grund");
        return reasonOption != null ? reasonOption.getAsString() : "Kein Grund angegeben";
    }
}
