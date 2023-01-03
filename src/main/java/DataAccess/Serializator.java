package DataAccess;

import java.io.*;

public class Serializator implements Serializable {

    /**
     * Se face serializarea
     * @param nameFile numele fisierului unde se va face serializarea
     * @param o un obiect oarecare
     */
       public void writeData(String nameFile, Object o)
        {
            try {
                FileOutputStream fileOut =
                        new FileOutputStream(nameFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(o);
                out.close();
                fileOut.close();
                System.out.print("Serialized data is saved in file.ser");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

    /**
     * Se face deserializarea
     * @param nameFile numele fisierului de unde se face deserializarea
     * @return un obiect ce a fost serializat in fisier
     */
        public Object readData(String nameFile)
        {
            Object e = null;
            try {
                FileInputStream fileIn = new FileInputStream(nameFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                e = in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.print("DeliveryService class not found");
                c.printStackTrace();
            }
            return e;
        }

}
