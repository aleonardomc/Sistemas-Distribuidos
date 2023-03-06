#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <strings.h>
#include <string.h>
#include <unistd.h>
#include "int_to_arr.h"

int main( int argc, char *argv[] )
{
    int sockfd, clisockfd, portno;
    socklen_t clilen;
    char buffer[256];
    char contentBuffer[255];
    char numstr[255];
    int aux=0;
    struct sockaddr_in serv_addr, cli_addr;
    int  n;
    int numero=1;


    /* Llamando la funcion socket */
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
    {
        perror("ERROR opening socket");
        return(1);
    }

    /* Inicializando la estructura */
    bzero((char *) &serv_addr, sizeof(serv_addr));
    portno = 4444;
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);

    /* Escuchando */
    if (bind(sockfd, (struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0)
    {
        perror("ERROR on binding");
        return(1);
    }

    listen(sockfd,5);
    clilen = (socklen_t) sizeof(cli_addr);
    /* aceptando la conexion  */
    clisockfd = accept(sockfd, (struct sockaddr *)&cli_addr, &clilen);

    if (clisockfd < 0) 
    {
        perror("ERROR on accept");
        return(1);
    }
    /* ciclo condicionado */
    while (aux == 0)
    {
        bzero(buffer,256);
        bzero(contentBuffer,255);
        /* Si la conexion fue establecida exitosamente se empieza la comunicacion */
        n = read(clisockfd,buffer,255);
        if (n < 0)
        {
            perror("ERROR reading from socket");
            return(1);
        }
        /* recuperando numero desde el buffer */
        numero = atoi(buffer);
        printf("Recibido: %d\n",numero);
        /* Condicional para terminar la comunicacion */
        if(numero == 0){
            printf("Conexion terminada\n");
            n = write(clisockfd,"Conexion terminada",19);
            if (n < 0)
            {
                perror("ERROR writing to socket");
                return(1);
            }
            aux = 1;
        }
        else{
        /* incrementando el numero en 1 y enviandolo */
            numero = numero + 1;
            itoa(numero,numstr,10);
            strcat(numstr,"\n");
            n = write(clisockfd,numstr,strlen(numstr));
            if (n < 0)
            {
                perror("ERROR writing to socket");
                return(1);
            }
        }
    }
    /* Terminando conexion */
    close(sockfd);
    return 0;
}