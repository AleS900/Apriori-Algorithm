# Apriori

## Introducción

El algoritmo Apriori fue el primer algoritmo que se propuso
para la minería frecuente de conjuntos de elementos.
Posteriormente fue mejorado por R Agarwal y R Srikant y llegó
a ser conocido como Apriori. Este algoritmo utiliza dos pasos
'unir' y 'podar' para reducir el espacio de búsqueda. Es un
enfoque iterativo para descubrir los conjuntos de elementos
más frecuentes. Los pasos explicados son:
- Unir: Este paso genera (K + 1) conjunto de elementos a partir de K-conjuntos de elementos uniendo cada elemento consigo mismo.
- Podar: Este paso analiza el recuento de cada elemento de la base de datos. Si el artículo candidato no cumple con el soporte mínimo, entonces se considera poco frecuente y, por lo tanto, se elimina. Este paso se realiza para reducir el tamaño de los conjuntos de elementos candidatos.

Con el algoritmo Apriori, los conjuntos de elementos
candidatos se generan utilizando solo los conjuntos de
elementos grandes del paso anterior. El conjunto de elementos
grande del paso anterior se une consigo mismo para generar
todos los conjuntos de elementos con un tamaño que es mayor
en uno; a continuación, se elimina cada conjunto de elementos
generado con un subconjunto que no sea grande. Los conjuntos
de elementos restantes son los candidatos.


