package com.baizhi.ems.controller;

import com.baizhi.ems.entity.Emp;
import com.baizhi.ems.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    //更新
    @PostMapping("update")
    public String update(Emp emp){
        empService.update(emp);
        return "redirect:/emp/findAll";
    }


    //根据id查询
    @GetMapping("findOne")
    public String findOne(String id,Model model){
        Emp emp = empService.findById(id);
        model.addAttribute("emp",emp);
        return "/ems/updateEmp";
    }


    //删除
//    @GetMapping("toDelete/{id}") @PathVariable("id")
    @GetMapping("toDelete")
    public String toDelete(String id){
        empService.toDelete(id);
        return "redirect:/emp/findAll";
    }

    //保存员工
    @PostMapping("saveEmp")
    public String saveEmp(Emp emp){
        empService.saveEmp(emp);
        return "redirect:/emp/findAll";
    }

    //查询所有
    @GetMapping("findAll")
    public String findAll(Model model){
        List<Emp> emps = empService.findAll();
        model.addAttribute("emps",emps);
        return "ems/emplist";
    }

}
