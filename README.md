# alkemy_disney_app
### Para correr la aplicacion es necesario primero crear la base de datos con nombre: "disneydb"
### Para cambiar los datos de sesion para la base de datos en caso de ser necesario estan en "aplication properties"
### El usuario actual para la base de datos es "user: root" "password: root"
### Tiene la configuracion para swagger.

#####Algunos links para probar en postman junto con el formato de texto (json) que se deben enviar en caso de ser metodo POST: 

url --> http://localhost:8080/auth/login
login :
 {
    "username": "user2",
    "password": "1234"
}
charater:registro: 
url --> http://localhost:8080/characters/register
{
    "name": "Superman3",
    "age": 30,
    "weight": 90,
    "history": "Historia de superman...",
    "image": "url:image:superman"
}
character:update:
url --> http://localhost:8080/characters/update
{
    "id": 2,
    "name": "ironmanUpdated2",
    "age": 35,
    "weight": 80,
    "history": "Historia de ironman updated2...",
    "image": "url:image:ironmanUpdate2"
}

movies: create:
url --> http://localhost:8080/movie
{
    "image": "url:image:",
    "title": "Ironman",
    "score": 10,
    "genres": [
        {  
            "name": "Drama"},
        {
            "name":"Accion"}],
    "characters": [{ "name": "vinculado2",
    "age": 35,
    "weight": 100,
    "history": "Historia de superman2...",
    "image": "url:image:superman2"
},
{ "name": "vinculado1",
    "age": 30,
    "weight": 90,
    "history": "Historia de superman...",
    "image": "url:image:superman"
}]
}

movie:update
url --> http://localhost:8080/movie/2
{
    "image": "url:image:",
    "title": "superman0",
    "enable": false,
    "score": 10,
    "genres": [
        {  
            "name": "Drama"},
        {
            "name":"Accion"}],
    "characters": [{ "name": "vinculado2",
    "age": 35,
    "weight": 100,
    "history": "Historia de superman2...",
    "image": "url:image:superman2"
},
{ "name": "vinculado1",
    "age": 30,
    "weight": 90,
    "history": "Historia de superman...",
    "image": "url:image:superman"
}]
}

busqueda con filtros: 
name:
http://localhost:8080/characters/name/{name}
age:
http://localhost:8080/characters/age/{age}
id movie:
http://localhost:8080/characters/movies/{idMovie}
