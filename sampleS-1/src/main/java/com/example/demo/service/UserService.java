package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

  /**
   * ユーザー情報 Repository
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * ユーザー情報 全検索
   * @return 検索結果
   */
  public List<User> searchAll() {
    return userRepository.findAll();
  }
  
  public Page<User> seachpageAll(Pageable pageable) {
	    return userRepository.findAll(pageable);
	  }
  
  /**
   * ユーザー情報 主キー検索
   * @return 検索結果
   */
  public User findById(Long id) {
    return userRepository.findById(id).get();
  }
  
  
  //検索機能
  public Page<User> getUsers(Pageable pageable){
	    return userRepository.findByStatusCode("accepted", pageable); // (3)
	}
  

  /**
   * ユーザー情報 新規登録
   * @param user ユーザー情報
   */
  public void create(UserRequest userRequest) {
    Date now = new Date();

    User user = new User();
    user.setName(userRequest.getName());
    user.setAddress(userRequest.getAddress());
    user.setPhone(userRequest.getPhone());
    user.setCreateDate(now);
    user.setUpdateDate(now);
    userRepository.save(user);
  }
  
  
  /**
   * ユーザー情報 更新
   * @param user ユーザー情報
   */
  public void update(UserUpdateRequest userUpdateRequest) {
      User user = findById(userUpdateRequest.getId());
      user.setAddress(userUpdateRequest.getAddress());
      user.setName(userUpdateRequest.getName());
      user.setPhone(userUpdateRequest.getPhone());
      user.setUpdateDate(new Date());
      userRepository.save(user);
  }
  
  
  /**
   * ユーザー情報 物理削除
   * @param id ユーザーID
   */
  public void delete(Long id) {
      User user = findById(id);
      userRepository.delete(user);
  }
  
}