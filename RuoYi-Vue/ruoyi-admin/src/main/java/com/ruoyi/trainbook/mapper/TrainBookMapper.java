package com.ruoyi.trainbook.mapper;

import com.ruoyi.trainbook.domain.TrainBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 火车订票Mapper接口（注解方式，无需XML也可运行）
 *
 * @author ruoyi
 */
@Repository
public interface TrainBookMapper {
    /**
     * 新增订票记录
     *
     * @param trainBook 订票实体
     * @return 影响行数
     */
    @Insert("INSERT INTO train_book (" +
            "order_no, schedule_id, train_no, start_station, end_station, depart_date, " +
            "passenger_name, id_card, phone, carriage_no, seat_no, seat_type, ticket_price, " +
            "create_time) " + // 删除了update_time列名
            "VALUES (" +
            "#{book.orderNo}, #{book.scheduleId}, #{book.trainNo}, #{book.startStation}, #{book.endStation}, #{book.departDate}, " +
            "#{book.passengerName}, #{book.idCard}, #{book.phone}, #{book.carriageNo}, #{book.seatNo}, #{book.seatType}, #{book.ticketPrice}, " +
            "NOW())") // 删除了最后一个NOW()（对应update_time的值）
    int insertTrainBook(@Param("book") TrainBook trainBook);
}