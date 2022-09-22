package exercise;

import jdk.jshell.execution.Util;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private String address;

    public FileKV(String address, Map<String, String> map) {
        this.address = address;
        Utils.writeFile(address, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(address));
        map.put(key, value);
        Utils.writeFile(address, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(address));
        map.remove(key);
        Utils.writeFile(address, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(address));
        if(map.containsKey(key)) {
            return map.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(address));
    }
}
// END
