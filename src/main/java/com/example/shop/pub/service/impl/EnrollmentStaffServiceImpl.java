package com.example.shop.pub.service.impl;

import com.example.shop.management.bean.Activity;
import com.example.shop.management.bean.EnrollmentStaff;
import com.example.shop.pub.mapper.EnrollmentStaffMapper;
import com.example.shop.pub.service.EnrollmentStaffService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 活动报名人员 服务实现类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Service
public class EnrollmentStaffServiceImpl extends ServiceImpl<EnrollmentStaffMapper, EnrollmentStaff> implements EnrollmentStaffService {
   @Autowired
    private EnrollmentStaffMapper  enrollmentStaffMapper;
    @Override
    public List<Activity> MyenrollmentStaffpage(EnrollmentStaff enrollmentStaff) {
        return enrollmentStaffMapper.MyenrollmentStaffpage(enrollmentStaff);
    }
}
