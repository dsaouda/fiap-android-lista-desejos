
package com.github.dsaouda.wishlist.dto.b2w;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links___ {

    @SerializedName("self")
    @Expose
    private Self__ self;

    public Self__ getSelf() {
        return self;
    }

    public void setSelf(Self__ self) {
        this.self = self;
    }

}
