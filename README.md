# ID3

## Introduction

El algoritmo ID3 comienza con el conjunto original S como nodo raíz. En cada iteración del algoritmo, itera a través de cada atributo no utilizado del conjunto S y calcula la entropía H(S) (o ganancia de información IG(A)) de ese atributo. Luego selecciona el atributo que tiene el valor de entropía más pequeño (o la ganancia de información más grande). Luego, el conjunto S se divide por el atributo seleccionado (por ejemplo, la edad es menor de 50 años, la edad está entre 50 y 100, la edad es mayor de 100) para producir subconjuntos de datos. El algoritmo continúa repitiéndose en cada subconjunto, considerando solo los atributos nunca antes seleccionados.

## Data Set

El problema del clima es un conjunto de datos de juguete que usaremos para comprender cómo se construye un árbol de decisión. Proviene de Quinlan (1986), un artículo que analiza el algoritmo ID3 introducido en Quinlan (1979). Eso
se reproduce con ligeras modificaciones en Witten y Frank (1999), y se refiere a las condiciones bajo las cuales se puede jugar algún juego hipotético al aire libre. Los datos se muestran a continuación:

| Outlook | Temperature | Humidity |  Wind  | Play Ball? |
|:-------:|:-----------:|:--------:|:------:|:----------:|
|Sunny    | Hot         | High     | Weak   | No         |
|Sunny    | Hot         | High     | Strong | No         |
|Overcast | Hot         | High     | Weak   | Yes        |
|Rain     | Mild        | High     | Weak   | Yes        |
|Rain     | Cool        | Normal   | Weak   | Yes        |
|Rain     | Cool        | Normal   | Strong | No         |
|Overcast | Cool        | Normal   | Strong | Yes        |
|Sunny    | Mild        | High     | Weak   | No         |
|Sunny    | Cool        | Normal   | Weak   | Yes        |
|Rain     | Mild        | Normal   | Weak   | Yes        |
|Sunny    | Mild        | Normal   | Strong | Yes        |
|Overcast | Mild        | High     | Strong | Yes        |
|Overcast | Hot         | Normal   | Weak   | Yes        |
|Rain     | Mild        | High     | Strong | No         |

