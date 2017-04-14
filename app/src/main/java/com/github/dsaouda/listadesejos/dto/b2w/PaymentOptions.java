
package com.github.dsaouda.listadesejos.dto.b2w;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentOptions {

    @SerializedName("BOLETO")
    @Expose
    private BOLETO bOLETO;

    public BOLETO getBOLETO() {
        return bOLETO;
    }

    public void setBOLETO(BOLETO bOLETO) {
        this.bOLETO = bOLETO;
    }

}
