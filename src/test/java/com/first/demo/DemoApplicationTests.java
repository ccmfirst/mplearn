package com.first.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.first.demo.dao.FooMapper;
import com.first.demo.dao.UserMapper;
import com.first.demo.entity.FooEntity;
import com.first.demo.entity.User;
import com.first.demo.tools.BeanUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("张松");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
        user.setEmail("ccc@qq.com");
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void selectById() {
        Long id = 1088248166370832385L;
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    @Test
    public void selectByIds() {
        List<Long> ids = Arrays.asList(1088248166370832385L, 1088250446457389058L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> con = new HashMap<>();
        con.put("name", "王天风");
        con.put("age", 25);
        List<User> users = userMapper.selectByMap(con);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "风").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "风").between("age", 20, 40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "王").or().ge("age", 20).orderByDesc("age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
        .inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "王").and(wq->wq.lt("age", 40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "王").and(wq->wq.lt("age", 40).ge("age", 20).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.nested(wq->wq.lt("age", 40).or().isNotNull("email"))
        .likeRight("name", "王");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.in("age", Arrays.asList(30,31,32));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.in("age", Arrays.asList(30,31,32)).last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper11() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name").like("name", "风").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void saveMap() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "zs");
        dataMap.put("age", 40);
        dataMap.put("manager_id", 1088248166370832385L);
        int count = this.userMapper.saveData(dataMap);
        System.out.println(count);
    }

    @Autowired
    private FooMapper fooMapper;

    @Test
    public void test(){
        FooEntity fooEntity = new FooEntity();
        fooEntity.setName("SpringBoot中文社区");
        Map<String, Object> condition = BeanUtils.beanToMap(fooEntity, true);

        int retVal = this.fooMapper.insert(condition);

        Integer id = ((BigInteger)condition.get("id")).intValue();

        System.out.println("受到影响的行数:" + retVal + "自增的id:" + id);
    }

    public void insertUser() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "zs");
        dataMap.put("age", 40);
        dataMap.put("manager_id", 1088248166370832385L);
        this.userMapper.importUser(dataMap, "user");
    }
}
