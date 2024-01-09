package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.EmployeeRepository;

//Repositoryごとに定義する

@Service //Serviceクラスであることを示す。データの加工や計算などシステムに必要な処理全般を実行する。

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
    // ビジネスロジック用のメソッドを以降に定義します
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
*/