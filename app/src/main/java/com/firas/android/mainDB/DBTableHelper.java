package com.firas.android.mainDB;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Feras on 1.04.2018.
 */

public class DBTableHelper {

   public static Map<TableType,DBTable> tableMap = new HashMap<>();
    static {
        tableMap.put(TableType.CHILDREN_TABLE,new ChildrenTable());
        tableMap.put(TableType.DEVICE_TABLE,new DeviceTable());
        tableMap.put(TableType.MEASUREMENT_TABLE,new MeasurementTable());
        tableMap.put(TableType.NOTIFICATIONS_TABLE,new NotificationsTable());
    }
}
