package core.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Page <T>{
    private int limit;
    private int pageNumber;
    private int pageMin;
    private int pageMax;
    private int totalItems;
    private Iterable<T> itemsForPageList;


    public int getOffset() {
        int offset = this.limit * (this.pageNumber - 1);
        return offset;
    }


    public int getMinRange() {
        int minRange = pageNumber - pageMin;
        int minRange1 = Math.max(minRange, 1);
        return minRange1;
    }

    public int getMaxRange() {
        int maxRange = pageNumber + pageMax;
        int maxRange1 = Math.min(maxRange, getTotalPages());
        return maxRange1;
    }


    public int getTotalPages() {
        return (int) Math.ceil(totalItems * 1.0 / limit);
    }


    public int getNextPage() {
        int nextPage = Math.min(pageNumber + 1, getTotalPages());
        return nextPage;
    }


    public int getPreviousPage() {
        int previousPage = Math.max(pageNumber - 1, 1);
        return previousPage;
    }


    public boolean hasNextPage() {
        if (pageNumber == getNextPage()) {
            return false;
        }
        return true;
    }


    public boolean hasPreviousPage() {
        if (pageNumber == getPreviousPage()) {
            return false;
        }
        return true;
    }

}
