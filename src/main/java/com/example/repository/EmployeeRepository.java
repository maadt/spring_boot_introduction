package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

//Entityごとに定義する
//@Repositoryをインターフェースに設定する
//Spring Data JPAに定義されているJpaRepositoryを継承する(JPA利用時のみ)
//用意したいSQLごとにメソッドを作成する

@Repository //Repositoryインターフェースであることを示す。データベース操作を行うために定義
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//public interface Repository名 extends JpaRepository<Entityクラス名, 主キーの型>
	//JpaRepositoryを継承していることで、データの挿入(更新)、削除、単純なデータ取得のメソッドは継承したRepositoryでも定義せず利用可能。
    // データ呼び出し用のメソッドを定義します
	
    // 名前が一致した人をすべて取得する
    public List<Employee> findByName(String name);

    // 名前が一致した最初の人を取得する
    public Optional<Employee> findFirstByNameOrderByIdAsc(String name);

}



/*
<クエリメソッドの例1>
public List<Employee> findByName(String name);
・NAMEカラムの条件に引数を渡し、全件取得します。
・下記のクエリに相当する。

SELECT
    *
FROM
    EMPLOYEES
WHERE
    NAME = 引数の値
;



<クエリメソッドの例2>
public Optional<Employee> findFirstByNameOrderByIdAsc(String name);
・NAMEカラムの条件に引数を渡し、ID順にソートしたうえで1件のデータを取得します。
・下記のクエリに相当する。

SELECT * FROM (
    SELECT
        *
    FROM
        EMPLOYEES
    WHERE
        NAME = 引数の値
    ORDER BY
        ID ASC
)
WHERE
    ROWNUM = 1
;



<クエリメソッドの基本的な作成ルール>
・findByやfindFirstByよりあとの部分はSELECT文のWHERE句にあたる名前を付ける
・findBy「Name」やfindBy「Department」のように指定したフィールド名を条件に持つSQLがつくられる
・メソッドの引数は検索に使いたいフィールドの型に合わせる、引数名は自由
・クエリの結果が複数件の場合はList<Entityクラス>を1件の場合はOptional<Entityクラス>を返り値の型に指定する



<クエリメソッドでの条件分岐>
検索したいフィールドの数によってはAndやOrを付けることでAND句やOR句を生成し、
カラム名の後にLikeを続けて記述することで、LIKE句を使ったあいまい検索を行うことが可能です。

// SELECT * FROM employees WHERE name = 引数の値1 AND department = 引数の値2
public List<Employee> findByNameAndDepartment(String name, String department);

// SELECT * FROM employees WHERE name = 引数の値1 OR department = 引数の値2
public List<Employee> findByNameOrDepartment(String name, String department);

// SELECT * FROM employees WHERE name LIKE 引数の値
public List<Employee> findByNameLike(String name)
*/