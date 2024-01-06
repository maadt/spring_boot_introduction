package com.example.form;

public class SampleForm {
    // フォームから送信されるフィールド
    private String name; // 名前
    private String bloodType; // 血液型

    // 以下、アクセスメソッド
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}

