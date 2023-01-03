package DataAccess;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public class DataWriter {

    public DataWriter() {


    }

    /**
     * Crearea chitantei pentru comanda plasata de un client
     * @param orderID id-ul unei comenzi
     * @param nameClient numele unui client
     * @param comanda comanda clientului
     * @param date data la care a comandat clientul comanda
     * @param pret pretul comenzii
     */
    public void makeTheBill(int orderID, String nameClient, Set<String> comanda, LocalDateTime date, int pret)
    {
        try{
            FileWriter fw=new FileWriter("D:\\PT2021_30222_Cardos_Catalin_Assignment_4\\chitanta.txt");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n" + date.toString() +"\n\n    Chitanta pe numele: " + nameClient + "\n");
            stringBuilder.append("    Comanda cu numarul: "+ orderID + "\n");
            stringBuilder.append("    Coamnda contine: " + comanda.toString() + "\n");
            stringBuilder.append("    Pret: " + pret + "\n");
            stringBuilder.append("\n\n\n         COMANDA A FOST PLASATA!");
            fw.write(stringBuilder.toString());
            fw.close();
        }catch(Exception e){System.out.println(e);}
    }
}
