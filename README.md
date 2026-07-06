# Ramas-Repo

Boton Actualizar
Se dividió en dao y controller
En el Dao se encontraba todo el apartado de conexcion usando try y catch para que las conexiones se cierren de manera automática
Se agregó un if que enviaba un mensaje en caso de no tener conexión
Instruccion query para actualizar en DAO: "UPDATE productos SET nombre=?, tipo_cafe=?, precio=? WHERE id=?"

Para el controller se uso metodos para cargar la base de datos y con un objeto de la clase produtos el cual guardaba los datos de la tabla
se cargaban a los textfield para posteriormente modificarlos

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
