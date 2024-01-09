package com.example.repository;

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
}
//qqqq