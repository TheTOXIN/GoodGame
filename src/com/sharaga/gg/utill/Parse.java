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

    //Парсинг дейтаграммы в правило
    public static Rule getRule(DatagramPacket packet) {
        return getRule(getStr(packet));
    }

    //Парсинг строчки в соситояние из пакета
    public static State getStat(String data) {
        for (State state : State.values()) {
            if (data.equals(state.name())) {
                return state;
            }
        }
        return null;
    }

    //Парсинг сообщение из пакета
    public static String getMes(String data) {
        return data.substring(3, data.indexOf(Rule.END.name()));
    }

    //Парсинг сообщение из дейтаграммы
    public static String getMes(DatagramPacket packet) {
        return getMes(getStr(packet));
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
