package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tourGuide.user.User;
import tourGuide.user.UserDTORequest;

@FeignClient(name = "tourGuide-application", url = "localhost:8080")
public interface UserProxy {

  @GetMapping(value = "/getUserThroughApplication")
  User getUserThroughApplication(@RequestParam String userName);

  //add an endpoint to get userName through application
  @GetMapping(value = "/getUserNameCheck")
  String getUserNameCheckThroughApplication(@RequestParam String userName);

  //add an endpoint to get user through application
  @GetMapping(value = "/getUser")
  User getUserThroughEndPoint(@RequestParam String userName);

  //add an endpoint to get userDTO through application
  @GetMapping(value = "/getUserDTO")
  UserDTORequest getUserDTOThroughEndPoint(@RequestParam String userName);

}
