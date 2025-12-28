package com.ruoyi.trainquery.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 座位剩余+票价VO
 */
public class SeatVO {
    private String seatType;       // 二等座/一等座
    private Integer remainSeats;   // 剩余座位数
    private Double price;          // 票价

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("seatType", seatType)
                .append("remainSeats", remainSeats)
                .append("price", price)
                .toString();
    }

    // get/set
    public String getSeatType() { return seatType; }
    public void setSeatType(String seatType) { this.seatType = seatType; }
    public Integer getRemainSeats() { return remainSeats; }
    public void setRemainSeats(Integer remainSeats) { this.remainSeats = remainSeats; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}