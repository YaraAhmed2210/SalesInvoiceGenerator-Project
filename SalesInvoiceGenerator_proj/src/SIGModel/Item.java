/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SIGModel;

/**
 *
 * @author Dell
 */
public class Item {
    String invoiceNumber;
    String itemName;
    int price;
    int count;    


public String getItemPriceTotal(){

    int pricee=price;
    int countt=count;

    int totalPrice=pricee*countt;

    return Integer.toString(totalPrice);
}


public String getInvoiceNum(){
    return this.invoiceNumber;
}

public String getItemName(){
    return this.itemName;
}

public int getPrice(){
    return this.price;
}

public int getCount(){
    return this.count;
}


public void setInvoiceNum(String invNum){
    this.invoiceNumber=invNum;
}

public void setItemName(String itmName){
    this.itemName=itmName;
}
public void setPrice(int price1){
    this.price=price1;
}
public void setCount(int count1){
    this.count=count1;
}
}

