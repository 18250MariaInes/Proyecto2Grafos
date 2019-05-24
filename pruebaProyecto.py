# Ma.Ines - Abril - Camila
# 17/05/2019
# Se trabajo en python el proyecto ya que teniamos experiencia en este lenguaje gracias la HDT10
# Esto sirvio para tomar idea de como tendria que ser en JAVA y asi fuera mas facil trabajar en dicho lenguaje

from neo4j import GraphDatabase

driver = GraphDatabase.driver("bolt://localhost:7687", auth=("neo4j", "netsa"))

    
    
#Muestra todos las peliculas segun el genero ingresado 
def return_docs (tx, name):
    print("Peliculas de " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (n:Movie) WHERE n.genre = $name RETURN n.title, id(n), n.released, n.genre ORDER BY n.title",
            name=name):
        cont = cont + 1
        print (str(cont) + ". " + record["n.title"])
        
#Muestra todos las peliculas en las que actuo el actor ingresado
def return_actorsmovies(tx, name):
    print("Peliculas del Actor " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (d:Person {name:$name})-[:ACTED_IN]->(n:Movie) RETURN n.title, id(n), n.released, n.genre ORDER BY n.title",
            name=name):
        cont = cont + 1
        print (str(cont) + ". " + record["n.title"]+ " "+record["n.genre"])

#Muestra todos las peliculas de determinado genero en las que actuo el actor detrminado
def return_actorsmoviesofgenre(tx, name, genero):
    print("Peliculas del Actor " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (d:Person {name:$name})-[:ACTED_IN]->(x:Movie {genre:$genero}) RETURN x.title, x.genre, x.released ORDER BY x.title",
            name=name, genero=genero):
        cont = cont + 1
        print (str(cont) + ". " + record["x.title"])

def return_moviefromanothergenreandactors(tx, title, genero):
    print("Peliculas del Actor " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (n:Movie {title:$title})<-[:ACTED_IN]-(d:Person)-[:ACTED_IN]->(x:Movie {genre:$genero}) RETURN x.title, x.genre, x.released ORDER BY x.title",
            title=title, genero=genero):
        cont = cont + 1
        print (str(cont) + ". " + record["x.title"])

#Muestra todos los actores de una pelicula especifica
def return_actorsofamovie(tx, name):
    print("Actores de la pelicula " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (d:Movie {title:$name})<-[:ACTED_IN]-(x:Person) RETURN x.name ORDER BY x.name",
            name=name):
        cont = cont + 1
        print (str(cont) + ". " + record["x.name"])

#Muestra el genero de una pelicula seleccionada
def return_genreofmovie(tx, name):
    print("Genero de la pelicula " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (d:Movie {title:$name}) RETURN d.genre",
            name=name):
        cont = cont + 1
        print (str(cont) + ". " + record["d.genre"])
        
#Muestra los actores de peliculas de un genero ingresado
def return_actorsofgenre(tx, name):
    print("Genero de la pelicula " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (d:Movie {genre:$name})<-[:ACTED_IN]-(x:Person) RETURN x.name ORDER BY x.name",
            name=name):
        cont = cont + 1
        print (str(cont) + ". " + record["x.name"])

def return_genreofanactor(tx, name):
    print("Genero de la pelicula " + name + "\n")
    cont = 0
    for record in tx.run ("MATCH (d:Person {name:$name})-[:ACTED_IN]->(x:Movie) RETURN x.genre ORDER BY x.genre",
            name=name):
        cont = cont + 1
        print (str(cont) + ". " + record["x.genre"])
#Verifica que el valor de una variable sea un numero converitble a entero
def validarNumero(variable): 
    try:
        variable = int(variable)
        return True
    except ValueError:
        return False

def allgenres(tx):
    print("Generos\n")
    cont = 0
    for record in tx.run ("MATCH (d:Movie) RETURN d.genre ORDER BY d.genre"):
        cont = cont + 1
        print (str(cont) + ". " + record["d.genre"])
def allmovies(tx):
    print("Peliculas\n")
    cont = 0
    for record in tx.run ("MATCH (n:Movie) RETURN id(n), n.title, n.genre, n.tagline, n.released  ORDER BY n.title"):
        cont = cont + 1
        print (str(cont) + ". " +record["n.title"]+ record["n.genre"]+record["n.tagline"])

def deleteactor(tx, nombre, namemovie):
    print("Borrar\n")
    tx.run ("MATCH (d:Person {name:$name})-[r:ACTED_IN]->(x:Movie {title:$namemovie}) DELETE r",
            name=name, namemovie=namemovie)

def createactor(tx, nombre, born):
    print("Crear\n")
    tx.run ("CREATE (d:Person {name:$name, born:$born})",
            name=name, born=born)
def allactors(tx):
    print("Actores\n")
    cont = 0
    for record in tx.run ("MATCH (d:Person) RETURN d.name ORDER BY d.name"):
        cont = cont + 1
        print (str(cont) + ". " + record["d.name"])

def linkactor(tx, nameactor, namemovie):
    tx.run("MERGE (d:Person {name:$nameactor})-[:ACTED_IN]->(x:Movie {title:$namemovie})",
           nameactor=nameactor, namemovie=namemovie)

def personExist(tx, name):
    print("Actores Existentes\n")
    cont = 0
    for record in tx.run ("MATCH (d:Person {name:$name}) RETURN d.name ORDER BY d.name",
                          name = name):
        cont = cont + 1
        print (str(cont) + ". " + record["d.name"])

def personExistinMovie(tx, name, namemovie):
    print("Actor en pelicula\n")
    cont = 0
    for record in tx.run ("MATCH (d:Person {name:$name})-[:ACTED_IN]->(x:Movie {title:$namemovie}) RETURN d.name ORDER BY d.name",
                          name = name, namemovie=namemovie):
        cont = cont + 1
        print (str(cont) + ". " + record["d.name"])
    

# Programa
with driver.session() as session:
    elec=0;
    #muestra mensaje de bienvenida.
    print("------BIENVENIDO AL RECOMENDADOR de PELIS------")
    while (elec!=10):
        #menu
        print("\n\nIngrese opcion que desea realizar\n")
        print("1. Buscar peliculas segun su genero específico\n")
        print("2. Buscar todas las peliculas de un actor específico\n")
        print("3. Buscar peliculas de un actor especifico de un genero especifico\n")
        print("4. Buscar actores de una pelicula especifica\n")
        print("5. Buscar genero de una pelicula especifica\n")
        print("6. Buscar actores de un genero especifico\n")
        print("7. Buscar los generos de un actor especifico\n")
        print("8. Salir\n")
        elec=input("")
        # si ingresa una opcion incorrecta.
        if ((validarNumero(elec)==False)or (int(elec)==0)or (int(elec)>20)):
            print("¡¡¡¡¡Ingresaste una opcion incorrecta!!!!\n")
        else:
            elec = int(elec)
            # busca peliculas segun su género.
            if (elec==1):
                print("\n-_-_-_-_-_-_Buscador de peliculas segun genero-_-_-_-_-_-_\n")
                espec=input ("Genero: ")
                session.read_transaction(return_docs, espec)
            # Busca peliculas de un actor.
            elif (elec==2):
                print("\n-_-_-_-_-_-_Buscador de peliculas de actor-_-_-_-_-_-_\n")
                espec=input ("Nombre: ")
                session.read_transaction(return_actorsmovies, espec)
            # Amistad doctor con doctor.
            elif (elec==3):
                print("\n-_-_-_-_-_-_Buscador de peliculas de actor segun su genero-_-_-_-_-_-_\n")
                espec=input ("Nombre: ")
                genre=input ("Genero: ")
                session.read_transaction(return_actorsmoviesofgenre, espec, genre)
            # Buscar actores de una pelicula
            elif (elec==4):
                print("\n-_-_-_-_-_-_Buscador de actores de una pelicula-_-_-_-_-_-_\n")
                espec=input ("Nombre de pelicula: ")
                session.read_transaction(return_actorsofamovie, espec)
            # buscar genero de una pelicula
            elif (elec==5):
                print("\n-_-_-_-_-_-_Buscador de actores de una pelicula-_-_-_-_-_-_\n")
                espec=input ("Nombre de pelicula: ")
                session.read_transaction(return_genreofmovie, espec)
            #buscar actores de un genero
            elif (elec==6):
                print("\n-_-_-_-_-_-_Buscador de actores de un genero-_-_-_-_-_-_\n")
                espec=input ("Genero: ")
                session.read_transaction(return_actorsofgenre, espec)
            elif (elec==7):
                print("\n-_-_-_-_-_-_Buscador de generos de un actor-_-_-_-_-_-_\n")
                espec=input ("Actor: ")
                session.read_transaction(return_genreofanactor, espec)
            elif (elec==8):
                 session.read_transaction(allactors)
            elif (elec==9):
                print("\n-_-_-_-_-_-_Buscador de generos de un actor-_-_-_-_-_-_\n")
                name=input ("Titulo: ")
                genero=input("Genero: ")
                session.read_transaction(return_moviefromanothergenreandactors, name, genero)
            elif (elec==10):
                print("\n-_-_-_-_-_-_eliminar actor-_-_-_-_-_-_\n")
                name=input ("Nombre: ")
                movie=input ("Peli: ")
                session.write_transaction(deleteactor, name, movie)
            elif (elec==11):
                print("\n-_-_-_-_-_-_crear actor-_-_-_-_-_-_\n")
                name=input ("Nombre: ")
                born=input ("Fecha: ")
                session.write_transaction(createactor, name, born)
            elif (elec==12):
                print("\n-_-_-_-_-_-_link actor with movie-_-_-_-_-_-_\n")
                name=input ("Nombre actor: ")
                born=input ("titulo: ")
                session.write_transaction(linkactor, name, born)
            elif (elec==13):
                print("\n-_-_-_-_-_-_verificar existe actor-_-_-_-_-_-_\n")
                name=input ("Nombre actor: ")
                session.read_transaction(personExist, name)
            elif (elec==14):
                print("\n-_-_-_-_-_-_verificar existe actor en pelicula-_-_-_-_-_-_\n")
                name=input ("Nombre actor: ")
                movie=input ("Pelicula: ")
                session.read_transaction(personExistinMovie, name, movie)
                
            
               
    #Termina el while        
    if(elec==13):
        print("Hasta luego!")
