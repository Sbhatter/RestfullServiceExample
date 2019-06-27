package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityResourceStub;

@Path("activities") // http://localhost:8080/exercise-services/activities
public class ActivityResource {
	private ActivityRepository activityRepository = new ActivityResourceStub();

	@GET
	// @Produces(MediaType.APPLICATION_XML)
	// @Produces(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActitives();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}") // http://localhost:8080/exercise-services/activities/1234
	public Activity getActivity(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}/user") // http://localhost:8080/exercise-services/activities/1234/user
	public User getActivityUser(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	// @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("activity") // http://localhost:8080/exercise-services/activities/activity
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {

		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));

		Activity activity = new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));

		activityRepository.create(activity);

		return activity;

	}

}
