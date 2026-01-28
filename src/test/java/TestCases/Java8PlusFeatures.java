package TestCases;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8PlusFeatures {

    @Test
    public void testLambdaStreamFilter(){
        List<String> list = Arrays.asList("sachin","dhoni","saurav","da","e","yuvraj singh");
        // Using lambda with forEach
        list.forEach(System.out::println);
        System.out.println(" Lambda with multiple statements ");
        list.forEach(name -> {
            System.out.print(name+"    ");
            System.out.println(name.length());
        });

        System.out.println("find Even Numbers from list");
        List<Integer> number = Arrays.asList(1,2,3,4,5,6,7,2,6,8);
        List<Integer> res=number.stream().filter(num -> num%2==0).toList();
        res.forEach(System.out::println);

        System.out.println("Double Even number using Lambda ");
        List<Integer> doubleNum= number.stream().filter(num -> num%2==0)
                .map(n -> n*2).toList();
        doubleNum.forEach(i -> System.out.print(i+"    "));

        System.out.println("Double Even Number and remove duplicates ");
        Set<Integer> set=number.stream().filter(num -> num%2==0)
                .map(n -> n*2).collect(Collectors.toSet());
        set.forEach(i -> System.out.print(i+"    "));

    }

}