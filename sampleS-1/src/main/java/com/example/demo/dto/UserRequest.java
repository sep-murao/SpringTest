package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * ユーザー情報 リクエストデータ
 */
@Data
public class UserRequest implements Serializable {

  /**
   * 名前
   */
  @NotEmpty(message = "名前を入力してください")
  @Size(max = 20, message = "名前は20字以内で入力してください")
  private String name;

  /**
   * 住所
   */
  @Size(max = 40, message = "住所は40文字以内で入力してください")
  private String address;

  /**
   * 電話番号
   */
  @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "電話番号は「000-0000-0000」の形式で入力してください")  private String phone;
}
