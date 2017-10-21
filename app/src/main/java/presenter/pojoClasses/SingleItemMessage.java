package presenter.pojoClasses;

/**
 * Created by ddopik on 10/16/2017.
 */

public class SingleItemMessage {
    public Boolean updateFragmentOneList;


    String search_query;

    public SingleItemMessage() {
    }

    public Boolean getUpdateFragmentOneList() {
        return updateFragmentOneList;
    }

    public void setUpdateFragmentOneList(Boolean updateFragmentOneList) {
        this.updateFragmentOneList = updateFragmentOneList;
    }

    public String getSearch_query() {
        return search_query;
    }

    public void setSearch_query(String search_query) {
        this.search_query = search_query;
    }
}
