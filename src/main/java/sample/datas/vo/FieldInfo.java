package sample.datas.vo;

import sample.utils.CodeCreateUtils;
import sample.utils.FieldMergerEnum;
import sample.utils.StringUtils;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 $date$
 */
public class FieldInfo {
    private String _name;
    private String _type;
    private String _comment;
    //    public String javaCode;
    private String _fieldName;
    //    public String defautValue;
    private String _nameSuffix;
    private String _typeCol;
    private String _filedType;
    private StringBuffer _cfgKeys;
    private StringBuffer _comments;
    private FieldMergerEnum mergerEnum = FieldMergerEnum.NONE;

    public FieldInfo(String filedCol, String typeCol, String commentCol) {
        setInfo(filedCol, typeCol, commentCol);
    }

    public void setInfo(String filedCol, String typeCol, String commentCol) {
        this._name = filedCol;

        this._typeCol = typeCol;
        String[] types = typeCol.split(":");
        this._type = types[0];
        if (types.length > 1) {
            this._nameSuffix = types[1];
        }
        this._comment = commentCol;
        String[] nameArr = _name.split("_");
        if (_name.startsWith("q_")) {
            _fieldName = StringUtils.toHump(_name.substring(2), "_");
        } else {
            _fieldName = StringUtils.toHump(_name, "_");
        }
    }

    public String getNameSuffix() {
        return _nameSuffix;
    }

    public FieldMergerEnum getMergerEnum() {
        return mergerEnum;
    }

    public void merger(FieldInfo fieldInfo, FieldMergerEnum mergerEnum) {
        this.mergerEnum = mergerEnum;
        if (_cfgKeys == null) {
            _cfgKeys = new StringBuffer();
            _cfgKeys.append("\"");
            _cfgKeys.append(this._name);
            _cfgKeys.append("\"");
            _comments = new StringBuffer();
            _comments.append(formatComment(_comment));
        }
        _comments.append("=");
        _comments.append(formatComment(fieldInfo._comment));
        _cfgKeys.append(", \"");
        _cfgKeys.append(fieldInfo._name);
        _cfgKeys.append("\"");
        if (mergerEnum == FieldMergerEnum.SUFFIX) {
            _filedType = _type + "[]";
        }
    }

    private static String formatComment(String comment) {
        if (comment.length() > 10) {
            comment = comment.substring(0, 8) + "...";
        }
        return comment;
    }

    public String getCfgKeys() {
        if (_cfgKeys != null) {
            return "{" + _cfgKeys.toString() + "}";
        }
        return "\"" + _name + "\"";
    }

    public String getFieldType() {
        if (_filedType != null) {
            return _filedType;
        }
        return _type;
    }

    private static String filterFlag(String comments) {
        comments = comments.trim();
        comments = comments.replaceAll("\\r", "");
        comments = comments.replaceAll("\\n", "");
        comments = comments.replaceAll("\"", "\'");
        comments = comments.replaceAll("\\)", "）");
        comments = comments.replaceAll("\\(", "（");
        return comments;
    }

    public String getComments() {
        if (_comments != null) {
            return "\"" + filterFlag(_comments.toString()) + "\"";
        }
        return "\"" + filterFlag(_comment) + "\"";
    }

    public String getFieldName() {
        if (mergerEnum == FieldMergerEnum.SUFFIX) {
            return _nameSuffix;
        }
        if (getMergerEnum() == FieldMergerEnum.PARSER) {
            String classType = getFieldType();
            JavaSrcFileReadInfo classTypeInfo = CodeCreateUtils.parserClassTypeMap.get(classType);
            if (classTypeInfo != null) {
                classType = classTypeInfo.parseType;
            }
            return getFirstCharLower(classType);
        }
        return _fieldName;
    }

    private static String getFirstCharLower(String classType) {
        return Character.toLowerCase(classType.charAt(0)) + classType.substring(1);
    }

    public String getTypeCol() {
        return _typeCol;
    }
}
