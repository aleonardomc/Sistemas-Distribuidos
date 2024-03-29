/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rand.h"


void
rand_prog_1(char *host, int a, int b)
{
	CLIENT *clnt;
	void  *result_1;
	dupla_int  inicializa_random_1_arg;
	long  *result_2;
	char *obtiene_siguiente_random_1_arg;

#ifndef	DEBUG
	clnt = clnt_create (host, RAND_PROG, RAND_VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	inicializa_random_1_arg.a = a;
	inicializa_random_1_arg.b = b;

	result_1 = inicializa_random_1(&inicializa_random_1_arg, clnt);
	if (result_1 == (void *) NULL) {
		clnt_perror (clnt, "call failed");
	}


	result_2 = obtiene_siguiente_random_1((void*)&obtiene_siguiente_random_1_arg, clnt);
	if (result_2 == (long *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	else
		for (int i = 0; i < b; i +=1){
			printf("%d: %d\n", i+1,  *result_2);
			result_2 = obtiene_siguiente_random_1((void*)&obtiene_siguiente_random_1_arg, clnt);
		}
		
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}


int
main (int argc, char *argv[])
{
	char *host;
	int a, b, misemilla, itera;
	if (argc < 4) {
		printf ("usage: %s server_host int_a int_b\n", argv[0]);
		exit (1);
	}
	host = argv[1];

	if ((a = atoi(argv[2])) == 0 && *argv[2] != '0') {
		fprintf(stderr, "invalid value: %s\n", argv[2]);
		exit(1);
	}
	if ((b = atoi(argv[3])) == 0 && *argv[3] != '0') {
		fprintf(stderr, "invalid value: %s\n", argv[3]);
		exit(1);
	}

	misemilla = a;
	itera = b;
	/*inicializa_random_1 (misemilla);
	for (int i = 0; i < itera; i++)
		printf("%d : %d\n", i, obtiene_siguiente_random());
	exit(0);
	*/
	rand_prog_1 (host, a, b);

exit (0);
}
