package com.sharaga.gg.utill;

/**
 * Договор префиксов для пакетов через протокол UDP
 */
public enum Rule {
    CON, //авторизация на сервере
    DIS,  //отлкчение от сервера
    MES, //простое сообщение
    STA, //обновление состояния nick
    NEW, //новый игрок на сервере
    MAP, //матрица карты
    END, //конец сообщение в пакете
    ERR, //это значит произошла ебанина
    TRU, //ответ на пакет == true
    FLS, //ответ на пакет == false
    EAT  //для увеличения очка =)
}
