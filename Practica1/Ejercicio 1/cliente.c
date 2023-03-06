#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<netdb.h>

int main(int argc, char const **argv){

  if (argc < 2){
    perror("Ingrese: el host y el puerto.");
  }

  int socketfd;
  struct sockaddr_in socketCliente;
  struct hostent *socketServidor;

  socketServidor = gethostbyname(argv[1]);
  int puerto = atoi(argv[2]);
  char mensaje[100];

// CREACION DEL SOCKET Y ASIGNACION DEL SOCKET
  if ((socketfd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
    perror("Error al crear el socket. :c");
    exit(1);
  }

//VINCULACION DEL SOCKET CON LA DIRECCION Y EL NUMERO DE PUERTO
  socketCliente.sin_family = AF_INET;
  socketCliente.sin_port = htons(puerto);
  socketCliente.sin_addr.s_addr = INADDR_ANY;

  if (socketServidor == NULL){
    printf("Error con la direccion ip del servidor.");
    return 1;
  }

  //CONEXION ENTRE EL CLIENTE Y EL SERVIDOR
  if (connect(socketfd,(struct sockaddr *)& socketCliente, sizeof(socketCliente)) == -1){
      perror("Error al conectarse con el servidor. :c");
      exit(0);
  }
   
  printf("Conectado con %s:%d\n",inet_ntoa(socketCliente.sin_addr), htons(socketCliente.sin_port));    
  
  while(1){
    printf("--> Cliente: ");
    fgets(mensaje, 100, stdin);
    send(socketfd, mensaje, 100, 0); 
    bzero(mensaje, 100);
    recv(socketfd, mensaje, 100, 0); 
    fflush(stdin);
    printf("%s", mensaje);    
  }

  return 0;
}
