# Product Management Service
Este README proporciona los pasos necesarios para clonar este repositorio y levantar el microservicio junto con sus servicios de base de datos utilizando Docker Compose.

Requisitos Previos
Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu sistema:

* Git: Para clonar el repositorio.

* Docker Desktop (o Docker Engine y Docker Compose): Para construir y correr los contenedores Docker.

* Java Development Kit (JDK) 17 o superior: Necesario si planeas compilar o ejecutar el microservicio directamente fuera de Docker (aunque para levantarlo con Docker Compose, solo es necesario dentro del contenedor).

* Maven 3.6 o superior: Necesario si necesitas construir el JAR de la aplicación localmente (el Dockerfile asume que el JAR ya está construido o que tiene las instrucciones para construirlo dentro del contenedor).

## Pasos para Clonar y Levantar el Microservicio
Sigue los siguientes pasos para poner en marcha el microservicio y su base de datos:

1. Clonar el Repositorio
   Abre tu terminal o línea de comandos y ejecuta el siguiente comando para clonar el repositorio del microservicio:

git clone https://github.com/oskyng/product-management-service.git
cd product-management-service

2. Verificar la Existencia de Dockerfile y docker-compose.yml
   Asegúrate de que los archivos Dockerfile (para construir la imagen de tu microservicio) y docker-compose.yml (para orquestar los servicios) existen en el directorio raíz del proyecto que acabas de clonar.

* Dockerfile: Contiene las instrucciones para construir la imagen Docker de tu aplicación Spring Boot.
* docker-compose.yml: Define los servicios (tu microservicio, PostgreSQL, etc.), sus configuraciones, redes y volúmenes. Aquí es donde se establece la conexión entre tu microservicio y la base de datos a través de variables de entorno.

3. Construir el JAR de tu Microservicio (si no lo has hecho)
   Si tu Dockerfile asume que el JAR ya está construido (como en el ejemplo anterior COPY target/nombre-del-microservicio-0.0.1-SNAPSHOT.jar app.jar), necesitarás compilar tu aplicación Spring Boot localmente antes de ejecutar Docker Compose:

* mvn clean install

Esto generará el archivo JAR ejecutable en el directorio target/ de tu proyecto.

4. Levantar los Servicios con Docker Compose
   Con el docker-compose.yml y el Dockerfile en su lugar, y el JAR de tu aplicación construido, puedes levantar todos los servicios definidos en el archivo docker-compose.yml con un solo comando:

Asegúrate de estar en el directorio raíz del proyecto (donde se encuentra docker-compose.yml): 
pwd # Debería mostrar la ruta al directorio del proyecto

Ejecuta el siguiente comando:

docker-compose up -d --build

up: Inicia los servicios.

-d: Ejecuta los contenedores en modo "detached" (en segundo plano), liberando tu terminal.

--build: Fuerza la reconstrucción de las imágenes si han habido cambios en los Dockerfiles o en el código de tu aplicación (recomendado la primera vez y cuando hay cambios en el código de tu microservicio).

Docker Compose primero construirá la imagen de tu microservicio (si no la tiene o si --build es usado), y luego levantará los contenedores de PostgreSQL y tu microservicio, asegurándose de que la base de datos esté lista antes de iniciar el microservicio.

5. Verificar que los Contenedores Estén Corriendo
   Puedes verificar el estado de los contenedores ejecutando:

docker ps

Deberías ver listados los contenedores

6. Verificar el Funcionamiento del Microservicio
   Una vez que los contenedores estén levantados y el microservicio haya iniciado completamente (esto puede tomar unos segundos), podrás acceder a sus endpoints.

Tu microservicio Spring Boot debería estar accesible en el puerto 8080 de tu máquina local (o el puerto que hayas mapeado en tu docker-compose.yml).

Puedes probarlo usando curl o herramientas como Postman/Insomnia

## Detener los Servicios
Cuando hayas terminado de trabajar y quieras detener los servicios:

Abre tu terminal en el directorio raíz del proyecto.

Ejecuta:

docker-compose down

Este comando detendrá y eliminará los contenedores, redes y volúmenes definidos en tu docker-compose.yml. Si solo quieres detenerlos sin eliminar los contenedores, usa docker-compose stop.