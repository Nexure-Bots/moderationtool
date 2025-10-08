# Moderationtool

Ein simples Moderationstool mit den grundlegenden Befehlen **Kick** und **Ban**, umgesetzt mit [JDA 5.0.0-alpha.21](https://github.com/DV8FromTheWorld/JDA).  
Ideal als Einstieg oder Grundlage für eigene Discord-Bots mit Java 22.

---

## ⚙️ Features

- `/kick` – Entfernt ein Mitglied vom Server
- `/ban` – Bannt ein Mitglied dauerhaft vom Server

---

## 💡 Voraussetzungen

- Java 22 installiert  
- Maven oder Gradle
- Ein Discord-Bot mit aktiviertem Privileged Gateway Intent `GUILD_MEMBERS`
- Ein Server, auf dem der Bot die Berechtigungen `Kick Members` und `Ban Members` besitzt
- Slash Commands muss man selbstständig im Bot registrieren

## ❓ Beispiel einer Slash Command Registrierung
````
Commands.slash("ban", "Bannt einen Nutzer.")
.addOption(OptionType.USER, "user", "Der zu bannende Nutzer", true)
.addOption(OptionType.STRING, "grund", "Grund für den Bann", false);

Commands.slash("kick", "Kickt einen Nutzer.")
.addOption(OptionType.USER, "user", "Der zu kickende Nutzer", true)
.addOption(OptionType.STRING, "grund", "Grund für den Kick", false);
````