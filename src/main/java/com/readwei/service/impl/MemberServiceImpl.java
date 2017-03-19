package com.readwei.service.impl;

import com.readwei.entity.Member;
import com.readwei.mapper.MemberMapper;
import com.readwei.service.IMemberService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements IMemberService {
	
}
