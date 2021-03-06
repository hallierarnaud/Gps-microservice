package tourGuide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.UUID;

import tourGuide.gps.DTO.VisitedLocationResponse;
import tourGuide.gps.service.GpsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GpsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GpsService gpsService;

  @Test
  public void getUserLocation_shouldReturnOk() throws Exception {
    when(gpsService.trackUserLocation(any(UUID.class))).thenReturn(new VisitedLocationResponse());
    mockMvc.perform(get("/trackUserLocations")
            .contentType(MediaType.APPLICATION_JSON)
            .param("userId", "6ba7b810-9dad-11d1-80b4-00c04fd430c8"))
            .andExpect(status().isOk());
  }

  @Test
  public void getAttractions_shouldReturnOk() throws Exception {
    when(gpsService.getAttractions()).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/attractions")).andExpect(status().isOk());
  }

}
