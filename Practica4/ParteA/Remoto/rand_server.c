/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rand.h"

void *
inicializa_random_1_svc(dupla_int *argp, struct svc_req *rqstp)
{
	static char * result;
	srandom(argp->a);
	return (void *) &result;
}

double *
obtiene_siguiente_random_1_svc(void *argp, struct svc_req *rqstp)
{
	static double  result;

	result = random();
	return &result;
}
