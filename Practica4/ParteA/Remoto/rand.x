struct dupla_int {
int a;
int b;
};

program RAND_PROG
{ version RAND_VERS
{ void INICIALIZA_RANDOM(dupla_int) =
1;
long OBTIENE_SIGUIENTE_RANDOM(void) = 2; }
= 1;
} = 0x31111111;