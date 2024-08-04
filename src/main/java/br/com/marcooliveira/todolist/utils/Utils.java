package br.com.marcooliveira.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    //IRÁ COPIAR TUDO QUE NÃO FOR NULO.
    public static void copyNonNullProperties(Object source, Object target) {

        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    //CRIAÇÃO DO MÉTODO QUE RETORNARÁ UM ARRAY QUE PEGARÁ TODOS OS NOMES DA PROPRIEDADES NULAS.
    public static String[] getNullPropertyNames(Object source) {

        /*BEANWRAPPER é uma interface que fornece uma forma para acessar as propriedades de um obj
         * BEANWRAPPER é a implementação desta interface.*/
        final BeanWrapper src = new BeanWrapperImpl(source);

        //OBTENDO AS PROPRIEDADES DO OBJETO.
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        //CRIAÇÃO DO CONJUNTO DE PROPRIEDADES DE VALORES NULOS.
        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds) {

            Object srcValue = src.getPropertyValue(pd.getName());

            //VERIFICANDO SE A PROPRIEDADE srcValue É NULA.
            if(srcValue == null) {

                emptyNames.add(pd.getName()); /*TODAS AS PROPRIEDADES QUE FOREM NULAS
                SERÃO GUARDADAS DENTRO DA VARIÁVEL emptyNames.*/
            }
        }

        //CRIAÇÃO DO ARRAY DE STRING PARA ARMAZENAR TODOS OS NOMES DAS PROPRIEDADES.
        String[] result = new String[emptyNames.size()]; /*emptyNames.size para especificar
        o tamanho do array.*/
        return emptyNames.toArray(result); //CONVERTENDO O emptyNames para um array.
    }
    
}
