Desarrollo Laboratorio 3.2

    1. Download the project PrimeFinder. this is a program that calculates 
        prime numbers beetween 0 and M (Control.MAXVALUE),concurrently, 
        distributing the searching of them between n (Control.NTHREADS) 
        independent threads.

![](img/1Descarga.png)


    2.Complete el metodo main de CountMainThread de la clase:

        1 .Cree 3 hilos ed tipo CountThread y asigneles intervalos de [0,99], [99,199] ,[199,299]

![](img/2.13hilos.png)

        2.Empiece cada hilo con Start.

![](img/2.2InicioStart.png)

        3.Ejecute y revise la salida por pantalla.

![](img/3InicioEvidencia.png)

        Se evidencia en la salida que los números no son constantes

        4.Cambie start por run y mire el resultado.

![](img/InicioRun.png)

        Con el uso de run podemos observar como la salida de datos es organizada.
    


Parte2 -- Blac List Search Exercise

    1. Cree una clase Thread que represente el ciclo de vida de un hilo que 
        busca un segmento del grupo de servidores disponibles. Agregue a esa 
        clase un método que le permita preguntar a las instancias (los hilos) 
        cuántas ocurrencias de servidores maliciosos ha encontrado o encontrado.

        R: Se creo una clase llamada Bllock, la cual recibe un segmento de la 
            lista de servidores y analiza en busqueda del servidor malicioso.
            Antes en la clase HostBlackListValidator se crean los hilos, 
            los cuales se le asigna un segmento a revisar y asi reducir 
            el tiempo de busqueda.

![](img/revisandoIpMala.png)


    2. Agregue al método checkHost un parámetro entero N, correspondiente al 
        número de subprocesos entre los que se realizará la búsqueda (recuerde 
        tener en cuenta si N es par o impar). Modifique el código de este método 
        para que divida el espacio de búsqueda entre las N partes indicadas y se 
        pare la búsqueda en N hilos. Haga que la función espere hasta que los 
        subprocesos N terminen de resolver sus respectivos subproblemas, 
        agregue las apariciones encontradas para cada subproceso a la lista que 
        devuelve el método y luego calcule (agregando el número total de 
        incidencias encontradas para cada subproceso) si el Número de las 
        ocurrencias son mayores o iguales a BLACK_LIST_ALARM_COUNT. 
        Si este es el caso, al final el host DEBE ser reportado como confiable 
        o no confiable, y la lista debe mostrarse con los números de las listas 
        negras respectivas. Para lograr este comportamiento de espera, revise el 
        método de unión de la API de concurrencia de Java. 

        R: Se agrego un N, de tal manera que en este entero sera dividida 
        la lista y sera creada un numero igual de hilos para procesesar las IPs.
    

![](img/RevisandoIpSana.png)

        2.1.Para reducir el tiempo de búsqueda y no se siga buscando un servidor 
            cuando ya cumplio para que el servidor sea reportado, tendremos que 
            tener un get que traiga desde la clase block la lista de ocurrencias 
            que vaya registrando y sumarla a la que tenga actualmente, y cuando 
            este llegue por lo menos a 5, detenga el resto de hilos 
            y no procese más.
    
    3. Evaluacion de Desempeño

        1. Un Solo hilo
![](img/Solo1Hilo.png)
        

        2. Tantos Hilos Como nucleos de Procesamiento.

![](img/TantosHiloComoNucleos.png)

        3. Tantos hios como el doble de nucleos de procesamiento.

![](img/TantosHilosDobleProce.png)

        4. 50 Hilos

![](img/50Hilos.png)

        5. 100 Hilos 

![](img/100hilos.png)

        Grafica

![](img/Grafica.png)


        1.El mejor desempeño no se logra con 500 hilos porque al final son los núcleos 
            del procesador que nos indicarán la rapidez del software, con esto quiero 
            decir que entre más hilos podemos volver más lento el software, en este caso 
            sería tener un procesador con una frecuencia más alta y menos hilos y se obtiene 
            un mejor resultado en rendimiento.


        2. EL tiempo de que dura en terminar el problema se reduce aproximadamente 10 segundos.

        3. No del todo, porque la capacidad de los núcleos de las 100 máquinas se desperdiciaria, 
            aunque sí mejoraría al usar en una máquina esos 100 hilos. Si c no es por lo menos mayor 
            o igual a 50 podría ver una mejoría en la  la ley de Amdahls.


