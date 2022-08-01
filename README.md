# Proyecto Mutant

Este proyecto está diseñado para ejecutar un servicio que permite determinar si
una cadena de ADN perteneciente a un humano indica si este es o no, un mutante. Además,
permite llevar un historial de ADNs verificados, dando el ratio entre ADN Humano y ADN Mutante.

## Tecnologías usadas

- Java 8
- Spring Boot
- JPA, Hibernate
- PostgreSQL v14
- Maven

## Instalación del proyecto

Para instalar localmente este proyecto se necesitan los siguientes requisitos:

### Requisitos para instalación

1. Contar con una versión de java 8 instalada.
2. Contar con una versión de maven instalada.
3. Contar con postgreSQL v14 instalado (solo para ambiente de desarrollo).

## Ejecución rápida del proyecto localmente

1. Clonar proyecto en la máquina local: *git clone https://github.com/JuliansM/mutant.git*
2. Navegar dentro de la carpeta del proyecto desde la terminal
3. Ejecutar el siguiente comando para compilar el proyecto:   *mvn clean install*
4. Ejecutar el siguiente comando para correr el proyecto localmente:  *java -jar target/mutant-0.0.1-SNAPSHOT.war*

### Servicios habilitados para probar

1. Servicio que permite verificar ADN Humano y determinar si es mutante o no.

- Url: http://localhost:8081/mutant
- Method: POST
- Body (ejemplo): {
  "dnas": ["CATAAA","CAGAGG","TTTTGT","TGAGTG","CCCCGA","TCTCTG"]
  }
- Respuesta: devuelve *true* en caso que se verifique MUTANTE el ADN, en caso contrario devuelve *false*

2. Servicio que permite obtener la relación de ADNs verificados y ADNs mutantes, con su respectivo ratio.

- Url: http://localhost:8081/mutant/stats
- Method: GET
- Respuesta: devuelve un JSON con la siguiente forma: { "countMutantDna": 3, "countHumanDna": 4, "ratio": 0.75 }

--------------------------------------------------------------------------------------------

# Preparación para ambiente de desarrollo

### Clonando repositorio

- Ejecutar el comando de git para clonar este repositorio: git clone https://github.com/JuliansM/mutant.git

### Preparando base de datos postgreSQL

Una vez clonado el repositorio e instalado postgreSQL localmente, se debe abrir el archivo
*application.properties*, dentro se encuentran los datos correspondientes para la base
de datos en postgreSQL. A continuación guíese de dicho archivo y crear la DB en postgreSQL.

### Compilando, construyendo y levantando aplicación en ambiente local

Para realizar el proceso de Build del proyecto de manera local, a continuación siga las
indicaciones:

1. Abrir proyecto en el IDE de su preferencia (se recomienda Intellij IDEA)
2. Abrir terminal y ejecutar el siguiente comando: mvn clean install
3. Una vez compilado con maven, corra la aplicación.


### Servicios habilitados para su uso

1. Servicio que permite verificar ADN Humano y determinar si es mutante o no.

- Url: http://localhost:8081/mutant
- Method: POST
- Body (ejemplo): {
  "dnas": ["CATAAA","CAGAGG","TTTTGT","TGAGTG","CCCCGA","TCTCTG"]
  }
- Respuesta: devuelve *true* en caso que se verifique MUTANTE el ADN, en caso contrario devuelve *false*

2. Servicio que permite obtener la relación de ADNs verificados y ADNs mutantes, con su respectivo ratio.

- Url: http://localhost:8081/mutant/stats
- Method: GET
- Respuesta: devuelve un JSON con la siguiente forma: { "countMutantDna": 3, "countHumanDna": 4, "ratio": 0.75 }
