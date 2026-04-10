/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author lukaa
 */
public class Request implements Serializable {
    private Operacija op;
    private Object param;

    public Request() {
    }

    public Request(Operacija op, Object param) {
        this.op = op;
        this.param = param;
    }

    public Operacija getOp() {
        return op;
    }

    public void setOp(Operacija op) {
        this.op = op;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
