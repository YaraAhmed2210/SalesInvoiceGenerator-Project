/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SIGController;

import SIGModel.Invoice;
import SIGModel.Item;
import SIGView.homeGUI;
import SIGView.createNewInvoice;
import SIGView.createNewItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */


public class guiActionListener implements ActionListener {

    filesInputandOutput y;
    private static homeGUI myHomeScreen;
    private static createNewInvoice myInvoiceFrame;
    private static createNewItem myItemFrame;
    
    static  File allInvoicesFile1;
    static File allItemsFile1;
    
    static ArrayList<Invoice> allInv ;
    static ArrayList<Item> allItm ;
    
    String selectedInvoiceNum;
    
    public guiActionListener(homeGUI homeScreen){
        myHomeScreen=homeScreen;
    }
    
    public guiActionListener(createNewItem itemFrame){
        
        myItemFrame=itemFrame; 
    }
    
    public guiActionListener(homeGUI homeScreen, createNewInvoice invoiceFrame,createNewItem itemFrame) {
        myHomeScreen=homeScreen;
        myInvoiceFrame=invoiceFrame;
        myItemFrame=itemFrame; 
        //myInvoiceFrame.setController(this);
    }


    public guiActionListener(createNewInvoice newInv) {

        myInvoiceFrame=newInv;
    }
    

   /* public guiActionListener(homeGUI aThis, createNewInvoice create, createNewItem createItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            y=new filesInputandOutput();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(guiActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (e.getActionCommand()){
            
            case "LoadAllInvoices":  //done            
                loadInvoicesFile();
                break;
                
            case "Load all Items":  //done
                loadItemsFile();   
                break;
           
            case "Save Files":  //done
                saveFiles();
                System.out.println("Save files");
                break;
                
            case "Create New Invoice":  //done --> open new frame for creation
                createNewInvoice();
                System.out.println("create new inv");
                break;   
                
            case "submitCreateInvoice":  //done
                submitCreateInvoice();
                break;
                
            case "Delete Invoice":  //done
                deleteInvoice();
                System.out.println("del inv");
                break;
                
            case "Create New Item": //done -> open new frame for creation
                createNewItem();
                System.out.println("create new itm");
                break;
                
                
            case "submitCreateItem": //done 
                submitCreateNewItem();
                System.out.println("create new itm");
                break;
                
                
            case "Delete Item":
                deleteItem();
                System.out.println("del itm");
                break;
                
            case "Save": //done
                saveAddedItem();
                System.out.println("save itm");
                break;
                
            case "Cancel":
                Cancel();
                break;
            case "CancelAdding": //done
                myItemFrame.dispose();
                System.out.println("cancel small frame");
                break;
                
                
        }
        
        
    
    
    
    }
    public void loadInvoicesFile() { 
                System.out.println("Load all invoices clicked");
                JFileChooser chooseFile1= new JFileChooser();
                int res1=chooseFile1.showOpenDialog(myHomeScreen);
                
                if(res1==JFileChooser.APPROVE_OPTION){
                     allInvoicesFile1=chooseFile1.getSelectedFile();
            
             try {
                y.readFromFileAllInvoices(allInvoicesFile1);
             } catch (FileNotFoundException ex) {
         JOptionPane.showMessageDialog(myHomeScreen,"Please select a file!","Error",JOptionPane.ERROR_MESSAGE); 
             }
            
                }
                
                myHomeScreen.setAllInvoicesView(allInv);
                
                System.out.println("Load all invoices clicked");       
                
        JOptionPane.showMessageDialog(myHomeScreen,"File \""+allInvoicesFile1.getName()+ "\" is uploaded.","SUCCESS",JOptionPane.INFORMATION_MESSAGE); 
    }
    
    public void loadItemsFile(){
         JFileChooser chooseFile2= new JFileChooser();
                int res2=chooseFile2.showOpenDialog(myHomeScreen);
                if(res2==JFileChooser.APPROVE_OPTION){
                     allItemsFile1=chooseFile2.getSelectedFile();
            try {
               y.readFromFileAllItems(allItemsFile1);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(myHomeScreen,"Please select a file!","Error",JOptionPane.ERROR_MESSAGE);  
            }
                }
                System.out.println("Load all items clicked");
                
                y.addItemsToInvoice(allInv,allItm);
                
               // y.addItemsToInvoice();
                myHomeScreen.setAllItemsView(allItm);
                myHomeScreen.loadInvoicesToTable(allInv);     
                
    JOptionPane.showMessageDialog(myHomeScreen,"File \""+allItemsFile1.getName()+ "\" is uploaded.","SUCCESS",JOptionPane.INFORMATION_MESSAGE); 
  
    }
    
    public Invoice getInvDetails(String getSelectedRowValue){
       return y.getInvoiceDetails(getSelectedRowValue);
    }
    
    void createNewInvoice(){
        myInvoiceFrame.setVisible(true);
        System.out.println("big create");
         
    }
    void submitCreateInvoice(){
          //String invDate=myInvoiceFrame.getTxtInvDate.getText();
        String invDate=myInvoiceFrame.getTxtInvDate().getText();
        String custName=myInvoiceFrame.getTxtCustName().getText();
        
        
        System.out.println(invDate);
        System.out.println(custName);
        
        Invoice x= y.createInvoice(invDate, custName);
        
        System.out.println(x.invoiceID);
        
        System.out.println(x.getInvoiceDate());
        System.out.println(x.getCustomerName());
        
        
        
            System.out.println("invoices after addition");
        
        for(int i=0; i<allInv.size(); i++){
            System.out.println(allInv.get(i).getCustomerName());
        }
        
        //allInv.add(x);
        myInvoiceFrame.dispose(); 
     
        
        
        JOptionPane.showMessageDialog(myHomeScreen,"Invoice Created Successfully :) \n To Save Changes: Click Save in the menu bar. ","SUCCESSED",JOptionPane.INFORMATION_MESSAGE); 
        //JOptionPane.showMessageDialog(myHomeScreen,"Invoice added successfully :)","SUCCESSED",JOptionPane.INFORMATION_MESSAGE); 
        //myInvoiceFrame.dispose();  
    }
    void deleteInvoice(){  
        
        String []rows=new String[allInv.size()-1];
        String getSelectedRowValue=myHomeScreen.getSelectedInvoice();
        System.out.println("selected invoice is: "+ getSelectedRowValue);
        y.deleteInvoice(getSelectedRowValue);
        
         myHomeScreen.displayDataAfterDeleteInvoice(allInv);      
    }
    
    void saveFiles(){
        
         try {
             y.writeToFileAllInvoices(allInvoicesFile1);
             y.writeToFileAllItems(allItemsFile1);
         } catch (IOException ex) {
           //  Logger.getLogger(homeGUI.class.getName()).log(Level.SEVERE, null, ex);
             
                JOptionPane.showMessageDialog(myHomeScreen,"problem in saving !","Problem",JOptionPane.ERROR_MESSAGE);  
         }
    }
    
    void createNewItem(){
        
        selectedInvoiceNum=myHomeScreen.getSelectedInvoice();
        
        System.out.println("sel inv num big :" + selectedInvoiceNum);
        myItemFrame.setSelectedInvNum(selectedInvoiceNum);
        
        myItemFrame.setVisible(true);
        System.out.println("big create");
    }
    
    void submitCreateNewItem(){
        
        String itmName=myItemFrame.getTxtItmName().getText();
        String itmPrice=myItemFrame.getTxtItmPrice().getText();
        String itmCount=myItemFrame.getTxtItmCount().getText();
            
       // System.out.println(itmName+ " - "+itmPrice+" - "+ itmCount);
        
        String getSelectedOne=myItemFrame.getSelectedInvNum();
        //createItem(String invoiceNum, String itmName, int price1,int count1 )
 
        //System.out.println("sel inv num small : " + getSelectedOne);
        y.createItem(getSelectedOne, itmName,Integer.parseInt(itmPrice) ,Integer.parseInt(itmCount));
        y.addThisItemtoInvoice( getSelectedOne,  itmName);
       
        
        
        myItemFrame.dispose();
        //JOptionPane.showMessageDialog(myHomeScreen,"Item Added to Invoice "+ getSelectedOne +" Successfully :) \nTo Save Changes: Click on Save Button ","SUCCESSED",JOptionPane.INFORMATION_MESSAGE); 
        
        Item x= new Item();
        x.setInvoiceNum(getSelectedOne);
        x.setItemName(itmName);
        x.setPrice(Integer.parseInt(itmPrice));
        x.setCount(Integer.parseInt(itmCount));
        String totalPrice=null;
        
        System.out.println("selected invoice number is : "+myHomeScreen.getSelectedInvoice());
        
        for(int i=0; i<allInv.size(); i++){
            if(allInv.get(i).invoiceID.equals(myHomeScreen.getSelectedInvoice())){
               totalPrice= Integer.toString(allInv.get(i).getTotalPrice());
            }            
        }
        
        myHomeScreen.showNewItemCreated(x,totalPrice);
        
        myItemFrame.getTxtItmName().setText("");
        myItemFrame.getTxtItmPrice().setText("");
        myItemFrame.getTxtItmCount().setText("");
        
        
    /*
        int total=selectedInvoice.getTotalPrice();
        lbl_invTotalValue.setText(Integer.toString(total));*/
    }
    
    void saveAddedItem(){
        
        try {
            y.writeToFileAllItems(allItemsFile1);
        } catch (IOException ex) {
            Logger.getLogger(homeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        myHomeScreen.resetItems();
        JOptionPane.showMessageDialog(myHomeScreen,"Item Saved Successfully :) ","SUCCESSED",JOptionPane.INFORMATION_MESSAGE);        
    }
    void deleteItem(){
        
        String getSelectedInvoiceID=myHomeScreen.getSelectedInvoice();
        System.out.println("selected invoice is: "+ getSelectedInvoiceID);
         
        String getSelectedItemName =myHomeScreen.getSelectedItem();
        System.out.println("selected invoice is: "+ getSelectedItemName);
      
        y.deleteItem(getSelectedInvoiceID, getSelectedItemName);
      
        String totalPrice=null;
       
        for(int i=0; i<allInv.size(); i++){
            if(allInv.get(i).invoiceID.equals(myHomeScreen.getSelectedInvoice())){
               totalPrice= Integer.toString(allInv.get(i).getTotalPrice());
            }            
        }
        
        myHomeScreen.showItemsAfterDelete(allItm, totalPrice);
        
    }
    void Cancel(){
        
        try {
            y= new filesInputandOutput();            
             y.readFromFileAllInvoices(allInvoicesFile1);
             y.readFromFileAllItems(allItemsFile1);
            y.addItemsToInvoice(allInv,allItm);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(guiActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        
         
         try {
             y= new filesInputandOutput();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(homeGUI.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             y.readFromFileAllInvoices();
             y.readFromFileAllItems();
             y.addItemsToInvoice();
             
             DefaultTableModel tblModel=(DefaultTableModel)leftTable.getModel();
             tblModel.setRowCount(0);
             
             for(int i=0; i<y.allInvoices.size();i++){
                 String totalPrice1= Integer.toString(y.allInvoices.get(i).getTotalPrice());
                // System.out.println("price i="+i+" -> "+totalPrice);
                
                 String row[]={y.allInvoices.get(i).invoiceID, y.allInvoices.get(i).invoiceDate, y.allInvoices.get(i).customerName,totalPrice1};
                 
                 tblModel.addRow(row);
             }
         
         
         } catch (FileNotFoundException ex) {
             Logger.getLogger(homeGUI.class.getName()).log(Level.SEVERE, null, ex);
         }
       

             DefaultTableModel tblModelRight=(DefaultTableModel)rightTable.getModel();
             tblModelRight.setRowCount(0);
             lbl_invNumValue.setText("-");
             txt_custName.setText("");
             txt_invDate.setText("");
             lbl_invTotalValue.setText("-");


        
        
        
        
        */
        
        
        myHomeScreen.loadInvoicesToTable(allInv);
    }
    
}
