/* Archivo rand.x */
struct dupla_int {
int a;
};

program RAND_PROG{
 version RAND_VERS{
 void INICIALIZA_RANDOM(dupla_int) = 1;
 double OBTIENE_SIGUIENTE_RANDOM() = 2;
} = 1;
} = 0x31111111;
