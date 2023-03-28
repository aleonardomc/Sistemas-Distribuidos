/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _RAND_H_RPCGEN
#define _RAND_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct dupla_int {
	int a;
	int b;
};
typedef struct dupla_int dupla_int;

#define RAND_PROG 0x31111111
#define RAND_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define INICIALIZA_RANDOM 1
extern  void * inicializa_random_1(dupla_int *, CLIENT *);
extern  void * inicializa_random_1_svc(dupla_int *, struct svc_req *);
#define OBTIENE_SIGUIENTE_RANDOM 2
extern  long * obtiene_siguiente_random_1(void *, CLIENT *);
extern  long * obtiene_siguiente_random_1_svc(void *, struct svc_req *);
extern int rand_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define INICIALIZA_RANDOM 1
extern  void * inicializa_random_1();
extern  void * inicializa_random_1_svc();
#define OBTIENE_SIGUIENTE_RANDOM 2
extern  long * obtiene_siguiente_random_1();
extern  long * obtiene_siguiente_random_1_svc();
extern int rand_prog_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_dupla_int (XDR *, dupla_int*);

#else /* K&R C */
extern bool_t xdr_dupla_int ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_RAND_H_RPCGEN */