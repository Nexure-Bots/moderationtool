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

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;


public class Main {

    public static void main(String[] args) {

        String token = "DEIN_BOT_TOKEN";
        String status = "DEIN_GEWÜNSCHTER_BOTSTATUS";

        JDABuilder jda = JDABuilder.createDefault(token)
                .setActivity(Activity.playing(status))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(
                        new ModerationTool(),
                        new SlashCommandsRegister()
                )
                .enableIntents(GatewayIntent.GUILD_MEMBERS, new GatewayIntent[]{GatewayIntent.GUILD_PRESENCES, GatewayIntent.MESSAGE_CONTENT})
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL);

        JDA bot = jda.build();

        System.out.println("Dein Discord Bot wurde gerade gestartet!");
    }
}