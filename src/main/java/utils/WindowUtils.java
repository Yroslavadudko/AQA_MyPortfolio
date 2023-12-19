package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowUtils {

    // Приватний конструктор для приховання імпліцитного публічного конструктора
    private WindowUtils() {
        // Порожнє тіло конструктора
    }

    /**
     * Використовуйте цей метод для виконання дій у новому вікні
     *
     * @param driver             екземпляр WebDriver
     * @param actionsInNewWindow дії, які слід виконати у новому вікні
     */
    public static void performActionsInNewWindow(WebDriver driver, Runnable actionsInNewWindow) {
        // Зберігаємо ідентифікатор основного вікна
        String mainWindowHandle = driver.getWindowHandle();

        // Отримуємо всі ідентифікатори вікон
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Переключаємося на кожне нове вікно
        for (String windowHandle : allWindowHandles) {
            driver.switchTo().window(windowHandle);
            // Викликаємо дії у новому вікні
            actionsInNewWindow.run();
            // Закриваємо нове вікно
            driver.close();
        }

        // Повертаємося до основного вікна
//        driver.switchTo().window(mainWindowHandle);
    }
}


