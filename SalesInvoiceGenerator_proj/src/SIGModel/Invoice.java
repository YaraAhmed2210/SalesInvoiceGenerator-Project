/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SIGModel;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Invoice {
 
    public static int invoiceNumber=0;
    public String invoiceID;
    String invoiceDate;
    String customerName;
    public ArrayList<Item> items;
    //Item []itemss;
    int totalPrice=0;

    public Invoice(){
       // invoiceNumber++;
        items=new ArrayList<>();
        totalPrice=this.getTotalPrice();
    }
    public int getTotalPrice(){
        int x=0;
        for(int i=0; i< items.size(); i++){
            x+=items.get(i).price * items.get(i).count;           
        }
        totalPrice=x;
       
       // System.out.println("price fn Invoice i="+" -> "+totalPrice);
        return totalPrice ;
    }
    public void addItem(Item itm){
        items.add(itm);
    }
    public Invoice getInvoiceDetails(){
        Invoice tempInvoice= new Invoice();
        tempInvoice.invoiceDate=this.invoiceDate;
        tempInvoice.customerName=this.customerName;
        tempInvoice.items=this.items;
        tempInvoice.totalPrice=this.totalPrice;
        return tempInvoice;
    }

    public String getInvoiceDate(){
        
        return this.invoiceDate;
    }
    public void setInvoiceDate( String invDate){
        this.invoiceDate=invDate;
    }

    public String getCustomerName(){
        
        return this.customerName;
    }
    
    public void setCustomerName( String custName){
        this.customerName=custName;
    }

   
}
