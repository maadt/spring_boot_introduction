package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Employee;
import com.example.service.EmployeeService;



@Controller //コントローラーとしての機能を付与
@RequestMapping("employee") 
  //"employee"URLに対するリクエストが来たらEmployeeControllerクラスが実行される

public class EmployeeController {

    private final EmployeeService employeeService; //インジェクションしたいフィールドを定義

    @Autowired //EmployeeServiceのインスタンスを生成して紐付け
    public EmployeeController(EmployeeService employeeService) { 
    //EmployeeServiceをコンストラクタ・インジェクション
    	
        this.employeeService = employeeService;
    }
    
    @GetMapping("/list") //"/list"URLに対するGETリクエストが来たら、showListメソッドを実行する。
    public String showList(Model model) { 
        List<Employee> employees = this.employeeService.findAllEmployee(); //全件取得
        model.addAttribute("employees", employees); //modelにデータを渡す
        return "employee/list"; //ビューで"employee/list"が表示される
    }
    
    // データの1件取得
    @GetMapping("/find/{employeeId}") //URLのパス変数がemployeeIdに渡される
    public String showEmployee(@PathVariable Integer employeeId, Model model) {
        // Serviceを呼び出し、Model属性にセットします
        Employee employee = this.employeeService.findEmployee(employeeId);
        model.addAttribute("employee", employee);
        return "employee/data";
    }
    
    @GetMapping("/searchByName/{name}") //URLのパス変数がnameに渡される
    public String searchEmployee(@PathVariable String name, Model model) {
        List<Employee> employees = this.employeeService.findByName(name); //nameによる絞り込み
        model.addAttribute("employees", employees);
        // データの全件取得時に作成したテンプレートファイルを流用します。
        return "employee/list";
    }
    
    // 冒頭で述べた通り、一般的にはPOST通信によりデータ挿入が行われます
    @GetMapping("/create")
    public String addEmployee(@RequestParam("name") String name
                              , @RequestParam("department") String department) {
        // データを挿入します
        this.employeeService.insert(name, department);
        // 一覧ページにリダイレクト(後述)します
        return "redirect:/employee/list";
    }
    
    // 一般的にはPOST通信によりデータ更新が行われます
    @GetMapping("/update/{employeeId}")
    public String editEmployee(@PathVariable Integer employeeId
                             , @RequestParam("name") String name
                             , @RequestParam("department") String department) {
        // データを更新します
        this.employeeService.update(employeeId, name, department);
        // 一覧ページにリダイレクトします
        return "redirect:/employee/list";
    }
    
    @GetMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        // データを削除します
        this.employeeService.delete(employeeId);
        // 一覧ページにリダイレクトします
        return "redirect:/employee/list";
    }
}


/*
<リダイレクト>
リダイレクトとはGET通信によって指定のページにリクエストさせる仕組みで、特定のページにアクセスされては困る場合によくリダイレクトが使われます。
今回の場合、リロードするとデータの再登録が何度でも実行されるため、一覧ページにリダイレクトするよう記述しました。
Spring Frameworkではredirect:に続けてパスを記述することでリダイレクトすることができます。

*/