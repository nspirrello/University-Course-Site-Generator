/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.Data.CSGData;
import jtps.jTPS_Transaction;

/**
 *
 * @author zhaotingyi
 */
public class TAtoggleUR implements jTPS_Transaction{
    
    private String TAname;
    private String cellKey;
    private CSGData data;
    
    public TAtoggleUR(String TAname, String cellKey, CSGData data){
        this.TAname = TAname;
        this.cellKey = cellKey;
        this.data = data;
    }
    

    @Override
    public void doTransaction() {
        data.getTaData().toggleTAOfficeHours(cellKey, TAname);
    }

    @Override
    public void undoTransaction() {
        data.getTaData().toggleTAOfficeHours(cellKey, TAname);
    }
    
}
