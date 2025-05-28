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

git clone https://github.com/tu-usuario/nombre-del-repositorio.git
cd nombre-del-repositorio