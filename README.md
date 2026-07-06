# Ramas-Repo

LD (Creacion -jar y .exe): 
1. mvn clean package

2. Intento inicial con jlink (NO recomendado)

Se intentó generar un runtime con:

jlink --module-path ... --add-modules com.example.cafeteria

Con problemas como:
PostgreSQL JDBC es un módulo automático
jlink no lo soporta correctamente
JavaFX también genera conflictos en modo modular
3. Solución final: jpackage directo

Se utilizó jpackage sin jlink:

jpackage `
--type exe `
--name Cafeteria `
--input target `
--main-jar Cafeteria-1.0.jar `
--main-class com.example.cafeteria.Launcher `
--dest output `
--win-menu `
--win-shortcut `
--vendor Byron
4. Se instalo el exe pero con una instalación compatiblecon windows
