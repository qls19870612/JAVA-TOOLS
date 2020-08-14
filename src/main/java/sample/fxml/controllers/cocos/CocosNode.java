package sample.fxml.controllers.cocos;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import game.collection.IntHashMap;
import lombok.Getter;
import lombok.Setter;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/06/22 10:52
 */
@Getter
@Setter
public class CocosNode {
    private static final Logger logger = LoggerFactory.getLogger(CocosNode.class);
    public final String name;
    public final String type;
    private final JSONObject object;
    public final int id;
    private String codeType;

    public ArrayList<CocosNode> components = new ArrayList<>();

    public CocosNode(JSONObject object) {
        this.object = object;
        this.name = getNodeName(object);
        this.type = getNodeType(object);
        this.id = getNodeId(object);
    }

    public void initChildren(IntHashMap<CocosNode> idMap, ArrayList<CocosNode> list) {
        JSONArray childObjects = object.getJSONArray("_components");
        if (childObjects == null || childObjects.size() == 0) {
            codeType = this.type;
            return;
        }
        for (Object child : childObjects) {
            JSONObject object = (JSONObject) child;
            int id = object.getInteger("__id__");
            CocosNode cocosNode = list.get(id);
            if (cocosNode != null) {

                this.components.add(cocosNode);
            }
            else {
                logger.debug("initChildren id:{},id:{},type:{}", id,this.id,this.type);
            }
        }
        if (components.size() <= 0) {
            codeType = this.type;
            return;
        }
        if (childObjects.size() > 1) {
            for (CocosNode component : components) {
//                if (component.type) {
//
//                }
            }
            codeType = this.components.get(this.components.size()-1).type;
            return;
        }

        codeType = this.components.get(0).type;
    }

    public static int getNodeId(JSONObject object) {
        if (object.containsKey("node")) {
            JSONObject node = object.getJSONObject("node");
            return node.getInteger("__id__");
        }
        return 0;
    }

    public static String getNodeName(JSONObject object) {
        if (object.containsKey("_name")) {
            return object.getString("_name");
        }
        return "";
    }

    public static String getNodeType(JSONObject object) {
        if (object.containsKey("__type__")) {
            return object.getString("__type__");
        }
        return "";
    }

    public boolean isBind() {
        if (StringUtils.isNotEmpty(name)) {

            return this.name.startsWith("$");
        }
        return false;
    }
}
