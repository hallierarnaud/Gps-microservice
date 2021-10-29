package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tourGuide.user.UserDTOToMainService;
import tourGuide.user.UserDTOFromMainService;

@FeignClient(name = "tourGuide-application", url = "localhost:8080")
public interface UserProxy {

  @GetMapping(value = "/getUserThroughApplication")
  UserDTOToMainService getUserThroughApplication(@RequestParam String userName);

  //add an endpoint to get userName through application
  @GetMapping(value = "/getUserNameCheck")
  String getUserNameCheckThroughApplication(@RequestParam String userName);

  //add an endpoint to get user through application
  @GetMapping(value = "/getUser")
  UserDTOToMainService getUserThroughEndPoint(@RequestParam String userName);



  //Utilisation de cet endpoint
  //add an endpoint to get userDTO through application
  @GetMapping(value = "/userDTOFromMainService")
  UserDTOFromMainService getUserDTOFromMainService(@RequestParam String userName);

}
