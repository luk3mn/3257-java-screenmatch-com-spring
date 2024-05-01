package br.com.alura.screenmatch.annotations;

import java.util.Arrays;
import java.util.List;

public class LambdaFunction {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

//        for(Integer i: lista) {
//            if(i % 2 == 0) {
//                System.out.println(i);
//            }
//        }

        // mesmo resultado
        lista.stream().filter(i -> i % 2 == 0).forEach(System.out::println);
        /*
        * NOTE:
        * No código acima, criamos um stream da nossa lista, filtramos esse
        * stream para incluir apenas os números pares (isso é feito pela função
        * lambda i -> i % 2 == 0), e finalmente usamos o método forEach para printar
        * cada elemento do stream filtrado.
        * */

        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
        // operações encadeadas
        System.out.println();
        nomes.stream()
                .sorted() // 1. ordena a lista
                .limit(3) // 2. limita a "3" primeiros
                .forEach(System.out::println); // 3. printa a lista final

        /*
        * NOTE:
        * - Tudo que gera um novo fluxo é uma operação intermediária, como
        *   o "sort()" e o "limit()".
        * - Tudo que faz algo com esse fluxo é uma operação final, como
        *   o "forEach()" que está sendo usado
        * */

        // operações encadeadas
        System.out.println();
        nomes.stream()
                .sorted() // 1. ordena a lista
                .limit(3) // 2. limita a "3" primeiros
                .filter(n -> n.startsWith("N")) // 3. filta apenas aqueles que começem com "N"
                .map(String::toUpperCase) // 4. converte os valoes para MAIÚSCULO => map(n -> n.toUpperCase())
                .forEach(System.out::println); // 5. printa a lista final

        /*
        * NOTE:
        * As operações intermediárias são aquelas que podem ser aplicadas em
        * uma stream e retornam uma nova stream como resultado. Essas operações
        * não são executadas imediatamente, mas apenas quando uma operação final
        * é chamada.
        *
        *  *******************************************
        *  ***** OPERAÇÕES INTERMEDIÁRIAS ************
        *  *******************************************
        * - Filter: permite filtrar os elementos da stream com base em uma condição.
        *   Por exemplo, podemos filtrar uma lista de números para retornar apenas
        *   os números pares.
        *
        * - Map: permite transformar cada elemento da stream em outro tipo de dado.
        *   Por exemplo, podemos transformar uma lista de strings em uma lista de
        *   seus respectivos tamanhos.
        *
        *  *********************************
        *  ******* OPERAÇÕES FINAIS ********
        *  *********************************
        *  - ForEach: permite executar uma ação em cada elemento da stream. Por exemplo,
        *    podemos imprimir cada elemento da lista.
        *
        *  - Collect (Lista Mutável): permite coletar os elementos da stream em uma coleção ou em outro
        *    tipo de dado. Por exemplo, podemos coletar os números pares em um conjunto.
        *
        *  - toList (Lista Imutável)
        *
         * */
    }
}
