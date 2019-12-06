package m19.notificatins;

import m19.requests.Request;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ArrayList;
import java.text.Collator;
import java.util.TreeMap;
import java.util.Locale;
import java.util.List;
import java.util.Map;

public class Notification {
    private String _notification;

    public Notification(String notification) {
        _notification = notification;
    }

    public String getNotification() {
        return _notification;
    }
}
