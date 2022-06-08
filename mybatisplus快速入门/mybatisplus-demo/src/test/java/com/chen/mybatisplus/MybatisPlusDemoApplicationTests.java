package com.chen.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.mybatisplus.mapper.UserMapper;
import com.chen.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PrintStream;
import java.util.*;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

//	MybatisPlus的一些操作。。。
	@Autowired
	private UserMapper userMapper;

	@Test
	public void test1() {
		Date date = new Date();
		java.sql.Date sql_date = new java.sql.Date(date.getTime());
		User user = new User();
		user.setUsername("数据库自动填充2");
		user.setAddress("武汉2");
		user.setSex("男");
		user.setBirthday(sql_date);
		int res = this.userMapper.insert(user);
		System.out.println(res);
		System.out.println(user);
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(1);
		user.setUsername("李四1");
		user.setSex("女");
		user.setAddress("美国");
		int i = this.userMapper.updateById(user);
		System.out.println(i);
	}

	@Test
	public void test31() {
		User user = (User)this.userMapper.selectById(1);
		user.setSex("男");
		this.userMapper.updateById(user);
	}

	@Test
	public void test32() {
		User user1 = (User)this.userMapper.selectById(1);
		user1.setSex("男");
		user1.setAddress("新西兰");
		User user2 = (User)this.userMapper.selectById(1);
		user2.setSex("女");
		this.userMapper.updateById(user2);
		this.userMapper.updateById(user1);
	}

	@Test
	public void testSelectById() {
		List<User> users = this.userMapper.selectBatchIds(Arrays.asList(1, 24, 25));
		System.out.println("+++++++++++++++++=============+++++++++++++++++");
	}

	@Test
	public void testSelectByBatchId() {
		HashMap<String, Object> map = new HashMap();
		map.put("username", "李四");
		map.put("sex", "女");
		List<User> users = this.userMapper.selectByMap(map);
	}

	@Test
	public void testPage() {
		Page<User> page = new Page(1L, 3L);
		userMapper.selectPage(page, (Wrapper)null);
	}

	@Test
	public void testDeleteById() {
		this.userMapper.deleteById("26");
	}

	@Test
	public void testDeleteBatchIds() {
		int res = this.userMapper.deleteBatchIds(Arrays.asList(24, 25));
		System.out.println("+++++++++++++++++=============+++++++++++++++++");
		System.out.println(res);
	}

	@Test
	public void testDeleteByMap() {
		HashMap<String, Object> map = new HashMap();
		map.put("username", "李四");
		map.put("id", "28");
		userMapper.deleteByMap(map);
	}

}
