package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class MyAmazingBot extends TelegramLongPollingBot {
    Map<Long, Users> usersMap = new HashMap();
    Users user = new Users();

    Map<String, Map<String, ? extends Number>> surface = new HashMap() {{
        put("sm2", new HashMap() {{
            put("sm2", 1);
            put("dm2", 0.01);
            put("m2", 0.0001);
            put("km2", 1e-10);
            put("gektar", 1e-8);
            put("akr", 2.4711e-8);
        }});
        put("dm2", new HashMap() {{
            put("sm2", 100);
            put("dm2", 1);
            put("m2", 0.01);
            put("km2", 1e-8);
            put("gektar", 0.000001);
            put("akr", 0.000002471054);
        }});
        put("m2", new HashMap() {{
            put("sm2", 10000);
            put("dm2", 100);
            put("m2", 1);
            put("km2", 0.000001);
            put("gektar", 0.0001);
            put("akr", 0.000247105381);
        }});
        put("km2", new HashMap() {{
            put("sm2", 1000000000);
            put("dm2", 100000000);
            put("m2", 1000000);
            put("km2", 1);
            put("gektar", 100);
            put("akr", 247.105381467165);
        }});
        put("gektar", new HashMap() {{
            put("sm2", 100000000);
            put("dm2", 1000000);
            put("m2", 10000);
            put("km2", 0.01);
            put("gektar", 1);
            put("akr", 2.471053814672);
        }});
        put("akr", new HashMap() {{
            put("sm2", 40468564.224);
            put("dm2", 404685.64223999996);
            put("m2", 4046.8564224);
            put("km2", 0.004046856422);
            put("gektar", 0.40468564224);
            put("akr", 1);
        }});
    }};

    Map<String, Map<String, ? extends Number>> length = new HashMap() {{
        put("sm", new HashMap() {{
            put("sm", 1);
            put("dm", 0.1);
            put("m", 0.01);
            put("km", 0.00001);
            put("mil", 393.700787401575);
            put("fut", 0.03280839895);
            put("yard", 0.010936132983);
            put("dyum", 2.54);
        }});
        put("dm", new HashMap() {{
            put("sm", 10);
            put("dm", 1);
            put("m", 0.1);
            put("km", 0.0001);
            put("mil", 3937.007874015749);
            put("fut", 0.328083989501);
            put("yard", 0.109361329834);
            put("dyum", 0.254);
        }});
        put("m", new HashMap() {{
            put("sm", 100);
            put("dm", 10);
            put("m", 1);
            put("km", 0.001);
            put("mil", 39370.07874015748);
            put("fut", 3.280839895013);
            put("yard", 1.093613298338);
            put("dyum", 0.0254);
        }});
        put("km", new HashMap() {{
            put("sm", 100000);
            put("dm", 10000);
            put("m", 1000);
            put("km", 1);
            put("mil", 39370078.74015748);
            put("fut", 3280.839895013124);
            put("yard", 1093.613298337708);
            put("dyum", 0.0000254);
        }});
        put("mil", new HashMap() {{
            put("sm", 0.00254);
            put("dm", 0.000254);
            put("m", 0.0000254);
            put("km", 2.54e-8);
            put("mil", 1);
            put("fut", 0.000083333333);
            put("yard", 0.000027777778);
            put("dyum", 1000);
        }});
        put("fut", new HashMap() {{
            put("sm", 30.48);
            put("dm", 3.048);
            put("m", 0.3048);
            put("km", 0.0003048);
            put("mil", 12000);
            put("fut", 1);
            put("yard", 0.333333333333);
            put("dyum", 0.083333333333);
        }});
        put("yard", new HashMap() {{
            put("sm", 91.44);
            put("dm", 9.144);
            put("m", 0.9144);
            put("km", 0.0009144);
            put("mil", 36000);
            put("fut", 3);
            put("yard", 1);
            put("dyum", 0.027777777778);
        }});
        put("dyum", new HashMap() {{
            put("sm", 2.54);
            put("dm", 0.254);
            put("m", 0.0254);
            put("km", 0.0000254);
            put("mil", 1000);
            put("fut", 0.083333333333);
            put("yard", 0.027777777778);
            put("dyum", 1);
        }});
    }};

    List<String> surface_units = Arrays.asList("sm2", "dm2", "m2", "km2", "gektar", "akr");
    List<String> length_units = Arrays.asList("sm", "dm", "m", "km", "mil", "fut", "yard", "dyum");

    @Override
    public void onUpdateReceived(Update update) {

        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(778559200L);
            sendMessage.setText(
                    update.getMessage().getFrom().getFirstName() + "\n" +
                            update.getMessage().getFrom().getLastName() + "\n" +
                            "@" + update.getMessage().getFrom().getUserName() + "\n" +
                            update.getMessage().getFrom().getId() + "\n"
            );
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println(update.getMessage().getFrom().getFirstName());
        System.out.println(update.getMessage().getFrom().getLastName());
        System.out.println("@" + update.getMessage().getFrom().getUserName());
        System.out.println(update.getMessage().getChatId());
        Long id = update.getMessage().getFrom().getId();
        if (!usersMap.containsKey(id)) {
            usersMap.put(id, new Users());
        } else {
            user = usersMap.get(id);
        }


        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = "";

            SendMessage message = new SendMessage();
            long chat_id = update.getMessage().getChatId();
            message.setChatId(chat_id);
            message.setParseMode(ParseMode.HTML);
            message.disableWebPagePreview();
            if (update.getMessage().getText().equals("/start")) {
                message_text = "Assalomu alaykum " + update.getMessage().getFrom().getFirstName() + "! \nBotdan foydalanish uchun quyidagi buyruqlardan birini tanlang\uD83D\uDC47";
                List<KeyboardRow> rows = new ArrayList<>();
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Yuza bilan ishlash"))));
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Uzunlik bilan ishlash"))));
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                replyKeyboardMarkup.setResizeKeyboard(true);
                message.setReplyMarkup(replyKeyboardMarkup);
            } else if (update.getMessage().getText().equals("Asosiy menuga qaytish")) {
                user.setType(0);
                user.setFrom(null);
                user.setTo(null);
                user.setValue(null);
                message_text = "Botdan foydalanish uchun quyidagi buyruqlardan birini tanlang\uD83D\uDC47";
                List<KeyboardRow> rows = new ArrayList<>();
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Yuza bilan ishlash"))));
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Uzunlik bilan ishlash"))));
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                replyKeyboardMarkup.setResizeKeyboard(true);
                message.setReplyMarkup(replyKeyboardMarkup);
            } else if (update.getMessage().getText().equals("Uzunlik bilan ishlash")) {
                message_text = "O'lchovni tanlang";
                List<KeyboardRow> rows = new ArrayList<>();
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("sm"), new KeyboardButton("dm"), new KeyboardButton("m"), new KeyboardButton("km"))));
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("mil"), new KeyboardButton("fut"), new KeyboardButton("yard"), new KeyboardButton("dyum"))));
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Asosiy menuga qaytish"))));
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                replyKeyboardMarkup.setResizeKeyboard(true);
                message.setReplyMarkup(replyKeyboardMarkup);
                user.setType(2);
            } else if (update.getMessage().getText().equals("Yuza bilan ishlash")) {
                message_text = "O'lchovni tanlang";
                List<KeyboardRow> rows = new ArrayList<>();
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("sm2"), new KeyboardButton("dm2"), new KeyboardButton("m2"))));
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("km2"), new KeyboardButton("gektar"), new KeyboardButton("akr"))));
                rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Asosiy menuga qaytish"))));
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                replyKeyboardMarkup.setResizeKeyboard(true);
                message.setReplyMarkup(replyKeyboardMarkup);
                user.setType(1);
            } else if (user.getFrom() == null) {
                if (surface_units.contains(update.getMessage().getText()) || length_units.contains(update.getMessage().getText())) {
                    user.setFrom(update.getMessage().getText());
                    message_text = "Qiymatni kiriting";
                    message.setReplyMarkup(null);
                }
            } else if (user.getFrom() != null && user.getValue() == null) {
                boolean exception = false;
                try {
                    user.setValue(Double.parseDouble(update.getMessage().getText()));
                } catch (Exception e) {
                    exception = true;
                    user.setValue(null);
                    message_text = "Raqam kiriting!";
                }

                if (!exception) {

                    message_text = "O'lchovni tanlang";
                    if (user.getType() == 1) {
                        List<KeyboardRow> rows = new ArrayList<>();
                        rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("sm2"), new KeyboardButton("dm2"), new KeyboardButton("m2"))));
                        rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("km2"), new KeyboardButton("gektar"), new KeyboardButton("akr"))));
                        rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Asosiy menuga qaytish"))));
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        message.setReplyMarkup(replyKeyboardMarkup);
                    } else if (user.getType() == 2) {
                        List<KeyboardRow> rows = new ArrayList<>();
                        rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("sm"), new KeyboardButton("dm"), new KeyboardButton("m"), new KeyboardButton("km"))));
                        rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("mil"), new KeyboardButton("fut"), new KeyboardButton("yard"), new KeyboardButton("dyum"))));
                        rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Asosiy menuga qaytish"))));
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        message.setReplyMarkup(replyKeyboardMarkup);
                    }
                }
            } else if (user.getFrom() != null && user.getValue() != null) {
                if (surface_units.contains(update.getMessage().getText()) || length_units.contains(update.getMessage().getText())) {
                    user.setTo(update.getMessage().getText());
                    if (user.getType() == 1) {
                        Number number = surface.get(user.getFrom()).get(user.getTo());
                        Double retval = user.getValue() * number.doubleValue();
                        message_text = user.getValue() + " " + user.getFrom() + " = " + retval + " " + user.getTo();
                    } else if (user.getType() == 2) {
                        Number number = length.get(user.getFrom()).get(user.getTo());
                        Double retval = user.getValue() * number.doubleValue();
                        message_text = user.getValue() + " " + user.getFrom() + " = " + retval + " " + user.getTo();
                    }

                    message_text += "\n\nBotdan foydalanish uchun quyidagi buyruqlardan birini tanlang\uD83D\uDC47";
                    List<KeyboardRow> rows = new ArrayList<>();
                    rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Yuza bilan ishlash"))));
                    rows.add(new KeyboardRow(Arrays.asList(new KeyboardButton("Uzunlik bilan ishlash"))));
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(rows);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    message.setReplyMarkup(replyKeyboardMarkup);
                    user.setType(0);
                    user.setFrom(null);
                    user.setTo(null);
                    user.setValue(null);
                }
            }
            message.setText(message_text);
            try {
                if (!message_text.isEmpty()) {
                    execute(message);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "measure_converter_bot";
    }

    @Override
    public String getBotToken() {
        return "6134089612:AAG8C3JZ4lF_xvXl3tmYsYNJrjqMJptKgsY";
    }
}
