package com.example.redisdemo.Controller;

import com.example.redisdemo.Entity.Product;
import com.example.redisdemo.Entity.User;
import com.example.redisdemo.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedisController {

    private final RedisService myRedisService;

    @Autowired
    public RedisController(RedisService myRedisService) {
        this.myRedisService = myRedisService;
    }

    /**
     * Key value
     *
     * @param value Key
     */
    @GetMapping("/set")
    public void setValue(String key, String value) {
        System.out.println("有打到set: " + key);
        myRedisService.setData(key, value);
    }

    @GetMapping("/get")
    public String getValue(String key) {
        System.out.println("有打到Get: " + key);
        return myRedisService.getData(key);
    }

    @DeleteMapping("/delete")
    public Boolean deleteValue(String key) {
        System.out.println("有打到delete: " + key);
        return myRedisService.deleteData(key);
    }

    /**
     * User
     *
     * @param user
     */
    @PostMapping("/setUser")
    public void setValue(@RequestBody User user) {
        System.out.println("有打到setUser: " + user);
        myRedisService.saveUser(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id) {
        System.out.println("有打到getUser: " + id);
        System.out.println("有打到getUser: " + myRedisService.getUserById(id));
        return myRedisService.getUserById(id);
    }

    @DeleteMapping("/delUser/{id}")
    public void delUser(@PathVariable int id) {
        System.out.println("有打到delUser: " + id);
        myRedisService.deleteUser(id);
    }

    /**
     * UserList
     *
     * @param userList
     */
    @PostMapping("/setUserList")
    public void setUserList(@RequestBody List<User> userList) {
        System.out.println("有打到setUser: " + userList);
        myRedisService.saveUserList(userList);
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        System.out.println("有打到getUser: ");
        System.out.println("有打到getUser: " + myRedisService.getUserList());
        return myRedisService.getUserList();
    }

    @DeleteMapping("/delUserList")
    public void delUserList() {
        System.out.println("有打到delUser: ");
        myRedisService.deleteUserList();
        ;
    }

    /**
     * Product {
     * User
     * }
     */
    @PostMapping("/setProduct")
    public void setProduct(@RequestBody Product product, @RequestParam int userId) {
        System.out.println("有打到setProduct: " + product);
        myRedisService.saveProduct(product, userId);
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable int id) {
        System.out.println("有打到getProduct: " + id);
        System.out.println("有打到getProduct: " + myRedisService.getUserById(id));
        return myRedisService.getProductById(id);
    }

    @DeleteMapping("/delProduct/{id}")
    public void delProduct(@PathVariable int id) {
        System.out.println("有打到delUser: " + id);
        myRedisService.deleteProduct(id);
    }

    /**
     * ProductList
     *
     * @param
     */
    @PostMapping("/setProductList")
    public void setProductList(@RequestBody List<Product> productList) {
        System.out.println("有打到productList: " + productList);
        myRedisService.saveProductList(productList);
    }

    @GetMapping("/getProductList")
    public List<Product> getProductList() {
        System.out.println("有打到getProductList: ");
        System.out.println("有打到getUser: " + myRedisService.getProductList());
        return myRedisService.getProductList();
    }

    // @DeleteMapping("/delUserList")
    //    public void delUserList() {
    //        System.out.println("有打到delUser: ");
    //        myRedisService.deleteUserList();;
    //    }
}


