package de.jayy.data;

public interface DataStorage {
    String getClearFromMD5(String md5);
    void storeMD5(String clear, String md5);
    int getMD5Count();
}