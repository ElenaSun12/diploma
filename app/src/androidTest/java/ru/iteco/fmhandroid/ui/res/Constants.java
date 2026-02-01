package ru.iteco.fmhandroid.ui.res;


public class Constants {

    // Авторизация
    public static final String DEFAULT_LOGIN = "login2";
    public static final String DEFAULT_PASSWORD = "password2";
    public static final String INVALID_LOGIN = "wrongLogin";
    public static final String INVALID_PASSWORD = "wrongPassword";
    public static final String EMPTY_LOGIN = "";
    public static final String EMPTY_PASSWORD = "";
    public static final String UPPERCASE_LOGIN = "LOGIN2";
    public static final String UPPERCASE_PASSWORD = "PASSWORD2";


    // Безопасность
    public static final String SQL_INJECTION_LOGIN = "login2 OR '1'='1'";
    public static final String SQL_INJECTION_PASSWORD = "password2 OR '1'='1'";
    public static final String XSS_LOGIN = "<script>alert('XSS');</script>";
    public static final String XSS_PASSWORD = "<script>alert('XSS');</script>";

    // Сообщения об ошибках
    public static final String ERROR_SOMETHING_WENT_WRONG = "Что-то пошло не так. Попробуйте позднее.";
    public static final String ERROR_EMPTY_LOGIN_AND_PASSWORD = "Логин и пароль не могут быть пустыми";
    public static final String TEXT_ERROR_EMPTY_FIELDS = "Заполните пустые поля";
    public static final String TEXT_MESSAGE_CHANGES_NOT_BE_SAVED = "Изменения не будут сохранены. Вы действительно хотите выйти?";
    public static final String TEXT_MESSAGE_DELETE = "Вы уверены, что хотите безвозвратно удалить документ? Данные изменения нельзя будет отменить в будущем.";

    // Таймер
    public static final int START_TIME = 5000;


    // Тексты для интерфейса
    public static final String TEXT_AUTHORIZATION = "Авторизация";
    public static final String ALL_NEWS = "ВСЕ НОВОСТИ";
    public static final String OUR_MISSION = "Наша Миссия";
    public static final String VERSION = "Версия:";
    public static final String TEXT_LOGIN = "Логин";
    public static final String ABOUT_APPLICATION = "О приложении";
    public static final String EXIT = "Выйти";
    public static final String TEXT_PASSWORD = "Пароль";
    public static final String TEXT_MISSION = "идеальное устройство мира";


    // Тексты для меню
    public static final String MENU_NEWS_TEXT = "Новости";
    public static final String MENU_MAIN_TEXT = "Главная";
}
