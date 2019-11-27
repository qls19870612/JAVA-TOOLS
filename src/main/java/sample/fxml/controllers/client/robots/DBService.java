package sample.fxml.controllers.client.robots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.decodeGetLevel;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 15:08
 */
public class DBService {
    private static final Logger logger = LoggerFactory.getLogger(DBService.class);
    private final TomcatJdbcPool tomcatJdbcPool;

    public DBService() {
        tomcatJdbcPool = new TomcatJdbcPool("localhost:3306/online_0404_1", "root", "123456");
    }

    public void close() {
        tomcatJdbcPool.close();
    }

    public ArrayList<String> getAllAccount(int limitLevel, int operatorId, int serverId) {
        ArrayList<String> accounts = new ArrayList<>();
        try (Connection conn = tomcatJdbcPool.getConnection(); PreparedStatement pstmt = conn
                .prepareStatement("select name,easy_info from users where server_id=" + serverId + " and operator_id=" + operatorId)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int easy_info = rs.getInt("easy_info");
                int level = decodeGetLevel(easy_info);
                if (level >= limitLevel) {
                    accounts.add(name);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("getAllAccount accounts.size:{}", accounts.size());
        return accounts;
    }
}
