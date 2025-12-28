package com.ruoyi.trainbook.mapper;

import com.ruoyi.trainbook.domain.Passenger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 乘客信息Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface PassengerMapper
{
    /**
     * 查询乘客信息
     *
     * @param id 乘客主键
     * @return 乘客信息
     */
    public Passenger selectPassengerById(Long id);

    /**
     * 根据身份证号查询乘客
     *
     * @param idCard 身份证号
     * @return 乘客信息
     */
    public Passenger selectPassengerByIdCard(@Param("idCard") String idCard);

    /**
     * 查询乘客列表
     *
     * @param passenger 乘客信息
     * @return 乘客集合
     */
    public List<Passenger> selectPassengerList(Passenger passenger);

    /**
     * 新增乘客信息
     *
     * @param passenger 乘客信息
     * @return 结果
     */
    public int insertPassenger(Passenger passenger);

    /**
     * 修改乘客信息
     *
     * @param passenger 乘客信息
     * @return 结果
     */
    public int updatePassenger(Passenger passenger);

    /**
     * 删除乘客信息
     *
     * @param id 乘客主键
     * @return 结果
     */
    public int deletePassengerById(Long id);

    /**
     * 批量删除乘客信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePassengerByIds(Long[] ids);
}