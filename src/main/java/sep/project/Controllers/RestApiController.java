package sep.project.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * <h3>
 * returns only text
 * </h3>
 * <p>
 * for fetch calls that then load on page
 * </p>
 */
// ... imports
// ... class definition
@RestController
@RequestMapping("/api/products")
public class RestApiController {

    private final PointTradeList pointTradeList;
    private final ClovervilleResidentList clovervilleResidentList;
    private final CommunityGreenPoints communityGreenPoints;
    

    public RestApiController(PointTradeList pointTradeList, ClovervilleResidentList clovervilleResidentList,
            CommunityGreenPoints communityGreenPoints) {
        this.communityGreenPoints = communityGreenPoints;
        this.pointTradeList = pointTradeList;
        this.clovervilleResidentList = clovervilleResidentList;
    }


    @GetMapping("/pointTrades")
    public String getPointTrades() throws Exception {
        return pointTradeList.toJsonString();
    }

    @GetMapping("/greenPoints")
    public String getGreenPoints() {
        return communityGreenPoints.toJsonString();
    }
    
    @GetMapping("/residents")
    public String getResidents() {
        return clovervilleResidentList.toJsonString();
    }

    @GetMapping("/communityTasks")
    public String getCommunityTasks() {
        return ;
    }
    
    
    

}