package com.example.exerciseday04.repository;

import com.example.exerciseday04.model.Customer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Repository {
    private  ArrayList<Customer> allCustomer=new ArrayList<>();

    public Repository() {
        ArrayList<Customer> arrayList=new ArrayList<>();
        arrayList.add(new Customer(1,"Nguyen Van Quang","quang@gmail.com",123456789L));
        arrayList.add(new Customer(2,"Nguyen Duy Quang","duyquang@gmail.com",123186789L));
        arrayList.add(new Customer(3,"Le Anh Tuan","tuan@gmail.com",1234181789L));
        arrayList.add(new Customer(4,"Nguyen Dinh Kien","kien@gmail.com",1214956789L));
        arrayList.add(new Customer(5,"Nguyen Duy Manh","manh@gmail.com",1127916789L));

        allCustomer.addAll(arrayList);
    }

    public ArrayList<Customer> getAllCustomer(){
        return allCustomer;
    }
    public Optional<Customer> get(int id) {
        return allCustomer.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Customer create(Customer customer){
        int id;
        if (allCustomer.isEmpty()) {
            id = 1;
        } else {
            Customer lastCustomer = allCustomer.get(allCustomer.size() - 1);
            id = lastCustomer.getId() + 1;
        }
        customer.setId(id);
        allCustomer.add(customer);
        return customer;
    }

    public Customer edit(Customer customer){
        get(customer.getId()).ifPresent(existPerson->{
            existPerson.setFullName(customer.getFullName());
            existPerson.setEmail(customer.getEmail());
            existPerson.setTelephone(customer.getTelephone());
        });
        return customer;
    }

    public void delete(Customer customer){
        deleteById(customer.getId());
    }

    public void deleteById(int id) {
        get(id).ifPresent(existed -> allCustomer.remove(existed));
    }
    public Customer search(String email){
        return allCustomer.stream().filter(customer->customer.getEmail().contains(email)).findAny().orElse(null);
    }
    public void sortByFullName(String direction){
        allCustomer.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (direction.equals("asc")){
                    return o1.getFullName().compareTo(o2.getFullName());
                }
               if (direction.equals("desc")){
                   return o2.getFullName().compareTo(o1.getFullName());
               }
               return 0;
            }
        });
    }
}
