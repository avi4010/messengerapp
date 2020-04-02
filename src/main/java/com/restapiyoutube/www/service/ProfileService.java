package com.restapiyoutube.www.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.restapiyoutube.www.database.DatabaseClass;
import com.restapiyoutube.www.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		
		profiles.put("avinash", new Profile(1L,"Avinash","Avinash","Seelam"));
		profiles.put("maynak", new Profile(2L,"Maynak","Maynak","Dubey"));
		profiles.put("mouli", new Profile(3L,"Mouli","Mouli","Veerubotla"));
		profiles.put("vikas", new Profile(4L,"Vikas","Vikas","Shivakumar"));
		
	}
	
	public List<Profile> getAllProfilesFromMap(){
		
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(long id){
		return profiles.get(id);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty())
			return null;
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}

}
