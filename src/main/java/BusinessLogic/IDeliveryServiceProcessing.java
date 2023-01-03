package BusinessLogic;

import javax.swing.*;

public interface IDeliveryServiceProcessing {
     void intervalOfHour();
     void productOrderedNTimes();
     void ordersOfTimeAndValue();
     void orderDay();
     String builderOfDetails (MenuItem myItem);
     MenuItem findByName(String name);
     void updateMenuForComboBox (JComboBox e);
}
