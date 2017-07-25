# Generic Discord Music Bot

A generic music bot for Discord. Made with JDA and Lavaplayer as my first Java project in an attempt to learn the language.

*Disclaimer: You should never actually setup this bot for personal use. There are better options out there. This is simply a personal project for me to learn Java.*

## Customization
Edit src/main/java/main/Constants.example.java and rename to Constants.java to customize your bot token and prefix.

**Example:**
```java
package main;

public class Constants {

  public static final String TOKEN = "<MY BOT TOKEN HERE>";

  public static final String PREFIX = "<MY PREFIX HERE>";

}
```

## Dependencies

[JDA 3.2.0_226](https://github.com/DV8FromTheWorld/JDA)

[Lavaplayer 1.2.42](https://github.com/sedmelluq/lavaplayer)

## Current Commands
```
play - Play music in your current voice channel from a URL. [play <URL>]
queue - View the current songs queue. [queue]
skip - Skip the current song. [skip]
pause - Pause the current song. [pause]
resume - Resume the current song. [resume]
summon - Send the bot to your voice channel. [summon]
```

## Authors

* **Qata** - *Literally everything* - [q-ata](https://github.com/q-ata)
Update README.md
