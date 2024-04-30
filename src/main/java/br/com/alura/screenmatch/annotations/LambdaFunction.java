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
    }
}
