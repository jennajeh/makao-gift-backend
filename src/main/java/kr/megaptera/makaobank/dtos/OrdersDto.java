package kr.megaptera.makaobank.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderDto> orders;
    private PageMetadataDto metadata;

    public OrdersDto(List<OrderDto> orders, PageMetadataDto metadata) {
        this.orders = orders;
        this.metadata = metadata;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public PageMetadataDto getMetadata() {
        return metadata;
    }
}
