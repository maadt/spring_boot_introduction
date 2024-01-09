//クラス名やフィールド名がテーブル名とカラム名と同じ場合、@Tableと@Columnは省略可能だが、
//ソースコードの視認性が上がるため原則アノテーションを設定する。

package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity //クラスに指定することで、このクラスがEntityクラスであることを示します。
@Table(name = "EMPLOYEES") //指定したテーブルとマッピング(紐づけ)されます。
public class Employee { //原則としてクラス名はテーブル名を単数形で表す。

    @Id //そのフィールドが主キーであることを示します。
    @SequenceGenerator(name = "EMPLOYEE_ID_GENERATOR", sequenceName = "EMPLOYEE_ID_SEQ", allocationSize = 1) //エンティティの主キーにユニークな値を自動的に生成
      //name ... シーケンスジェネレータの名前を定義
      //sequenceName ... データベース内のシーケンスの名前を定義
      //allocationSize ... シーケンスの割り当てサイズ（インクリメントサイズ）を定義

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_GENERATOR") //データ挿入時に主キーの値を自動生成するために利用
      //strategy ... どのようにして各エンティティのインスタンスにユニークなIDが割り当てられるかを定義
      //generator ... シーケンスジェネレータの名前を指定

    @Column(name = "ID")
    private Integer id; //フィールド ≒ カラム

    @Column(name = "NAME")
    private String name; // フィールド ≒ カラム

    @Column(name = "DEPARTMENT")
    private String department; // フィールド ≒ カラム

    // 以下はすべてアクセサメソッド

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
