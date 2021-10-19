# alfa-test-task
Описание:
---
```
Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:  

если курс по отношению к рублю за сегодня стал выше вчерашнего,  
то отдаем рандомную отсюда https://giphy.com/search/rich  
если ниже - отсюда https://giphy.com/search/broke  
Ссылки  
REST API курсов валют - https://docs.openexchangerates.org/  
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide  
Must Have  
Сервис на Spring Boot 2 + Java / Kotlin  
Запросы приходят на HTTP endpoint, туда передается код валюты  
Для взаимодействия с внешними сервисами используется Feign  
Все параметры (валюта по отношению к которой смотрится курс,   
адреса внешних сервисов и т.д.) вынесены в настройки  
На сервис написаны тесты   
(для мока внешних сервисов можно использовать @mockbean или WireMock)   
Для сборки должен использоваться Gradle  
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску  
Nice to Have  
Сборка и запуск Docker контейнера с этим сервисом
```  

Только платная версия OpenExchangeRates API позволяет изменять base_currency, поэтому по умолчанию сравнивается курс доллара(USD) по отношению к рублю(RUB)
```json
{
  "error": true,
  "status": 403,
  "message": "not_allowed",
  "description": "Changing the API `base` currency is available for Developer, Enterprise and Unlimited plan clients. Please upgrade, or contact support@openexchangerates.org with any questions."
}
```

Все параметры, такие как адреса API, ключи и коды валют прописаны в `application.properties`

Для сборки проекта используется система сборки Gradle
Для реализации серивиса использовался Spring Boot, Feign и Thymeleaf
Для тестов также использовались JUnit, Mockito

Обращение к приложению:
--- Будет выполнен запрос на курсы валют сегодняшнего и предыдущего дня и далее будет показана страница в зависимости от того, вырос курс или упал.
```
GET localhost:{port}/gif
```

Для запуска приложения на локальной машине, потребуется:
* Склонировать репозиторий
* Собрать проект с помощью системы сборки Gradle
```
Если Gradle установлен локально
gradle build

Иначе и предпочтительнее
gradlew build
```
* Далее в папке `build/lib/` будет находиться искомый `.jar` файл
```
java -jar alfa-bank-test-task.jar
```
 
