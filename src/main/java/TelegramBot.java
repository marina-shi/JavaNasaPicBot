import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class TelegramBot extends TelegramLongPollingBot {
    private static final String BOT_NAME = Config.botName;
    private static final String BOT_TOKEN = Config.botToken;
    private static final String URL = Config.Api;

    public TelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            switch (update.getMessage().getText()) {
                case "/help":
                    sendMessage("Hi, I am NASA bot! Write /start " +
                            "or /give for get pic of the day", update.getMessage().getChatId());
                    break;
                case "/start":
                    sendMessage("Hi, I am NASA bot! Write /give " +
                            "for get pic of the day", update.getMessage().getChatId());
                    break;
                case "/give":
                    String url;
                    try {
                        url = Utils.getUrl(URL);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    sendMessage(url, update.getMessage().getChatId());
                    break;
                default:
                    sendMessage("Don't understand your command", update.getMessage().getChatId());
            }
        }
    }

    private void sendMessage(String text, long chatId) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        // TODO
        return BOT_TOKEN;
    }
}
