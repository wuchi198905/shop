package com.example.shop.pub.service;

import com.example.shop.management.bean.Activity;
import com.example.shop.management.bean.EnrollmentStaff;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 活动报名人员 服务类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
public interface EnrollmentStaffService extends IService<EnrollmentStaff> {

    List<Activity> MyenrollmentStaffpage(EnrollmentStaff enrollmentStaff);
}
