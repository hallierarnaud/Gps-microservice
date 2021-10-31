package tourGuide;

import com.jsoniter.output.JsonStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tourGuide.gps.service.LocationMS;
import tourGuide.proxies.UserProxy;
import tourGuide.user.UserDTOToMainService;

@RestController
public class TourGuideController {

	/*@Autowired
    LocationMS locationMS;

	@Autowired
    private UserProxy userProxy;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }*/
    
    /*@RequestMapping("/getLocation")
    public String getLocation(@RequestParam String userName) {
    	VisitedLocation visitedLocation = tourGuideService.getUserLocation(getUser(userName));
		return JsonStream.serialize(visitedLocation.location);
    }*/

    //Commentage de cette méthode
    /*@RequestMapping("/getLocation")
    public String getLocationThroughMS(@RequestParam String userName) {
        VisitedLocation visitedLocation = locationMS.getUserLocation(getUserDTOThroughApplication(userName));
        return JsonStream.serialize(visitedLocation.location);
    }*/

    //Commentage de cette méthode
    //add an endpoint to get user's location without the back and forth
    /*@RequestMapping("/getLocationWithUser")
    public String getLocationThroughMSWithUser(@RequestParam String userName) {
        VisitedLocation visitedLocation = locationMS.getUserLocation(getUserDTO(userName));
        return JsonStream.serialize(visitedLocation.location);
    }*/
    
    //  TODO: Change this method to no longer return a List of Attractions.
 	//  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
 	//  Return a new JSON object that contains:
    	// Name of Tourist attraction, 
        // Tourist attractions lat/long, 
        // The user's location lat/long, 
        // The distance in miles between the user's location and each of the attractions.
        // The reward points for visiting each Attraction.
        //    Note: Attraction reward points can be gathered from RewardsCentral
    //Commentage de cette méthode
    /*@RequestMapping("/getNearbyAttractions")
    public String getNearbyAttractions(@RequestParam String userName) {
    	VisitedLocation visitedLocation = locationMS.getUserLocation(getUserDTO(userName));
    	return JsonStream.serialize(locationMS.getNearByAttractions(visitedLocation));
    }*/
    
    /*@RequestMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() {
    	// TODO: Get a list of every user's most recent location as JSON
    	//- Note: does not use gpsUtil to query for their current location, 
    	//        but rather gathers the user's current location from their stored location history.
    	//
    	// Return object should be the just a JSON mapping of userId to Locations similar to:
    	//     {
    	//        "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371} 
    	//        ...
    	//     }
    	
    	return JsonStream.serialize("");
    }*/

    //Commentage de cette méthode suite au dernière modification
    /*@GetMapping("/getVisitedLocations")
    public List<VisitedLocationResponse> getVisitedLocations(@RequestParam String userName) {
        return locationMS.getVisitedLocations(getUserDTO(userName));
    }*

    @GetMapping("/getAttractions")
    public List<AttractionResponse> getAttractions() {
        return locationMS.getAttractions();
    }
    /*@GetMapping("/getAttraction")
    public AttractionResponse getAttraction(@RequestParam int attractionNumber) {
        return locationMS.getAttraction(attractionNumber);
    }*/

    /*private UserDTOToMainService getUserDTO(String userName) {
    	return locationMS.returnModifiedUserDTOToMainService(userName);
    }*/

    /*@GetMapping("/getUserThroughApplication")
    public UserDTOToMainService getUserDTOThroughApplication(String userName) {
      return userProxy.getUserThroughApplication(userName);
    }

    //add an endpoint to get user through application
    @GetMapping("/getUser")
    public UserDTOToMainService getUserThroughEndPoint(String userName) {
      return userProxy.getUserThroughEndPoint(userName);
    }*/


/*Y
    //Utilisation de cet endpoint
    //add an endpoint to get userDTO through application
    @GetMapping("/userDTOFromMainService")
    public UserDTOFromMainService getUserDTOFromMainService(String userName) {
        return userProxy.getUserDTOFromMainService(userName);
    }
*/


    //Utilisation de cet endpoint
    //add an endpoint to get user directly through GpsIsolation
    /*@GetMapping("/userDTOFromGpsService")
    public UserDTOToMainService getUserDTOToMainService(String userName) {
      return locationMS.returnModifiedUserDTOToMainService(userName);
    }*/



    //add two endpoints to enable communication between application and microservice
    /*@GetMapping("/getUserNameCheck")
    public String getUsernameCheckThroughApplication(String userName) {
      return userProxy.getUserNameCheckThroughApplication(userName);
    }
    @RequestMapping("/getLocationCheck")
    public String getLocationCheckWithUserThroughApplication(String userName) {
      String visitedLocation = locationMS.getUserLocationCheck(getUsernameCheckThroughApplication(userName));
      return visitedLocation;
    }*/

}