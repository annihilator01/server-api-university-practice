package com.universitypractice.springapplication.services.interfaces.useroperations;

import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.services.interfaces.baseoperations.AGUService;

public interface AGUUserService extends AGUService<UserModel, ChangeStatusModel, Long> {

}
