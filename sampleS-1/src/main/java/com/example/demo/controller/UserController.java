package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {

  /**
   * ユーザー情報 Service
   */
  @Autowired
  UserService userService;

  /**
   * ユーザー情報一覧画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping(value = "/user/list")
  public String displayList(Model model, @PageableDefault(size =10) Pageable pageable) {
   // List<User> userlist = userService.searchAll();
    Page<User> userlist = userService.seachpageAll(pageable);
   // model.addAttribute("userlist", userlist);
    PageWrapper<User> page = new PageWrapper<User>(userlist, "list");
    model.addAttribute("page", page);
    model.addAttribute("content", page.getContent());
    
    //検索機能
    Page<User> userPage = UserService.getUsers(pageable); 
    model.addAttribute("userPage", userPage);


    return "user/list";
  }

  /**
   * ユーザー新規登録画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping(value = "/user/add")
  public String displayAdd(Model model) {
    model.addAttribute("userRequest", new UserRequest());
    return "user/add";
  }

  /**
   * ユーザー新規登録更新
   * @param userRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @RequestMapping(value = "/user/create", method = RequestMethod.POST)
  public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
    if (result.hasErrors()) {
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
      }

      model.addAttribute("validationError", errorList);
      return "user/add";
   
    }

    model.addAttribute("UserRequest", userRequest);
    //userService.update(userUpdateRequest);
  //  return String.format("/user/{id}"());
    return "user/view3";
}
  
  
  /**
   * 新規登録確認
   * @param userUpdateRequest
   * @param model
   * @return
   */
  @RequestMapping(value="/user/AddCommit", method=RequestMethod.POST)
  public String AddCommit( @ModelAttribute("userRequest") UserRequest userRequest,  Model model) {

      // ユーザー情報の更新
     // model.addAttribute("userUpdateRequest", userUpdateRequest);
     userService.create(userRequest);
     return "redirect:/user/list";
  }
  
 
  
  /**
   * 削除確認画面
   * @param id 表示するユーザーID
   * @param model Model
   * @return 
   */
  @GetMapping("/user/{id}")
  public String displayView(@PathVariable Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("userRequest", user);
    return "user/view2";
  }
  
  
  
  /**
   * ユーザー編集画面を表示
   * @param id 表示するユーザーID
   * @param model Model
   * @return ユーザー編集画面
   */
  @GetMapping("/user/{id}/edit")
  public String displayEdit(@PathVariable Long id, Model model) {
      User user = userService.findById(id);
      
      UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
      userUpdateRequest.setId(user.getId());
      userUpdateRequest.setName(user.getName());
      userUpdateRequest.setPhone(user.getPhone());
      userUpdateRequest.setAddress(user.getAddress());
      model.addAttribute("userUpdateRequest", userUpdateRequest);
      return "user/edit";
  }
  /**
   * ユーザー更新
   * @param userRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報詳細画面
   */
  @RequestMapping(value="/user/update", method=RequestMethod.POST)
  public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
      if (result.hasErrors()) {
          List<String> errorList = new ArrayList<String>();
          for(ObjectError error : result.getAllErrors()) {
              errorList.add(error.getDefaultMessage());
          }
            model.addAttribute("validationError", errorList);
            return "user/edit";
          }

      model.addAttribute("userUpdateRequest", userUpdateRequest);
      //userService.update(userUpdateRequest);
    //  return String.format("/user/{id}"());
      return "user/view";
  }
  
  
  
  /**
   * 編集確認
   * @param userUpdateRequest
   * @param model
   * @return
   */
  @RequestMapping(value="/user/updateCommit", method=RequestMethod.POST)
  public String updateCommit( @ModelAttribute("userUpdateRequest") UserUpdateRequest userUpdateRequest,  Model model) {

      // ユーザー情報の更新
     // model.addAttribute("userUpdateRequest", userUpdateRequest);
     userService.update(userUpdateRequest);
     // return "user/list";
     return "redirect:/user/list";
  }
  
  
  
  /**
   * ユーザー情報削除
   * @param id 表示するユーザーID
   * @param model Model
   * @return ユーザー情報詳細画面
   */
  @GetMapping("/user/{id}/delete")
  public String delete(@PathVariable Long id, Model model) {
      // ユーザー情報の削除
      userService.delete(id);
      return "redirect:/user/list";
  }
  
}