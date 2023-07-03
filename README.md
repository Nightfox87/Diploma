# Дипломный проект по профессии «Инженер по тестированию»
В данном дипломном проекте проводится ручное и автоматизированное тестирование мобильного приложения "Мобильный хоспис".
Основной целью дипломного проекта является закрепление знаний и навыков по автоматизированному тестированию мобильных приложений.

## Запуск тестов и формирование отчетности
1. Склонировать [проект](https://github.com/Nightfox87/Diploma) в локальный репозиторий
2. Открыть проект в Android Studio
3. Запустить эмулятор с API 29 или подключить тестовое устройство
4. Запустить тесты командой control+shift+R или нажав правой кнопкой на папку ru.iteco.fmhandroid.ui и выбрав Run tests in 'ru.iteco.fmhandroid.ui'.
   Однако некоторые тесты и само приложение работают нестабильно, поэтому желательно запускать каждый тест отдельно.
5. Для формирования отчетности с помощью Device File Explorer необходимо найти папку с названием пакета ru.iteco.fmhandroid.ui, синхронизировать отчеты в подкаталоге allure-results, и выгрузить обновленный отчет в корневой каталог.
   Далее в терминале по адресу корневого каталога ввести команду allure serve.
