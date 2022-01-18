# ExchangeRate
Тестовое задание от "Альфа Банк" по отслеживанию курса двух валют

Задача:
Создать сервис, который обращается к сервису курсов валют и отдаёт gif в ответ:
   - если курс по отношению к рублю за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
   - если ниже - отсюда https://giphy.com/search/broke
   
REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

> На момент получения задания по следующим Must have требованиям я не подходил, поэтому решил написать сервис используя только Http servlets:
Spring Boot 2, Spring MVC, Gradle, Feign для связи с внешними сервисами, тесты на сервисы
