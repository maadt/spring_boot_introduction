package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;


//Repositoryごとに定義する

@Service //Serviceクラスであることを示す。データの取得、加工や計算などシステムに必要な処理全般を実行する。

public class EmployeeService {

    private final EmployeeRepository employeeRepository; //インジェクションしたいフィールドを定義
    //private final Repository名 フィールド;

    @Autowired //引数に紐づくクラスのインスタンスを生成し、利用する

    public EmployeeService(EmployeeRepository employeeRepository) { //コンストラクタ・インジェクション
    //public コンストラクタ名(Repository名 引数)

        this.employeeRepository = employeeRepository;
        //this.フィールド = 引数;
        //複数のRepositoryのインジェクションが必要なら合わせて引数やフィールドの数が増えます。
    }

    // メソッド名はRepositoryの処理や最終的な返り値がわかるような名前にしましょう
    public List<Employee> findAllEmployee() {
    	//無条件なデータ検索となっているため、Entityクラスの複数取得を想定し返り値の型はList<Entityクラス>となっています。
        // employeesテーブルのデータをemployeeオブジェクトにつめて全件取得する
        return this.employeeRepository.findAll();
        //findAll()...データを全件取得する。JpaRepositoryを継承しているRepositoryで呼び出せるメソッドの一つ。
    }

    // 主キーでの1件検索
    public Employee findEmployee(Integer employeeId) {
        // データの1件取得
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
        // Optional<Entityクラス>...「nullが保存される可能性のあるオブジェクト」に対して利用するクラス。
        // findById()...主キーを指定してデータを1件だけ取得する。JpaRepositoryを継承しているRepositoryで呼び出せるメソッドの一つ。
        // OptionalからEntityクラスの取得を試みる
        Employee employee = optionalEmployee.get();
        return employee;
    }

    public List<Employee> findByName(String name) {
        return this.employeeRepository.findByName(name); //nameで絞り込みを行う
    }

    // 新規登録処理
    public Employee insert(String name, String department) {
        // 保存したいEntityクラスのインスタンスを作成する
        Employee employee = new Employee();

        // 引数で受けたname, departmentをEmployeeオブジェクトにセットします
        employee.setName(name);
        employee.setDepartment(department);

        // データベースに保存する
        return this.employeeRepository.save(employee);
    }

    // 更新処理
    public Employee update(Integer employeeId, String name, String department) {
        // 更新したいデータを取得する
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.get();

        // Entityクラスのフィールドに更新内容をセットする
        employee.setName(name);
        employee.setDepartment(department);

        // データベースに保存する
        return this.employeeRepository.save(employee);
    }

    // 削除処理
    public void delete(Integer id) {
        this.employeeRepository.deleteById(id);
    }
}


/*
<セッター・インジェクション>

呼び出された時のみセッターが実行されるため、オブジェクトが不要な場合インジェクションしないという選択が可能

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository; //インジェクションしたいフィールドの定義

    @Autowired // 呼び出された時だけインジェクションされます
    public void setEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}



<フィールド・インジェクション>

フィールドに直接インジェクションする方法。
コンストラクタ・インジェクションと比べるとシンプルに書くことができますが、
final修飾子を設定できないことや管理が煩雑になるという理由などから非推奨となっています。

@Service
public class EmployeeService {
    // finalを記述できないため、別の実装クラスで上書き可能
    @Autowired
    EmployeeRepository employeeRepository;
}

<Optional型について>
・「nullが保存される可能性のあるオブジェクト」に対して利用するクラスでJava８以降で導入されています。
・データ取得に失敗した場合、Entityのオブジェクトはnullとなりますが、それを見越した処理を実装することが出来ます。


public void test(String str1) {
    // 定義
    Optional<String> opt = str1;
    // Optionalに保存された値を取得する
    String str1 = opt.get(); // nullならNoSuchElementExceptionが発生する
    // 値が存在すればtrue、それ以外はfalse
    boolean bool1 = opt.isPresent();
    // 値が存在しない場合true、それ以外はfalse
    boolean bool2 = opt.isEmpty();
}


<データを1件保存するsave()メソッド>
Repositoryで呼び出せるメソッドの一つで、引数には保存したいEntityを渡します。
挿入したデータをEntityクラスとして返します。
Entityクラスの主キーフィールドに値が設定されていなければデータの挿入が、値が設定されていればデータの更新が実行されます。
サンプルコードの場合は、Entityクラスをあらたに作成し、id以外のフィールドを設定しているのでデータの挿入が行われます。
IDは@GeneratedValueと@SequenceGeneratorを設定しているので、データの挿入の際に自動的に採番されます。

<データの更新もできるsave()メソッド>
save()メソッドはデータの追加時と同じですが、Entityクラスには主キーであるidが設定されている状態で呼び出されるため、データの更新が行われます。
返り値データの挿入同様に更新した結果をEntityオブジェクトに変換し、取得できます。



<データを1件削除するdeleteById()メソッド>
JpaRepositoryを継承しているrepositoryクラスで呼び出せるメソッドの一つです。
引数には主キーの値を渡します。
主キーに応じたデータの削除を行います。
返り値はvoidです。
*/
