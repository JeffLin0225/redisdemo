package com.example.redisdemo.Service;

import com.example.redisdemo.Entity.Product;
import com.example.redisdemo.Entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> template;

    public RedisService(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    /**
     * Key value
     *
     * @param value Key
     */
    public void setData(String key, String value) {
        template.opsForValue().set(key, value);
    }

    public String getData(String key) {
        return (String) template.opsForValue().get(key);
    }

    public Boolean deleteData(String key) {
        return template.delete(key);
    }

    /**
     * User
     *
     * @param user
     */
    public void saveUser(User user) {
        template.opsForValue().set("user:" + user.getId(), user);
    }

    public User getUserById(int userId) {
        User user = (User) template.opsForValue().get("user:" + userId);
        System.out.println(user);
        return user;
    }

    public void deleteUser(int userId) {
        template.delete("user:" + userId);
    }

    /**
     * UserList
     *
     * @param userList
     */
    public void saveUserList(List<User> userList) {
        template.opsForList().rightPushAll("userList", userList.toArray());
    }

    public List<User> getUserList() {
        List<Object> userList = template.opsForList().range("userList", 0, -1);
        List<User> theUserList = userList.stream().map(user -> (User) user).collect(Collectors.toList());
        System.out.println(userList);
        System.out.println(theUserList);
        return theUserList;
    }

    public void deleteUserList() {
        template.delete("userList");
    }

    /**
     * Product {
     * User
     * }
     */
    public void saveProduct(Product product, int userId) {
        User userCache = getUserById(userId);
        product.setUser(userCache);
        template.opsForValue().set("product:" + product.getId(), product);
    }

    public Product getProductById(int productId) {
        Product product = (Product) template.opsForValue().get("product:" + productId);
        System.out.println(productId);
        return product;
    }

    public void deleteProduct(int productId) {
        template.delete("product:" + productId);
    }

    /**
     * ProductList
     *
     * @return
     */
    public void saveProductList(List<Product> productList) {
        List<Product> theProductList = new ArrayList<>();
        for (Product product : productList) {
            User user = getUserById(product.getUser().getId());
            product.setUser(user);
            theProductList.add(product);
        }
        template.opsForList().rightPushAll("productList", theProductList.toArray());

    }

    public List<Product> getProductList() {
        List<Object> productList = template.opsForList().range("productList", 0, -1);
        List<Product> theProductList = productList.stream().map(product -> (Product) product).collect(Collectors.toList());
        System.out.println(productList);
        System.out.println(theProductList);
        return theProductList;
    }
}


