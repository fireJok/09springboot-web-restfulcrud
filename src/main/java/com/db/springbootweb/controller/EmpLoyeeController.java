package com.db.springbootweb.controller;

import com.db.springbootweb.dao.DepartmentDao;
import com.db.springbootweb.dao.EmployeeDao;
import com.db.springbootweb.entities.Department;
import com.db.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmpLoyeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    //查询所有员工列表页面
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        //因为把list页面放到了emp包中，所以要写成emp/list
        //这样模板引擎帮我们拼接路径的时候就是templates/emp/list.html;
        return "emp/list";
    }
    //员工添加
    @RequestMapping("/emp")
    public String toAddPage(Model model){
        //来到员工添加页面，需要先查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //springMVC自动将属性封装到对象中
    @PostMapping("/emp")
    public String addEmp(Employee employee){
    //来到员工列表页面
        //"/"代表当前项目
        System.out.println("员工信息"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //来到修改页面，查出当前员工信息，并进行回显
    @RequestMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id")Integer id,Model model){
    Employee employee=employeeDao.get(id);
    model.addAttribute("emp",employee);
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("depts",departments);
    //回到修改页面(add是一个修改和添加合一的页面)
    return "emp/add";
    }
    //员工修改，需要提交员工id
    @PutMapping("/emp")//只接受put方式的请求
    public String update(Employee employee){
        System.out.println("修改的员工信息"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id")Integer id){
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/emps";

    }

}
