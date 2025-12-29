package com.ruoyi.trainbook.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订票提交参数VO（强类型接收前端参数）
 *
 * @author ruoyi
 */
public class TrainBookSubmitVO {
    /** 车次ID（必填） */
    @NotNull(message = "车次ID不能为空")
    private Long scheduleId;

    /** 车次号（必填） */
    @NotBlank(message = "车次号不能为空")
    private String trainNo;

    /** 出发站（必填） */
    @NotBlank(message = "出发站不能为空")
    private String startStation;

    /** 到达站（必填） */
    @NotBlank(message = "到达站不能为空")
    private String endStation;

    /** 发车日期（必填，适配前端yyyy-MM-dd格式） */
    @NotNull(message = "发车日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departDate;

    /** 乘客姓名（必填） */
    @NotBlank(message = "乘客姓名不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,10}$", message = "姓名必须是2-10个中文字符")
    private String passengerName;

    /** 身份证号（必填+格式校验） */
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",
            message = "请输入正确的18位身份证号")
    private String idCard;

    /** 手机号（必填+格式校验） */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的11位手机号")
    private String phone;

    /** 车厢号（必填） */
    @NotBlank(message = "车厢号不能为空")
    @Pattern(regexp = "^[0-9]{1,2}$", message = "车厢号为1-2位数字（如：01、02）")
    private String carriageNo;

    /** 座位号（必填） */
    @NotBlank(message = "座位号不能为空")
    @Pattern(regexp = "^[0-9]{1,2}[A-Za-z]$", message = "座位号格式如：01A、05B")
    private String seatNo;

    /** 座位类型（必填） */
    @NotBlank(message = "座位类型不能为空")
    private String seatType;

    /** 票价（必填+正数） */
    @NotNull(message = "票价不能为空")
    @Positive(message = "票价必须大于0")
    private BigDecimal ticketPrice;

    // ========== Getter/Setter ==========
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}