# Consumo

### Insomnia

    http://localhost:8080/mail-sender/send-mail

![image](https://github.com/AlexGod05/mail-sender/assets/56901230/89e92064-9b9e-41f0-88ae-20efcc1b6645)

    http://localhost:8080/mail-sender/send-mail-with-file

![image](https://github.com/AlexGod05/mail-sender/assets/56901230/f67ad5a6-d745-41e2-ac6f-fad90c201184)


# Integración con Eureka

A continuación, podremos ver un pequeño resumen de la integración y configuración de Eureka con este servicio de MailSender, también podremos ver errores que he encontrado en el camino y su solución.

## Error al conectar con Eureka
> Normalmente se soluciona apuntando correctamente al servidor de eureka

![enter image description here](https://github.com/AlexGod05/mail-sender/assets/56901230/def6f0ae-9316-4b02-bbad-a381c751398e)   

    Caused by: org.apache.hc.client5.http.HttpHostConnectException: Connect to http://localhost:8761 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: no further information


 ## Conexión con Eureka
 1. Agregar la dependencia
 -  `implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'`
![enter image description here](https://github.com/AlexGod05/mail-sender/assets/56901230/5ae7bcd7-78f5-4dfd-b269-a50437cea192)

	**Refrescar el proyecto para que descargue las dependencias nuevas.**


2. Agregar anotación en la clase principal MailSenderApplication
- `@EnableDiscoveryClient`
![enter image description here](https://github.com/AlexGod05/mail-sender/assets/56901230/194cf752-6f95-42dc-9870-f1c98a8028f8)

## Finalizando Eureka

Aca podemos visualizar que nuestro servicio de MailSender se registro como una instancia en el servidor de Eureka.
![enter image description here](https://github.com/AlexGod05/mail-sender/assets/56901230/b5014111-e7af-484c-bcf6-e50878994a56)
