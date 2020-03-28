package com.opencart.tools;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.logging.Logger;

public class Listener {

    @Step("{0}")
    public static void info(String message){
        Logger.getAnonymousLogger().info(message);
    }
}
