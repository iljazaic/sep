package sep.project.Models.AtomicModels;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
  
import sep.project.Models.Interfaces.JsonManager;

public class PointTrade implements JsonManager {
    private String tradeName;
    private Long pointTradeId;
    private String creatorResidentName;
    private String description;
    private int pointAmount;

    public PointTrade(String tradeName, int pointAmount, String descString, String creatorResidentName) {
        this.creatorResidentName = creatorResidentName;
        this.pointAmount = pointAmount;
        this.description = descString;
        this.tradeName = tradeName;

        this.pointTradeId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    // both constructors for jackson to persist this thing
    public PointTrade() {
    };

    public PointTrade(String tradeName, int pointAmount, String description, String creatorResidentName, Long pointTradeId) {
        this.pointAmount = pointAmount;
        this.pointTradeId = pointTradeId;
        this.description = description;
        this.tradeName = tradeName;
        this.creatorResidentName = creatorResidentName;
    }

    public String getTradeName() {
        return this.tradeName;
    }

    public Long getPointTradeId() {
        return pointTradeId;
    }

    public void setPointTradeId(Long pointTradeId) {
        this.pointTradeId = pointTradeId;
    }

    public String getCreatorResidentNamee() {
        return creatorResidentName;
    }

    public void setCreatorResidentId(String creatorResidentName) {
        this.creatorResidentName = creatorResidentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }



    //trying a new method via hashmaps, looks terrible but more efficient
    @Override
    public String toJsonString() throws Exception {
        Map<String, Object> tradeData = new HashMap<>();
        tradeData.put("tradeName", getTradeName());
        tradeData.put("creatorResidentName", getCreatorResidentNamee());
        tradeData.put("pointAmount", getPointAmount());
        tradeData.put("pointTradeId", getPointTradeId());
        tradeData.put("description", getDescription());
        ObjectMapper objectMapper = new ObjectMapper();
        String jacksonData = objectMapper.writeValueAsString(tradeData);
        return jacksonData;
    }
}
