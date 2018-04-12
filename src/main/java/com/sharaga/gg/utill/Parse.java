package com.sharaga.gg.utill;

import java.net.DatagramPacket;

/**
 * Статический класс для парсинга пакетов
 */
public class Parse {
    //Парсинг строчки в правило из пакета
    public static Rule getRule(String data) {
        data = data.substring(0, 3);
        for (Rule rule : Rule.values()) {
            if (data.equals(rule.name())) {
                return rule;
            }
        }
        return null;
    }

    //Имя юзера с сообщения
    public static String getName(String data) {
        return getMes(data).substring(0, getMes(data).indexOf("/"));
    }

    //Парсинг сообщение из пакета
    public static String getMes(String data) {
        return data.substring(3, data.indexOf(Rule.END.name()));
    }

    //Парсинг пакета байтов в строчку
    public static String getStr(DatagramPacket packet) {
        return new String(packet.getData());
    }

    //Сборка пакета из правила и сообщения
    public static String build(Rule rule, String mes) {
        return rule.name() + mes + Rule.END;
    }
}
