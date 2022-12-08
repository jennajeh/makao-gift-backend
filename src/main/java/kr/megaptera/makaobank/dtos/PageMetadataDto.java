package kr.megaptera.makaobank.dtos;

public class PageMetadataDto {
    private Integer totalPages;

    public PageMetadataDto() {
    }

    public PageMetadataDto(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
