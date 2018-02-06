package monapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Counter {

    Integer value = 1000;

    public String inc() {
        value++;
        return null;  
    }

    public Integer getValue() {
        return value;
    }

}