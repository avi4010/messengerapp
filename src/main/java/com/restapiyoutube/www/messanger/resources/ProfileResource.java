package com.restapiyoutube.www.messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restapiyoutube.www.model.Profile;
import com.restapiyoutube.www.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getAllProfilesFromMap2(){
		return profileService.getAllProfilesFromMap();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") long msgId){
		return profileService.getProfile(msgId);
	}
	
	@POST
	public Profile addProfilesFromMap(Profile profile){
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfilesFromMap(@PathParam("profileName") String profName, Profile profile){
		profile.setProfileName(profName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public Profile deleteProfilesFromMap(@PathParam("profileName") String profName){
		return profileService.removeProfile(profName);
	}

}
