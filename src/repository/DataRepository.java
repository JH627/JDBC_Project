package repository;

import java.util.List;

public interface DataRepository {
    void addData(String data);

    List<String> findByTitle(String Title);

    List<String> findByTotalnum(String Totalnum);

    List<String> findByReleasedate(String predate, String postdate);
}