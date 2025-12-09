package sep.project.Models.AtomicModels;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PointTrade {
    private String tradeName;
    private Long pointTradeId;
    private Long creatorResidentId;
    private String description;
    private int pointAmount;

    public PointTrade(String name, int pointAmount, String descString, Long creatorResidentId) {
        this.creatorResidentId = creatorResidentId;
        this.pointAmount = pointAmount;
        this.description = descString;
        this.tradeName = name;

        this.pointTradeId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public String toJsonString() throws Exception {
        Map<String, Object> tradeData = new HashMap<>();
        tradeData.put("tradeName", getTradeName());
        tradeData.put("creatorResidentId", getCreatorResidentId());
        tradeData.put("pointAmount", getPointAmount());
        tradeData.put("pointTradeId", getPointTradeId());
        tradeData.put("description", getDescription());
        ObjectMapper objectMapper = new ObjectMapper();
        String jacksonData = objectMapper.writeValueAsString(tradeData);
        return jacksonData;
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

    public Long getCreatorResidentId() {
        return creatorResidentId;
    }

    public void setCreatorResidentId(Long creatorResidentId) {
        this.creatorResidentId = creatorResidentId;
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
}
