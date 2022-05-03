import org.json.simple.JSONObject;

public class factInfo {
    public String id;
    public String text;
    public String type;
    public String user;
    public Integer upvotes;

    public factInfo(JSONObject jsonObject){

    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public factInfo(String id, String text, String type, String user, Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return "factInfo{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
