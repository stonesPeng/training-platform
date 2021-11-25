package me.stone.training.platform.training.java.map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author honorStone@163.com
 * @description default description
 * @date 2021/11/25 21:19
 */
public interface List2Map {

    public static void main(String[] args) {

    }

    static List<Product> getList(){
        final List<Product> productList = new ArrayList<>(100);
        for(int i =1;i<=100;i++){
            productList.add(Product.builder()
                .id((long) i)
                .name("name"+i)
                .category("category"+i%9)
                .build());
        }
        return productList;
    }

    static void toMap(List<Product> productList){
        Map<Long, String> collect = productList.stream().collect(Collectors.toMap(Product::getId, Product::getName));
    }

    static void toMap2(List<Product> productList){
        Map<Long, Product> map = productList.stream().collect(Collectors.toMap(Product::getId, data -> data));
    }


    static void toMapWithKeyConflict(List<Product> productList){
        Map<String, String> map = productList.stream().collect(Collectors.toMap(Product::getCategory, Product::getName, (existing, replacement) -> existing));
    }

    static void toConcurrentMap(List<Product> productList){
        ConcurrentHashMap<String, String> map = productList.stream().collect(Collectors.toMap(Product::getCategory, Product::getName, (existing, replacement) -> existing, ConcurrentHashMap::new));

    }




    @Getter
    @Setter
    @ToString
    @Builder
    class Product{
        private Long id;
        private String category;
        private String name;
    }
}
