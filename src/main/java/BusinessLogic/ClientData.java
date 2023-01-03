package BusinessLogic;

import java.io.Serializable;

public class ClientData implements Serializable {
    private String name;
    private String pass;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientData(String name, String pass, Integer id) {
        this.name = name;
        this.pass = pass;
        this.id = id;
    }

    public ClientData() {
    }
}
