package com.universitypractice.springapplication.services.interfaces.useroperations;

import com.universitypractice.springapplication.models.UserStatusesModel;
import com.universitypractice.springapplication.models.logmodels.UserStatusesRequestModel;
import com.universitypractice.springapplication.services.interfaces.baseoperations.GetService;

import java.util.List;

public interface GetUserStatusesModelsService extends GetService<List<UserStatusesModel>, UserStatusesRequestModel> {

}
