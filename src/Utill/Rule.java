package Utill;

/**
 * Договор префиксов для пакетов через протокол UDP
 */
public enum Rule {
    CON, //авторизация на сервере
    MES, //простое сообщение
    STA, //обновление состояния player
    END, //конец сообщение в пакете
    TRU, //ответ на пакет == true
    FLS //ответ на пакет == false
}
