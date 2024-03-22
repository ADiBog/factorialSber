# Используем версию OpenJDK, соответствующую версии Java в проекте
FROM openjdk:17

WORKDIR /app

# Копируем .jar файл из директории в образ
COPY ./build/libs/factorialSber-0.0.1-SNAPSHOT.jar /app/factorialSber.jar

# Запускаем Java приложение
CMD ["java", "-jar", "/app/factorialSber.jar"]
