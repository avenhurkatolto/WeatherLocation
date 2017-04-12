
package com.zsoltdavid.weatherlocation.data.model.current;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private float speed;
    @SerializedName("gust")
    @Expose
    private Double gust;

    /**
     * @return The speed
     */
    public int getSpeed() {
        return Math.round(speed);
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return The gust
     */
    public Double getGust() {
        return gust;
    }

    /**
     * @param gust The gust
     */
    public void setGust(Double gust) {
        this.gust = gust;
    }

}
