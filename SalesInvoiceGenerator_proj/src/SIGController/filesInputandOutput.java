/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SIGController;

/**
 *
 * @author Dell
 */
import SIGModel.Invoice;
import SIGModel.Item;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class filesInputandOutput {
    
   // String file_path_allInvoices = "C:\\Users\\Dell\\Desktop\\sales-invoice-generator (1)\\Sales Invoice Generator\\InvoiceHeader.csv";
   // String file_path_allItems = "C:\\Users\\Dell\\Desktop\\sales-invoice-generator (1)\\Sales Invoice Generator\\InvoiceLine.csv";

    String file_path_allInvoices = "InvoiceHeader.csv";
    String file_path_allItems = "InvoiceLine.csv";

   public   ArrayList<Invoice> allInvoices = new ArrayList<>(); //array list to load all invoices in it
   public   ArrayList<Item> allItems= new ArrayList<>(); //array list to load all invoices in it


    public filesInputandOutput() throws FileNotFoundException {

        
        
    }

    /**
     * Read all invoices from InvoiceHeader.csv file
     * /
     ************************************************************************************/

    /**
     * Read all invoices from InvoiceHeader.csv file
 /
     * @throws java.io.FileNotFoundException
     */

    /**
     * Read all invoices from InvoiceHeader.csv file
 /
     * @return
     * @throws java.io.FileNotFoundException
     */
    public void readFromFileAllInvoices(File allInvoicesFile) throws FileNotFoundException {
       
        //allInvoices.clear();
        guiActionListener.allInv= new ArrayList<>();
   
        Scanner sc = new Scanner(allInvoicesFile);  //scanner to get csv file in it as an input
        sc.useDelimiter(",");

        ArrayList<String> cells = new ArrayList<>(); //array list of String to get value of each cell in it

        while (sc.hasNext()) { //if the scanner still have values

            cells.add(sc.next());
        }
        sc.close(); //close scanner

        for (int j = 0; j < cells.size() - 1; j = j + 3) {

            String temp = cells.get(j);
            String noBreaklineStr = temp.replaceAll("\n", "");//remove breaklines

            Invoice tempInvoice = new Invoice();
            tempInvoice.invoiceID = noBreaklineStr;

            String tempDate=cells.get(j + 1);
            
            tempInvoice.setInvoiceDate(tempDate);

            String temp2 = cells.get(j + 2);
            String noBreaklineStr2 = temp2.replaceAll("\n", "");//remove breaklines
            tempInvoice.setCustomerName(noBreaklineStr2);
            guiActionListener.allInv.add(tempInvoice);

        }
        
        Invoice lastInvoiceIDvalue = guiActionListener.allInv.get(guiActionListener.allInv.size() - 1);
        Invoice.invoiceNumber = Integer.parseInt(lastInvoiceIDvalue.invoiceID);
        Invoice.invoiceNumber=Integer.parseInt(guiActionListener.allInv.get(guiActionListener.allInv.size()-1).invoiceID);

        for(int i=0; i<guiActionListener.allInv.size();i++){
            Invoice temp=guiActionListener.allInv.get(i);
            System.out.println("Invoice No.: "+temp.invoiceID+ " Invoice Date: "+temp.getInvoiceDate()+ " Customer Name: "+temp.getCustomerName());
        }
        
        
       // return guiActionListener.allInv;

    }

    /**
     * Read all items sold from InvoiceLine.csv file
     * /
     ************************************************************************************/
    public void readFromFileAllItems(File allItemsFile) throws FileNotFoundException {
       
        guiActionListener.allItm= new ArrayList<>();

//scanner to get csv file in it as an input
        Scanner sc = new Scanner(allItemsFile);
        sc.useDelimiter(",");
        //array list of String to get value of each cell in it
        ArrayList<String> cells = new ArrayList<String>();

        while (sc.hasNext()) { //if the scanner still have values

            cells.add(sc.next());
        }
        sc.close(); //close scanner

        for (int j = 0; j < cells.size() - 1; j = j + 4) {

            String temp = cells.get(j);
            String noBreaklineStr = temp.replaceAll("\n", "");//remove breaklines

            Item tempItem = new Item();

           // tempItem.invoiceNumber = noBreaklineStr; //invoice number
            tempItem.setInvoiceNum(noBreaklineStr);

            
           // tempItem.itemName = cells.get(j + 1); //item name
            tempItem.setItemName(cells.get(j + 1));

            tempItem.setPrice(Integer.parseInt(cells.get(j + 2) ));
            //tempItem.price = Integer.parseInt(cells.get(j + 2) ); //item price

            String temp2 = cells.get(j + 3);

            String noBreaklineStr2 = temp2.replaceAll("\n", "z");//remove breaklines
           // tempItem.count = Integer.parseInt(noBreaklineStr2);
            tempItem.setCount(Integer.parseInt(noBreaklineStr2));


           guiActionListener.allItm.add(tempItem);


        }

        for(int i=0; i<guiActionListener.allItm.size(); i++){
            Item temp=guiActionListener.allItm.get(i);
            System.out.println("Invoice No.: "+temp.getInvoiceNum()+" Item Name: "+temp.getItemName()+" Price: "+Integer.toString(temp.getPrice())+" Count: "+Integer.toString(temp.getCount()));
        }
      //  return guiActionListener.allItm;
    }

    public void addThisItemtoInvoice(String invNum, String itmName){
        
         
        for(int i=0; i<guiActionListener.allInv.size(); i++){ //parse all the invoices

            if(guiActionListener.allInv.get(i).invoiceID.equals(invNum)){
                
                for(int j=0; j<guiActionListener.allItm.size();j++){
                    if((guiActionListener.allItm.get(j).getInvoiceNum().equals(invNum)) &&  guiActionListener.allItm.get(j).getItemName().equals(itmName)   ){
                        guiActionListener.allInv.get(i).items.add(guiActionListener.allItm.get(j));
                    }
                }
                
            }
        }
           // tempInvoice=allInvoices.get(i); //put it in the variable
/*
                for(int j=0; j<allItems.size(); j++){ //check all items have the same invoice ID
                    if(allItems.get(j).invoiceNumber.equals(allInvoices.get(i).invoiceID)){ //once you find items having same invoice id
                        allInvoices.get(i).addItem(allItems.get(j)); // add it to its invoice
                    }
                }*/

        
    }
    

//** put items to its invoice
/************************************************************************************/
    public void addItemsToInvoice(ArrayList<Invoice> tempInvList, ArrayList<Item> tempItemList){

      //  allItems= new ArrayList<>();
      
      guiActionListener.allInv=tempInvList;
      guiActionListener.allItm=tempItemList;

        for(int i=0; i<guiActionListener.allInv.size(); i++){ //parse all the invoices

           // tempInvoice=allInvoices.get(i); //put it in the variable

                for(int j=0; j< guiActionListener.allItm.size(); j++){ //check all items have the same invoice ID
                    if( guiActionListener.allItm.get(j).getInvoiceNum().equals(guiActionListener.allInv.get(i).invoiceID)){ //once you find items having same invoice id
                        guiActionListener.allInv.get(i).addItem( guiActionListener.allItm.get(j)); // add it to its invoice
                    }
                }

        }
    }
     /** Get Invoice details of selected one
     /************************************************************************************/

     public Invoice getInvoiceDetails(String invoiceID){
       //variable to put the found invoice in it
         Invoice  selectedInvoice= new Invoice();
         for(int i=0; i< guiActionListener.allInv.size(); i++){ //parse all the invoices

            // System.out.println("itr"+i);
             if(guiActionListener.allInv.get(i).invoiceID.equals(invoiceID)){ //when you find the selected invoice

                // System.out.println("invoice found");
                // System.out.println("your name is : "+allInvoices.get(i).customerName);
                   selectedInvoice=guiActionListener.allInv.get(i); //put it in the variable
                 /*for(int j=0; j<allItems.size(); j++){ //check all items have the same invoice ID
                     if(allItems.get(j).invoiceNumber.equals(invoiceID)){ //once you find items having same invoice id
                         selectedInvoice.addItem(allItems.get(j)); // add it to its invoice

                     //    System.out.println("item name is" + selectedInvoice.items.get(0).itemName);
                     }
                }*/
                 
            }
         }
     //    System.out.println("customer name is" + selectedInvoice.customerName);
       
     return selectedInvoice;
     }

    /** Create New Invoice
     /************************************************************************************/
    public Invoice createInvoice(String invDate, String customerName) {
       
        Invoice.invoiceNumber++;
        Invoice x=new Invoice();
        x.invoiceID=Integer.toString(Invoice.invoiceNumber);
       // x.invoiceDate=invDate;
        x.setInvoiceDate(invDate);
       // x.customerName=customerName;
        x.setCustomerName(customerName);
        guiActionListener.allInv.add(x);
      //  this.writeToFileAllInvoices();
      return x;
        
    }

     /** Delete an Existing Invoice
     * @param invID/************************************************************************************/
    public void deleteInvoice(String invID){
        
        for(int i=0; i<guiActionListener.allInv.size(); i++){
            if(guiActionListener.allInv.get(i).invoiceID.equals(invID)){
                guiActionListener.allInv.remove(i);
            }     
        }
        for(int i=0; i<guiActionListener.allItm.size(); i++){
            if(guiActionListener.allItm.get(i).getInvoiceNum().equals(invID)){
                guiActionListener.allItm.remove(i);
            }     
        }

    }

     /** Create new Item
     /************************************************************************************/
    public void createItem(String invoiceNum, String itmName, int price1,int count1 ){
     
        Item x= new Item();
       // x.invoiceNumber=invoiceNum;
        x.setInvoiceNum(invoiceNum);
       // x.itemName=itmName;
       x.setItemName(itmName);
       x.setPrice(price1);
     //  x.price=price1;
       x.setCount(count1);
     
       // x.count=count1;
        guiActionListener.allItm.add(x);
        
      /*  for(int i=0; i< allInvoices.size(); i++){
            if(allInvoices.get(i).equals(invoiceNum)){
                allInvoices.get(i).items.add(x);
        }*/
        
    }
    

     /** delete existing item 
     /************************************************************************************/
     
    public void deleteItem(String invoiceNum, String itmName){
        
        for(int i=0; i<guiActionListener.allInv.size();i++){
            if(guiActionListener.allInv.get(i).invoiceID.equals(invoiceNum)){
                //System.out.println("invoice found akheran");
                
                for(int j=0; j<guiActionListener.allInv.get(i).items.size(); j++){
                       if((guiActionListener.allInv.get(i).items.get(j).getItemName()).equals(itmName)){
                           // System.out.println("item found aho");
                           guiActionListener.allInv.get(i).items.remove(j);
                       }
                }
            }
        }
        
        for(int i=0; i<guiActionListener.allItm.size(); i++){
            if(guiActionListener.allItm.get(i).getItemName().equals(itmName)){
                guiActionListener.allItm.remove(i);
            }     
        }
    
    }
     /** Save all invoices to file after edit, delete, create functions.
     /************************************************************************************/
    public void writeToFileAllInvoices(File allInvoicesFile) throws IOException {

   /*    Invoice x=new Invoice();
        x.invoiceID="2";
        x.invoiceDate="20-11-2022";
        x.customerName="yara";
        allInvoices.add(x);
*/

      //  File file = new File( file_path_allInvoices);
        if ( !allInvoicesFile.exists() )
            allInvoicesFile.createNewFile();

        FileWriter fw = new FileWriter(allInvoicesFile,false);
        BufferedWriter writer = new BufferedWriter( fw );


        for(int i=0;i<guiActionListener.allInv.size();i++){
            Invoice tempInvoice=guiActionListener.allInv.get(i);
            writer.write(tempInvoice.invoiceID);
            writer.write(',');
          /*  LocalDate date1=tempInvoice.invoiceDate;
            String day=Integer.toString(date1.getDayOfMonth());
            String month=Integer.toString(date1.getMonthValue());
            String year=Integer.toString(date1.getYear());
            String myDate=day+"-"+month+"-"+year;*/
            writer.write(tempInvoice.getInvoiceDate());
            writer.write(',');
            writer.write(tempInvoice.getCustomerName());
            writer.write(',');
            writer.write("\n");

        }
        writer.close();
        fw.close();

    }

     /** Save all items' transactions to file after after edit, delete, create functions.
     /************************************************************************************/
    public void writeToFileAllItems(File allItemsFile) throws IOException {

       /* Item x=new Item();
        x.invoiceNumber="1";
        x.itemName="laptop";
        x.price="270";
        x.count="1";
        allItems.add(x);
        
        Item y=new Item();
        y.invoiceNumber="2";
        y.itemName="laptop";
        y.price="5000";
        y.count="1";
        allItems.add(y);
*/
      //  File file = new File( file_path_allItems);
      
        if ( !allItemsFile.exists() )
            allItemsFile.createNewFile();

        FileWriter fw = new FileWriter(allItemsFile,false);
        BufferedWriter writer = new BufferedWriter( fw );


        for(int i=0;i<guiActionListener.allItm.size();i++){
            Item tempItem=guiActionListener.allItm.get(i);
            writer.write(tempItem.getInvoiceNum());
            writer.write(',');
            writer.write(tempItem.getItemName());
            writer.write(',');
            writer.write(Integer.toString(tempItem.getPrice()));
            writer.write(',');
            writer.write(Integer.toString(tempItem.getCount()));
            writer.write(',');
            writer.write("\n");

        }
        writer.close();
        fw.close();


    }
    

    
}
