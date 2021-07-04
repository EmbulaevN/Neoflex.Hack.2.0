package ru.neoflex.telegrambot.bot;

import com.vdurmont.emoji.EmojiParser;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.neoflex.telegrambot.request.coin.Coin;
import ru.neoflex.telegrambot.request.coin.CoinsMap;
import ru.neoflex.telegrambot.request.controller.GetAllCoins;
import ru.neoflex.telegrambot.request.controller.GetNCoins;
import ru.neoflex.telegrambot.request.controller.GetOneCoin;
import ru.neoflex.telegrambot.request.util.Constant;

import java.io.IOException;
import java.util.List;

public class DevDucksCryptoBot extends TelegramLongPollingBot {

    private static final Logger log = Logger.getLogger(DevDucksCryptoBot.class);

    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            long chat_id = update.getMessage().getChatId();
            String message_text = update.getMessage().getText();

            String user_username = update.getMessage().getChat().getUserName();
            String user_first_name = update.getMessage().getChat().getFirstName();

            String emojiSmile = EmojiParser.parseToUnicode(":smile:" + ":smile:" + ":smile:");
            String emojiAlien = EmojiParser.parseToUnicode(":alien:");

            if(message_text.contains("/get")) {
                String count = message_text.replaceAll("/get", "").trim();

                try {
                    List<Coin> coins = GetNCoins.run(count);

                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(coins.toString()
                                    .replaceAll(",", "")
                                    .replaceAll("]", "")
                                    .replaceAll("\\[", "").trim());

                    try {
                        execute(message); // Sending our message object to user
                        log.info(user_username + " | send message | " + message_text);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            } else if(message_text.equals("/start")) {

                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Hello, " + user_first_name + "!" + emojiAlien + "\n" +
                                "Welcome to Crypto Currency bot-chat." + "\n" +
                                "Here you can get info about crypto currency in real time." + "\n" +
                                "To start writing a some string." + "\n"+
                                "For example /cucumber" + "\n" + "\n" +
                                "If you have some problems. Please!" + "\n" +
                                "Write me @EmbulaevN" + "\n" + "\n" +
                                "Thanks. Good luck! " + emojiSmile + "\n" +
                                "Created by ©DevDucks Team");

                try {
                    execute(message); // Sending our message object to user
                    log.info("User join : " + user_username);
                    log.info(user_username + " | send message | " + message_text);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (message_text.equals("/all")) {
                try {
                    List<Coin> coin = GetAllCoins.getAllSymbols();

                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(coin.toString()
                                    .replaceAll(",", "")
                                    .replaceAll("]", "")
                                    .replaceAll("\\[", ""));

                    try {
                        execute(message); // Sending our message object to user
                        log.info(user_username + " | send message | " + message_text);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (!CoinsMap.mapOfCoins.containsKey(message_text.toUpperCase())) {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(Constant.ERROR_VAR);

                    try {
                        execute(message); // Sending our message object to user
                        log.info(user_username + " | send message | " + message_text);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        Coin coin1 = new Coin();
                        String coin = GetOneCoin.getSymbolInfo(message_text.toUpperCase(), coin1);

                        String answer = coin;

                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setText(answer);

                        try {
                            execute(message); // Sending our message object to user
                            log.info(user_username + " | send message | " + message_text);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    // Return bot username
    public String getBotUsername() {
        return "DevDucksCryptoBot";
    }

    public String getBotToken() {
        // Return bot token from BotFather
        return "1897445530:AAHsF_o3-kf6dE_1LWpUyUf0j3eLxLF5NHA";
    }
}
