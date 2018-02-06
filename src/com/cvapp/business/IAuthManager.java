package com.cvapp.business;

import java.util.List;

import com.cvapp.entities.Activity;
import com.cvapp.entities.Person;

public interface IAuthManager {

	Activity addActivity(Activity activity);

	Activity updateActivity(Activity activity);

	Activity findActivity(Activity activity);

	Activity removeActivity(Activity activity);

	List<Activity> showActivities(Person p);

}
